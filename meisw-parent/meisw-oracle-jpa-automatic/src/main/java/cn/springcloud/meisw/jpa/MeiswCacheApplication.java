package cn.springcloud.meisw.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import cn.gjing.tools.redis.cache.core.EnableSecondCache;

/**
 * eureka server
 */
@SpringBootApplication
//@EnableCaching  //开启缓存
@EnableSecondCache
@EnableSwagger2
public class MeiswCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswCacheApplication.class, args);
    }
}