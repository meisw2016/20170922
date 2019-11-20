package cn.springcloud.boot.ssh.autoinstall.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import cn.com.yusys.yusp.autoinstall.domain.DashboardCmdPair;
import cn.com.yusys.yusp.autoinstall.domain.HostDomain;
import cn.com.yusys.yusp.autoinstall.domain.ResultDto;
import cn.com.yusys.yusp.autoinstall.service.HostService;
import cn.com.yusys.yusp.autoinstall.utils.DerbyUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 主机信息访问接口
 */
@RestController
public class HostResource {

	private final Logger log = LoggerFactory.getLogger(HostResource.class);

	@Autowired
	private DerbyUtil derbyUtil;

	@Autowired
	private HostService hostService;

	@Autowired
	private Environment env;
	
	@GetMapping("/api/host/infos")
    public ResultDto<List<HostDomain>> hostinfos() {
        ResultDto<List<HostDomain>> resultDto = new ResultDto<List<HostDomain>>(hostService.getHosts());
        resultDto.setTotal(resultDto.getData().size());
        return resultDto;
    }

	/**
	 * 新增主机
	 *
	 * @param host-待添加主机信息
	 */
	@PostMapping("/api/host/addhost")
	public ResultDto<String> addHostInfo(@RequestBody HostDomain host) {
		ResultDto<String> result = new ResultDto<String>();
		String addHostInfo = hostService.addHostInfo(host);
		result.setMessage(addHostInfo);
		return result;
	}

	/**
	 * 在主机上运行命令
	 *
	 * @param hostName-主机名
	 * @param dir-当前目录
	 * @param cmd-命令
	 * @return ssh返回输入流
	 * @throws Exception
	 */
	@GetMapping("/api/host/cmd")
	public ResultDto<DashboardCmdPair> runCmd(String hostName, String dir, String cmd) throws Exception {
		// modify 日志记录放到 service 层，原因：在 resource 记录不能获取到主机 IP ，在 service 层记录才能记录到 IP
		return new ResultDto<DashboardCmdPair>(hostService.runCmd(hostName, dir, cmd));
	}

	/**
	 * 删除主机信息
	 *
	 * @param host-主机对象
	 */
	@PostMapping("/api/host/delhost")
    public ResultDto<HostDomain> delHostInfo(@RequestBody HostDomain host) {
        ResultDto<HostDomain> result = new ResultDto<HostDomain>(host);
        result.setMessage(hostService.delHostInfo(host));
        return result;
    }

	/**
	 * 测试连接
	 *
	 * @param host-主机对象
	 * @return 测试连接结果，包括了查询后返回的主机名
	 */
	@PostMapping("/api/host/test")
	public ResultDto<HostDomain> testConn(@RequestBody HostDomain host) {
		ResultDto<HostDomain> resultDto = new ResultDto<HostDomain>();
		resultDto.setMessage(hostService.testConn(host));
		resultDto.setData(host);
		log.info("测试连接 [{}] -> {}", host.getIp(), resultDto.getMessage());
		return resultDto;
	}

	/**
	 * 初始化表
	 * 
	 * @return
	 */
	@GetMapping("/api/host/createTable")
	public ResultDto<String> createTable() {
		ResultDto<String> resultDto = new ResultDto<String>();
		String initFilePath = env.getProperty("derby.db.init-sql-path");
		File sqlFile = new File(initFilePath);
		String[] loadSql = loadSql(sqlFile);
		try {
			Connection conn = derbyUtil.getConnection();
			for (String sql : loadSql) {
				Statement st = conn.createStatement();
				st.execute(sql);
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultDto;
	}

	public String[] loadSql(File sqlFile) {
		List<String> sqlList = new ArrayList<String>();
		try {
			InputStream sqlFileIn = new FileInputStream(sqlFile);
			StringBuilder sqlSb = new StringBuilder();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead, "utf-8"));
			}
			// Windows 下换行是 \r\n, Linux 下是 \n
			String[] sqlArr = sqlSb.toString().split("(;\\s*\\r\\n)|(;\\s*\\n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (!sql.equals("")) {
					sqlList.add(sql);
					log.info("加载 SQL 语句：{}", sql);
				}
			}
		} catch (Exception e) {
			log.error("读取 SQL 文件，获取 SQL 语句异常：{}", e.getMessage());
		}
		if (sqlList.size() > 0) {
			String[] sqlArr = new String[sqlList.size()];
			sqlList.toArray(sqlArr);
			return sqlArr;
		} else {
			return null;
		}
	}

}