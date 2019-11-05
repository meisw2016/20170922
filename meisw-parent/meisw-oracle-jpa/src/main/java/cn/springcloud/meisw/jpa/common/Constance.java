package cn.springcloud.meisw.jpa.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constance {
	
	/**
	 * 
	 * @author meisw 2019年11月5日 下午3:15:21
	 * @Method: getCurrentTime 
	 * @Description: 获取当前时间
	 * @return 
	 * @throws
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
