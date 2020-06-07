package cn.springcloud.meisw.jpa.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastEntityUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CastEntityUtil.class);
	
	/**
	 * @throws SecurityException @throws NoSuchMethodException 转换实体 @author meisw 2019年11月8日 上午9:47:51 @Method: castEntity @Description: TODO @param list @param
	 *             clazz @return @throws MeiswException @throws
	 */
	public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws MeiswException {
		List<T> returnList = new ArrayList<T>();
		Object[] co = list.get(0);
		Class[] c2 = new Class[co.length];
		
		// 确定构造方法
		for (int i = 0; i < co.length; i++) {
			c2[i] = co[i].getClass();
		}
		
		for (Object[] o : list) {
			try {
				Constructor<T> constructor = clazz.getConstructor(c2);
				try {
					returnList.add(constructor.newInstance(o));
				} catch (InstantiationException e) {
					log.info("实体转换异常：{},{}", "InstantiationException", e);
				} catch (IllegalAccessException e) {
					log.info("实体转换异常：{},{}", "IllegalAccessException", e);
				} catch (IllegalArgumentException e) {
					log.info("实体转换异常：{},{}", "IllegalArgumentException", e);
				} catch (InvocationTargetException e) {
					log.info("实体转换异常：{},{}", "InvocationTargetException", e);
				}
			} catch (NoSuchMethodException e) {
				log.error("实体转换异常：{},{}", "NoSuchMethodException", e);
				throw new MeiswException(e.getMessage());
			} catch (SecurityException e) {
				log.error("实体转换异常：{},{}", "SecurityException", e);
				throw new MeiswException(e.getMessage());
			}
			
		}
		return returnList;
	}
}
