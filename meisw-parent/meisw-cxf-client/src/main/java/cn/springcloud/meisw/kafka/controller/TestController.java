package cn.springcloud.meisw.kafka.controller;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/cxfTest")
	public String cxfTest(String name){
		// 创建动态客户端
	    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
	    Client client = dcf.createClient("http://127.0.0.1:24151/webservice/webservice?wsdl");
	    // 需要密码的情况需要加上用户名和密码
	    // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
	    Object[] objects = new Object[0];
	    String result = "";
	    try {
	        // invoke("方法名",参数1,参数2,参数3....);
	        objects = client.invoke("hello", name);
	        System.out.println("返回数据:" + objects[0]);
	        result = (String)objects[0];
	    } catch (java.lang.Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
