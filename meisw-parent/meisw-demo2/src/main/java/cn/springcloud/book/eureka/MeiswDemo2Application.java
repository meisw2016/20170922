package cn.springcloud.book.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * eureka server
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MeiswDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(MeiswDemo2Application.class, args);
    }
}