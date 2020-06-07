//package cn.springcloud.meisw.jpa.conf.guava;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.google.common.cache.CacheBuilder;
//
//@Configuration
//@EnableCaching
//@EnableConfigurationProperties(GuavaProperties.class)
//public class GuavaCacheConfig {
//	
//	@Autowired
//	private GuavaProperties guavaProperties;
//	
//	@Bean
//	public CacheBuilder<Object,Object> cacheBuilder(){
//		Long duration = guavaProperties.getExpireAfterWriteDuration();
//		if(duration <= 0){
//			duration = 10L;
//		}
//		return CacheBuilder.newBuilder().expireAfterWrite(duration, TimeUnit.SECONDS);
//	}
//	
//	public CacheManager cacheManager(CacheBuilder<Object,Object> cacheBuilder){
//		GuavaCacheManager cacheManger = new GuavaCacheManager();
//		cacheManger.
//	}
//}
