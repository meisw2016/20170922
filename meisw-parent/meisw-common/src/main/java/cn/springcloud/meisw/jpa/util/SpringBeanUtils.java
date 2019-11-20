package cn.springcloud.meisw.jpa.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringBean工具类
 * <p>
 * </p>
 * @author meisw 2019年11月20日 上午10:21:39
 * @ClassName SpringBeanUtils
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月20日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware, DisposableBean {
	
	private static ApplicationContext applicationContext = null;
	
	private static Logger log = LoggerFactory.getLogger(SpringBeanUtils.class);
	
	/**
	 * 取得存储在静态变量中的ApplicationContext @author meisw 2019年11月20日 上午10:28:58 @Method: getApplicationContext @Description: TODO @return @throws
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}
	
	/**
	 * 从静态变量applicationContext中取得Bean,自动转型为所赋值对象的类型 @author meisw 2019年11月20日 上午10:30:32 @Method: getBean @Description: TODO @param name @return @throws
	 */
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T)applicationContext.getBean(name);
	}
	
	/**
	 * 从静态变量applicationContext中取得Bean，自动转型为赋值对象的类型 @author meisw 2019年11月20日 上午10:32:05 @Method: getBean @Description: TODO @param requiredType @return @throws
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}
	
	/**
	 * 清除SpringContextHolder中的ApplicationContext为NULL @author meisw 2019年11月20日 上午10:33:41 @Method: clearHolder @Description: TODO @throws
	 */
	public static void clearHolder() {
		log.debug("清除SpringContextHolder中的ApplicationContext:{}", applicationContext);
		applicationContext = null;
	}
	
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicationContext属性未注入,请在applicationContext.xml中定义SpringContext.");
	}
	
	@Override
	public void destroy() throws Exception {
		SpringBeanUtils.clearHolder();
	}
	
	/**
	 * 实现ApplicationContextAware接口,注入Context到静态变量中
	 * @author meisw 2019年11月20日 上午10:35:29
	 * @Method: setApplicationContext
	 * @Description: TODO
	 * @param applicationContext
	 * @throws BeansException
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
		if (SpringBeanUtils.applicationContext != null) {
			log.warn("SpringContextHolder中的ApplicationContext被覆盖,原有ApplicationContext为：{}", SpringBeanUtils.applicationContext);
		}
		SpringBeanUtils.applicationContext = applicationContext;
	}
}
