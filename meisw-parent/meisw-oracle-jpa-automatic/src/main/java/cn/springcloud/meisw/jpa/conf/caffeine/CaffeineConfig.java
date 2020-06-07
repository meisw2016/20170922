//package cn.springcloud.meisw.jpa.conf.caffeine;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import cn.gjing.tools.redis.cache.CaffeineCache;
//import cn.gjing.tools.redis.cache.RedisCache;
//import cn.gjing.tools.redis.cache.SecondCache;
//
//@Configuration
//@EnableCaching
//public class CaffeineConfig {
//	
//	@Bean
//	public SecondCache secondCache(){
//		return SecondCache.builder().cachePrefix("msw").dynamic(true).build();
//	}
//	
//	@Bean
//	public RedisCache redisCache(){
//		return RedisCache.builder().expire(10).build();
//	}
//	
//	@Bean
//	public CaffeineCache caffeineCache(){
//		return CaffeineCache.builder().expireAfterWrite(3000).build();
//	}
//}
