package cn.springcloud.book.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/test")
@Api(value = "kibana嵌入测试",description = "登录验证")
public class LoginTest {
	
	private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
	
	@RequestMapping("/login")
	@ApiOperation(value = "登录验证",notes = "登录验证")
	public String login(@ApiParam(name = "username",value = "用户名",required = true) @RequestParam(value = "username", required = true)String username,
			@ApiParam(name = "password",value = "密码",required = true) @RequestParam(value = "password",required = true)String password) {
		log.info("登录验证接口入参：username={},password={}",username,password);
		String result = "用户："+username+",密码："+password;
		return result;
		
	}
	
}