package cn.springcloud.meisw.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.springcloud.meisw.eureka.domain.StudentConfig;

@RefreshScope
@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private StudentConfig studentConfig;
	
	@Value(value = "${kline.host}")
	private String kLineHost;
	
	@RequestMapping("test1")
	public String test1(){
		return JSONObject.toJSONString(studentConfig);
	}
	
	@GetMapping(value = "test2")
	public String test2() {
		return kLineHost;
	}
}
