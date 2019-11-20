package cn.springcloud.boot.ssh.autoinstall.domain;

/**
 * 单个主机信息
 */
public class HostDomain {

	// HOST_ID为新增字段
	private String hostId;

	// 主机名
	private String hostName;

	// 主机类型，虚拟机或物理机
	private String type;

	// 主机IP
	private String ip;

	// 用户名
	private String userName;

	// 密码
	private String password;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}