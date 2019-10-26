package cn.springcloud.meisw.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * eureka server
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching  //开启缓存
@EnableSwagger2
public class MeiswDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(MeiswDemo1Application.class, args);
    }
}