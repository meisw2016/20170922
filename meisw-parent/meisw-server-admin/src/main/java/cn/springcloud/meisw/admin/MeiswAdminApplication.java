package cn.springcloud.meisw.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class MeiswAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiswAdminApplication.class, args);
    }
}