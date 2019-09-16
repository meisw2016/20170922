package cn.springcloud.book.eureka.automation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RfcConfig {

	@Bean
	public Integer setRfc() {
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","()*&^%$#@!~}{?><[]");
		return 0;
	}
}
