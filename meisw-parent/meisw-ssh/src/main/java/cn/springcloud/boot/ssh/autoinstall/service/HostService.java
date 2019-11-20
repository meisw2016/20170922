package cn.springcloud.boot.ssh.autoinstall.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import cn.com.yusys.yusp.autoinstall.domain.DashboardCmdPair;
import cn.com.yusys.yusp.autoinstall.domain.HostDomain;
import cn.com.yusys.yusp.autoinstall.repository.mapper.HostMapper;
import cn.com.yusys.yusp.ssh.common.ConnectionInfo;
import cn.com.yusys.yusp.ssh.session.SimpleSshSession;
import cn.com.yusys.yusp.ssh.session.SshSessionFactory;

/**
 * @Description:主机页面业务层-进行数据的校验
 * @Author:
 * @Date
 * @Version: 1.0
 */
@Service
public class HostService {

	private final Logger log = LoggerFactory.getLogger(HostService.class);

	@Autowired
	private HostMapper mapper;

	/**
	 * 获取所有主机
	 *
	 * @return 静态主机列表
	 */
	public List<HostDomain> getHosts() {
		return mapper.queryAllHosts();
	}

	/**
	 * 通过主机名查询主机
	 *
	 * @param hostName-主机名
	 * @return 主机对象
	 */
	public HostDomain getHostByHostName(String hostName) {
		return mapper.queryByHostName(hostName);
	}
	
	/**
	 * 通过ip查询主机
	 * @param ip
	 * @return
	 */
	public HostDomain getHostDomainByHostIp(String ip) {
		return mapper.queryByHostIP(ip);
	}

	/**
	 * 添加主机
	 *
	 * @param host-待添加主机
	 * @return 添加成功或失败信息
	 */
	public String addHostInfo(HostDomain host) {
		log.debug("请求参数Host对象:{}", host.toString());
		HostDomain hd = mapper.queryByHostName(host.getHostName());

		String result = "";
		if (Objects.nonNull(hd) && host.getHostId() != null && host.getHostId().equals(hd.getHostId())) {
			BeanUtils.copyProperties(host, hd);
			result = mapper.updateHostDomain(hd) == 1 ? "success" : "failed";
			//更新业务关联信息
		} else if (host.getHostId() == null && hd == null) {
			//增加主机ID
			host.setHostId(UUID.randomUUID().toString());
			result = mapper.insertHostDomain(host) == 1 ? "success" : "failed";
			//插入业务关联信息
			//插入集群关联信息
		} else {
			//存在相同主机名的主机
			return "error!已存在同名主机，请修改主机名!";
		}

		return "success";
	}

	/**
	 * 测试连接
	 *
	 * @param host-主机信息
	 * @return 测试结果信息
	 */
	public String testConn(HostDomain host) {
		log.debug("请求参数Host对象:{}", host.toString());
		JSch jSch = new JSch();
		String msg = "success";
		Session session = null;
		try {
			session = jSch.getSession(host.getUserName(), host.getIp());
			session.setConfig("userauth.gssapi-with-mic", "no");
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(host.getPassword());
			session.connect(10000);
			// 得到主机名并设置
			host.setHostName(new SimpleSshSession(session).goCmd("hostname\n"));
		} catch (Exception e) {
			e.printStackTrace();
			msg = "err: 服务器连接超时";
		} finally {
			if (session != null) {
				session.disconnect();
			}
		}
		return msg;
	}

	/**
	 * 运行命令
	 *
	 * @param hostName-主机名
	 * @param dir-当前目录
	 * @param cmd-命令
	 * @return cmd运行返回对象
	 * @throws Exception
	 */
	public DashboardCmdPair runCmd(String hostName, String dir, String cmd) throws Exception {
		log.debug("请求参数hostName:{},dir:{},cmd:{}", hostName, dir, cmd);
		DashboardCmdPair pair = new DashboardCmdPair(dir, cmd);
		if (StringUtils.isNotEmpty(cmd)) {
			HostDomain hostDomain = getHostByHostName(hostName);
			SimpleSshSession session = SshSessionFactory.getInstance().getSession(getConnectionInfo(hostDomain));
			pair = session.goCmd(pair);
			log.info("在主机 [{}] 上执行命令 -> {}", hostDomain.getIp(), cmd);
		}
		return pair;
	}

	/**
	 * 删除主机
	 *
	 * @param host-删除主机
	 * @return 删除成功或失败信息
	 */
	public String delHostInfo(HostDomain host) {
		log.debug("请求参数Host对象:{}", host.toString());
		mapper.deleteByHostName(host.getHostName());
		//mapper.deleteHostClusterInfoByHostId(host.getHostId());
		return "success";
	}

	/**
	 * 构造主机连接对象
	 *
	 * @param host-主机对象
	 * @return 主机连接对象
	 */
	public ConnectionInfo getConnectionInfo(HostDomain host) {
		ConnectionInfo info = new ConnectionInfo();
		info.setHostName(host.getHostName());
		info.setPassword(host.getPassword());
		info.setUri(host.getIp());
		info.setUserName(host.getUserName());
		return info;
	}

}