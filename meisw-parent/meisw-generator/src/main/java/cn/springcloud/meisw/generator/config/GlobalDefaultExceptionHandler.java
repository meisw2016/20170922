package cn.springcloud.meisw.generator.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springcloud.meisw.generator.util.ApiReturnObject;
import cn.springcloud.meisw.generator.util.ApiReturnUtil;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ApiReturnObject defaultExceptionHandler(HttpServletRequest req,Exception e) {
		e.printStackTrace();
		return ApiReturnUtil.error("服务器异常",e.getMessage());
	}
	
}
