package cn.springcloud.meisw.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * eureka server
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MeiswDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(MeiswDemo1Application.class, args);
    }
}