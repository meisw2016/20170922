package cn.springcloud.book.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * eureka server
 */
@SpringBootApplication
@EnableConfigServer
public class MeiswConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswConfigApplication.class, args);
    }
}