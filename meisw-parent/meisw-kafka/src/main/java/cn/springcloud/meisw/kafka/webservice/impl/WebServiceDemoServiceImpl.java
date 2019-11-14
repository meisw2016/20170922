package cn.springcloud.meisw.kafka.webservice.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import cn.springcloud.meisw.kafka.webservice.WebServiceDemoService;

@Service
@WebService(serviceName = "WebServiceDemoService",
		targetNamespace = "http://webservice.kafka.meisw.springcloud.cn/",
		endpointInterface = "cn.springcloud.meisw.kafka.webservice.WebServiceDemoService")
public class WebServiceDemoServiceImpl implements WebServiceDemoService {
	
	@Override
	public String hello(String name) {
		return "hello "+name;
	}
	
}
