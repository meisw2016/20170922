package cn.springcloud.meisw.jpa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Meisw {
	
	/**
	 * 
	 * @author meisw 2019年11月1日 下午5:13:34
	 * @Method: value 
	 * @Description: 业务的名称
	 * @return 
	 * @throws
	 */
	String value() default "";
	
	/**
	 * 
	 * @author meisw 2019年11月1日 下午5:13:56
	 * @Method: key 
	 * @Description: redis缓存的key（默认类名-方法名-自定义key）
	 * @return 
	 * @throws
	 */
	String key() default "";
	
	/**
	 * 是否刷新缓存，默认false
	 * @author meisw 2019年11月1日 下午5:14:38
	 * @Method: flush 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	boolean flush() default false;
	/**
	 * 
	 * @author meisw 2019年11月1日 下午5:15:07
	 * @Method: expire 
	 * @Description: 缓存时效时间，默认30
	 * @return 
	 * @throws
	 */
	long expire() default 30L;
	/**
	 * 
	 * @author meisw 2019年11月1日 下午5:15:40
	 * @Method: unit 
	 * @Description: 缓存时间单位，默认天
	 * @return 
	 * @throws
	 */
	TimeUnit unit() default TimeUnit.DAYS;
}
