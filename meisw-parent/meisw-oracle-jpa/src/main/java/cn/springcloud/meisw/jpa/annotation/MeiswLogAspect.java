//package cn.springcloud.meisw.jpa.annotation;
//
//import java.lang.reflect.Method;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 
// * <p></p>
// * @author meisw 2019年11月6日 下午3:16:24
// * @ClassName MeiswLogAspect
// * @Description 编写切面
// * @version V1.0   
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: {修改人} 2019年11月6日
// * @modify by reason:{方法名}:{原因}
// */
//@Aspect
//@Component
//public class MeiswLogAspect {
//	
//	public static final Logger log = LoggerFactory.getLogger(MeiswLogAspect.class); 
//	
//	@Pointcut("execution(* cn.springcloud.meisw.jpa.controller..*.*(..))")
//	public void controllerAspect() {}
//	
//	@Before("controllerAspect()")
//	public void doBefore(JoinPoint joinPoint) {
////		log.info("============== 执行controller前置通知 ==================");
////		if(log.isInfoEnabled()) {
////			log.info("before {}",joinPoint);
////		}
//	}
//	
//	/**
//	 * 
//	 * @author meisw 2019年11月6日 下午3:27:15
//	 * @Method: around 
//	 * @Description: 前置通知
//	 * @param joinPoint 
//	 * @throws
//	 */
//	@Around("controllerAspect()")
//	public void around(JoinPoint joinPoint) {
//		log.info("============== 执行controller环绕通知 ==================");
//		long startTime = System.currentTimeMillis();
//		try {
////			((ProceedingJoinPoint)joinPoint).proceed();
//			long endTime = System.currentTimeMillis();
//			if(log.isInfoEnabled()) {
//				log.info("around:{}\tUse time:{} ms!",joinPoint,(endTime-startTime));
//			}
//		} catch (Throwable e) {
//			long endTime = System.currentTimeMillis();
//			if(log.isInfoEnabled()) {
//				log.info("around {}\t Use time:{} ms with exception:{}",joinPoint,(endTime-startTime),e.getMessage());
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 * @author meisw 2019年11月6日 下午3:31:29
//	 * @Method: after 
//	 * @Description: 后置通知，用于拦截controller层记录用户的操作
//	 * @param joinPoint 
//	 * @throws
//	 */
//	@After("controllerAspect()")
//	public void after(JoinPoint joinPoint) {
////		log.info("功能正在完善中，开发工程师们正在加班加点的赶工呢!");
//	}
//	
//	/**
//	 * 
//	 * @author meisw 2019年11月6日 下午3:30:53
//	 * @Method: afterReturn 
//	 * @Description: 配置后置返回通知,使用在方法aspect()上注册的切入点
//	 * @param joinPoint 
//	 * @throws
//	 */
//	@AfterReturning("controllerAspect()")
//	public void afterReturn(JoinPoint joinPoint) {
////		log.info("============== 执行controller后置返回通知 ==================");
////		if(log.isInfoEnabled()) {
////			log.info("afterReturn:{}",joinPoint);
////		}
//	}
//	
//	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
//	public void doAfterThrowing(JoinPoint joinPoint ,Throwable e) {
//		
//		try {
//			String targetName = joinPoint.getTarget().getClass().getName();
//			String methodName = joinPoint.getSignature().getName();
//			Object[] arguments = joinPoint.getArgs();
//			Class targetClass = Class.forName(targetName);
//			Method[] methods = targetClass.getMethods();
//			String operationType = "";
//			String operationName = "";
//			for(Method method: methods) {
//				if(method.getName().equals(methodName)) {
//					Class[] clazzs = method.getParameterTypes();
//					if(clazzs.length == arguments.length) {
//						operationType = method.getAnnotation(MeiswLog.class).operationType();
//						operationName = method.getAnnotation(MeiswLog.class).operationName();
//						break;
//					}
//				}
//			}
//			
//			log.info("============== 异常通知开始 ==================");
//			log.info("异常代码：{}",e.getClass().getName());
//			log.info("异常信息：{}",e.getMessage());
//			log.info("异常方法：{}",(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"().")+operationType);
//		}catch(Exception e1) {
//			log.error("================== 异常通知异常 ================");
//			log.error("异常信息：{}",e1.getMessage());
//		}
//		log.error("异常方法：{}异常代码：{}异常信息：{}参数：{}",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),e.getClass().getName(),e.getMessage(),"");
//	}
//}
