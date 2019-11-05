package cn.springcloud.meisw.jpa.annotation;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeiswAspect {
	
	private static Logger log = LoggerFactory.getLogger(MeiswAspect.class);
	
	@Pointcut(value = "@annotation(cn.springcloud.meisw.jpa.annotation.Meisw)")
	public void pointcut() {
		log.info("pointcut:{}",System.currentTimeMillis());
	}
	
	@Around("pointcut()")
	public Object handle(ProceedingJoinPoint point)throws Throwable{
		Method currentMethod = AspectUtil.getMethod(point);
		log.info("pointcut:{},currentMethod:{}  ,start ....",System.currentTimeMillis(),currentMethod);
		
		//执行业务
		Object result = point.proceed();
		log.info("{}业务执行完成!",result);
		return result;
	}
	
}
