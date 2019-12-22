package cn.springcloud.meisw.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.springcloud.meisw.eureka.domain.StudentConfig;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private StudentConfig studentConfig;
	
	@RequestMapping("test1")
	public String test1(){
		return JSONObject.toJSONString(studentConfig);
	}
}
