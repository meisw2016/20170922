package cn.springcloud.book.eureka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Bean
	public Docket createRestApi() {
		
		ParameterBuilder ticketParm = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		ticketParm.name("Cookie").description("Cookie")
		.modelRef(new ModelRef("String")).parameterType("header")
		.required(false).build();
		pars.add(ticketParm.build());
		
		
		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.build()
				.globalOperationParameters(pars)
				.apiInfo(apiInfo());
//				.paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("xxxx使用swagger2测试")
				.version("11.13.45")
				.description("API描述信息").build();
	}
}
