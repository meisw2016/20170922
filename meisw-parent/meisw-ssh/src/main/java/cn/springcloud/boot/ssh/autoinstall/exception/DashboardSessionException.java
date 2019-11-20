package cn.springcloud.boot.ssh.autoinstall.exception;

/**
 * @Description:连接主机异常
 * @Author: lynl
 * @Date 2018/3/12
 * @Version: 1.0
 */
public class DashboardSessionException extends Exception {

	private static final long serialVersionUID = 2952698637101238574L;

	public DashboardSessionException(String msg) {
		super(msg);
	}

}