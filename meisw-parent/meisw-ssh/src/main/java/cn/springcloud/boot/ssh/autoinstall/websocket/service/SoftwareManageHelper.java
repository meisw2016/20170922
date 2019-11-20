package cn.com.yusys.yusp.autoinstall.websocket.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.yusys.yusp.autoinstall.domain.HostDomain;
import cn.com.yusys.yusp.autoinstall.repository.mapper.HostMapper;
import cn.com.yusys.yusp.autoinstall.websocket.common.SshClient4SoftwareManage;


/**
 * 软件管理帮助类，获取主机的操作系统版本，CPU内存配置，各种软件安装路径、版本
 * 调用getInfo返回的map格式如下
 * 格式如下,建议把map的key定义成常量：
 * 环境信息：
 *  CPUCORES=4C
	MemTotal=3773M
	Release=CentOS Linux release 7.5.1804 (Core) 
	HOSTIP=192.168.86.152 
  版本信息：
	Redis_Stable_Ver=redis-5.0.3
	Apollo_Ver=1.3.0
	Nexus_Ver=3.15.2-01
	RabbitMQ_Ver=3.7.3-1
	Nginx_Ver=nginx-1.14.2
	Mysql_Ver=5.7.24,
 安装路径：
	Apollo_Install_Dir=/usr/local/
	Nexus_Install_Dir=/usr/local/
	RabbitMQ_Install_Dir=/usr/local/
	Redis_Install_Dir=/usr/local/
	Nginx_Install_Dir=/usr/local/
	MySQL_Install_Dir=/usr/local/
 运行状态(key全小写)：
    apollo=is running
    redis=not install
    rabbitmq=is stopped
    mysql=is running
    nginx=not install
    nexus=	
 * @author danyubin
 *
 */
@Component
public class SoftwareManageHelper {

	private static Log logger = LogFactory.getLog(WebSocketApollo.class);

	@Autowired
	private HostMapper hostServiceDBMapper;

	// ssh客户端
	private SshClient4SoftwareManage client;
	
	public Map<String,String> getInfo(String hostIp, String softwareName){
		try {
			HostDomain host = hostServiceDBMapper.queryByHostIP(hostIp);
			this.client = new SshClient4SoftwareManage(host, 22);
			client.connect();
			String cmdStr = "sh osinfo.sh " + softwareName.toLowerCase() + "\r";
			this.client.write("cd yusp-autoinstall/yusp-autoinstall-shell/tools\r");
			client.write("chmod 777 YUSP\r");
			client.write(cmdStr);
			client.write("cat osinfo\r");
			while(!client.isFlag()) {
				client.writeToMap(client.getSsh2Session().getStdout());
			}
			client.write("rm -rf osinfo\r");
			client.disconnect();
			logger.info(client.getSoftwareManageMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return client.getSoftwareManageMap();
	}
}
