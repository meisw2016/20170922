package cn.springcloud.book.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * eureka server
 */
@SpringBootApplication
public class MeiswElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswElasticApplication.class, args);
        System.out.println("启动成功!");
    }
}