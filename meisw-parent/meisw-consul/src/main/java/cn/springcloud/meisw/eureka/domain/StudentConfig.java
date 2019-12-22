package cn.springcloud.meisw.eureka.domain;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "student")
@Data
public class StudentConfig {
	
	private String name;
	private Integer age;
}
