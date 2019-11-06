package cn.springcloud.meisw.jpa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MeiswLog {
	/**
	 * 
	 * @author meisw 2019年11月6日 下午3:13:15
	 * @Method: operationType 
	 * @Description: 要执行的操作类型 比如：add操作
	 * @return 
	 * @throws
	 */
	public String operationType() default "";
	
	/**
	 * 
	 * @author meisw 2019年11月6日 下午3:13:52
	 * @Method: operationName 
	 * @Description: 要执行的具体操作 比如：添加用户
	 * @return 
	 * @throws
	 */
	public String operationName() default "";
}
