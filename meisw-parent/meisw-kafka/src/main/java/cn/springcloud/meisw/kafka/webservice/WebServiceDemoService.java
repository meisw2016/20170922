package cn.springcloud.meisw.kafka.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @author Administrator
 *
 */
@WebService(targetNamespace = "http://webservice.kafka.meisw.springcloud.cn/")
public interface WebServiceDemoService {
	
	@WebMethod
	public String hello(@WebParam(name="name")String name);
}
