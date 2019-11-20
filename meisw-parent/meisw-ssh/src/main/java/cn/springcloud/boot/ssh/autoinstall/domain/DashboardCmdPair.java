package cn.springcloud.boot.ssh.autoinstall.domain;

/**
 * @Description: 命令参数对
 * @Author: lynl
 * @Date 2018/3/12
 * @Version: 1.0
 */
public class DashboardCmdPair {

	private String dir;

	private String out;

	private int code;

	public DashboardCmdPair(String dir, String out) {
		this.dir = dir;
		this.out = out;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}