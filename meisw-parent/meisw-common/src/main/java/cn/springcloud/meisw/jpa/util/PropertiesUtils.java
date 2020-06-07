package cn.springcloud.meisw.jpa.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private static Configuration config = null;
	
	static {
		try {
			config = new PropertiesConfiguration("application.properties");
		} catch (ConfigurationException e) {
			String message = "读取全局配置文件application.properties失败!";
			log.error(message,e);
		}
	}
	
	public static String getString(String key) {
		String value = config.getString(key);
		log.debug("读取属性文件key:{},value:{}",key,value);
		return value;
	}
	
	public static String[] getStringArray(String key) {
		String[] valueArray = config.getStringArray(key);
		return valueArray;
	}
	
	public static int getInt(String key) {
		int value = -1;
		value = config.getInt(key);
		log.debug("读取配置文件key:{},value:{}",key,value);
		return value;
	}
}
