package cn.springcloud.meisw.strategy.methodStatistics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CostTimeAOP {
	
	private static final Logger log = LoggerFactory.getLogger(CostTimeAOP.class);
	
	@Pointcut("@annotation(cn.springcloud.meisw.strategy.CostTime")
	public void costTimePointCut(){}
	
	public Object around(ProceedingJoinPoint point)throws Throwable{
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		long time = System.currentTimeMillis()-beginTime;
		logCostTime(point,time);
		return result;
	}
	
	private void logCostTime(ProceedingJoinPoint joinPoint,long time){
		MethodSignature sinature = (MethodSignature)joinPoint.getSignature();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = sinature.getName();
		log.info("class:{},method:{},cost:{}ms",className,methodName,time);
	}
}
