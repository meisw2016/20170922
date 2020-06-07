//package cn.springcloud.book.eureka.controller;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import cn.springcloud.book.eureka.test.Test1;
//import cn.springcloud.book.eureka.util.HttpClient;
//
//@Controller
//@RequestMapping("/kibana")
//public class KibanaManagerController {
//	
//	private static final Logger log = LoggerFactory.getLogger(KibanaManagerController.class);
//	
//	@RequestMapping("/login")
//	private String toLoginKibana(HttpServletRequest request,HttpServletResponse response) {
//		log.info("============== 开始登录kibana ... ============================");
////		try {
//			//response.sendRedirect("http://192.168.254.131:5601/login");
////			HttpClient http = new HttpClient(response);
////			http.setParameter("username", "elastic");
////			http.setParameter("password", "changeme");
////			http.sendByPost("http://192.168.254.131:5601/api/security/v1/login");
////			response.sendRedirect("http://192.168.254.131:5601/login");
//			//http.sendByPost("http://192.168.254.131:5601/api/xpack/v1/info");
//			
//			String result = Test1.postMap("http://192.168.253.131:5601/login");
////		} catch (IOException e) {
////			log.error("登录kibana服务异常：errorMessage={}",e);
////		}
//		log.info("");
//		return null;
//	}
//}
