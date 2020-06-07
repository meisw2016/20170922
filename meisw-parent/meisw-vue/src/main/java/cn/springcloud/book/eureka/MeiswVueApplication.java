package cn.springcloud.book.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * eureka server
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MeiswVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswVueApplication.class, args);
    }
}