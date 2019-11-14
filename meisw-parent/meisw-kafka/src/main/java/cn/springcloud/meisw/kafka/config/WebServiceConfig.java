package cn.springcloud.meisw.kafka.config;


import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.springcloud.meisw.kafka.webservice.WebServiceDemoService;

@Configuration
public class WebServiceConfig {
	
	@Autowired
	private WebServiceDemoService webServiceDemoService;
	
	@Bean(name = "cxfServlet")
	public ServletRegistrationBean cxfServlet(){
		return new ServletRegistrationBean(new CXFServlet(),"/webservice/*");
	}
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus(){
		return new SpringBus();
	}
	
	@Bean(name = "WebServiceDemoEndpoint")
	public Endpoint swepPayEndpoint(){
		EndpointImpl endpoint = new EndpointImpl(springBus(),webServiceDemoService);
		endpoint.publish("/webservice");
		return endpoint;
	}
}
