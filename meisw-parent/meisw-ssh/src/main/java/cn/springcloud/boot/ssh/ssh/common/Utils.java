package cn.springcloud.boot.ssh.ssh.common;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @Description:
 * @Author: lynl
 * @Date 2018/3/12
 * @Version: 1.0
 */
public class Utils {

	private static final String PARENT = "..";

	private static final String PARTITION = "/";

	/**
	 * @param old-当前路径
	 * @param dir-必须以cd开头
	 * @return 新的路径
	 */
	public static String getNewDir(String old, String dir) {
		if (!StringUtils.isEmpty(dir)) {
			String tmp = dir.trim().substring(2).trim();
			if (StringUtils.startsWith(tmp, PARTITION)) {
				return tmp;
			}
			while (StringUtils.startsWith(tmp, PARENT)) {
				if (StringUtils.length(tmp) == 2) {
					tmp = "";
				} else {
					tmp = tmp.substring(3);
				}
				old = old.substring(0, old.lastIndexOf(PARTITION));
			}
			old = old + PARTITION + tmp;
		}
		return old;
	}

	/**
	 * @return 生成独立的id
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

	public static String handlePath(String origin) {
		String target = origin;
		if (origin.startsWith("project:")) {
			target = System.getProperty("user.dir") + origin.substring(8).trim();
		}
		return target;
	}

}