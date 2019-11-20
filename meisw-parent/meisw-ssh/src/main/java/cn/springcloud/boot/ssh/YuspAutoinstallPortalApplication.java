package cn.springcloud.boot.ssh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "cn.com.yusys.yusp.**.repository.mapper" })
public class YuspAutoinstallPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuspAutoinstallPortalApplication.class, args);
	}

}
