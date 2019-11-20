package cn.springcloud.boot.ssh.ssh.cmd;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @Author: lynl
 * @Date 2018/3/12
 * @Version: 1.0
 */
public class CmdTypeConstants {

	public static final String CD = "cd";

	public static boolean isCDType(String cmd) {
		if (!StringUtils.isEmpty(cmd)) {
			return cmd.trim().toLowerCase().startsWith(CD);
		}
		return false;
	}

}