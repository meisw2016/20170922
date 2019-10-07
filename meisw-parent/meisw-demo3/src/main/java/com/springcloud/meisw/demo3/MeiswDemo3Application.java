package com.springcloud.meisw.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MeiswDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(MeiswDemo3Application.class, args);
    }
}