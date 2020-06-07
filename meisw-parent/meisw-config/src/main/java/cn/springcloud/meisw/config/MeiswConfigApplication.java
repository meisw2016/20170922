package cn.springcloud.meisw.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * eureka server
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class MeiswConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswConfigApplication.class, args);
    }
}