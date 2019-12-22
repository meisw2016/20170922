package cn.springcloud.meisw.jpa.controller;

import io.swagger.annotations.Api;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.common.OutputData;

/**
 * controller测试类
 * @author Administrator
 *
 */
@Api(value = "/test",description = "测试类")
@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("/cpu")
	public OutputData test1(){
		OutputData out = new OutputData().returnSuccess();
		Map<String,String> result = System.getenv();
		out.setData(result);
		return out;
	}
}
