package cn.springcloud.meisw.eureka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.springcloud.meisw.eureka.domain.StudentConfig;

/**
 * @author cb
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties({StudentConfig.class})
@ComponentScan(value = "com.sonsul.pusher")
@EnableScheduling //启用定时调度功能，Consul需要此功能来监控配置改变
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
