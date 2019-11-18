package cn.springcloud.meisw.generator.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年11月18日 下午2:36:28
 * @ClassName WebMvcConfig
 * @Description WebMvcConfig 整合 cors 和 SpringMvc MessageConverter
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// FastJsonHttpMessageConverter
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		fastConverter.setFastJsonConfig(fastJsonConfig);
		
		// StringHttpMessageConverter
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
		stringConverter.setSupportedMediaTypes(fastMediaTypes);
		converters.add(stringConverter);
		converters.add(fastConverter);
	}
}
