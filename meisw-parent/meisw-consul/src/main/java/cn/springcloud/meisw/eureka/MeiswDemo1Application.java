package cn.springcloud.meisw.eureka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import cn.springcloud.meisw.eureka.domain.StudentConfig;

/**
 * @author cb
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties({StudentConfig.class})
public class MeiswDemo1Application {
	
	/*@Value("${name}")
	private String name;*/
	
	public static void main(String[] args) {
		SpringApplication.run(MeiswDemo1Application.class, args);
	}

	/*@Override
    public void run(String... args) throws Exception {
		System.out.println("Consul4Application......启动成功 "+ name);
    }*/
	
}
