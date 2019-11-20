package cn.springcloud.book.eureka.test;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.book.eureka.security.IompUser;
import cn.springcloud.book.eureka.service.KibanaOperateService;
import cn.springcloud.book.eureka.util.IompSecurityUtil;

@RestController
@RequestMapping("/test")
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TransportClient client;
	
	@Autowired
	private KibanaOperateService kibanaOperateService;
	
	@RequestMapping("/add")
	public ResponseEntity add(String id,String name,String age,String work) {
		try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("name", name)
                    .field("age", age)
                    .field("work", work)
                    .endObject();
            IndexResponse result = client.prepareIndex("data", "person",id).setSource(content).get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	public String handleReqquest(HttpServletRequest request,HttpServletResponse response) {
		//获取当前登录用户信息
		IompUser iompUser = IompSecurityUtil.getCurrentUserInfo();
		if(iompUser == null) {
			log.warn("未获取到当前登录用户信息");
			return "未获取到当前登录用户信息";
		}
		this.kibanaOperateService.permissionConsistent(iompUser);
		try {
			response.sendRedirect("http://");
		}catch(IOException e) {
			log.error("跳转kibana登录页面异常：{}",e.getMessage());
		}
		return "";
	}
}
