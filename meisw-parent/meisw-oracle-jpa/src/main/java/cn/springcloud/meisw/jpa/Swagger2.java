package cn.springcloud.meisw.jpa;

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
		/**添加cookie请求头信息*/
		ParameterBuilder cookie = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		cookie.name("cookies").description("cookies").modelRef(new ModelRef("String")).parameterType("header").required(false).build();
		pars.add(cookie.build());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.springcloud.meisw.jpa"))
				.paths(PathSelectors.any()).build().globalOperationParameters(pars);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("meisw 测试专用Swagger2构建RESTful API")
				.version("1.0")
				.description("API 描述").build();

	}
}
