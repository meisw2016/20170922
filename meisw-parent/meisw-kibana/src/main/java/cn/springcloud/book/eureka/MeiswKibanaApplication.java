package cn.springcloud.book.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * eureka server
 */
@SpringBootApplication
public class MeiswKibanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswKibanaApplication.class, args);
        System.out.println("启动成功!");
    }
}