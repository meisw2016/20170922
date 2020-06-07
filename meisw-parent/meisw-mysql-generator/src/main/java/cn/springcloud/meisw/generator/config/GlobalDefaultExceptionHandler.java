package cn.springcloud.meisw.generator.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springcloud.meisw.generator.util.ApiReturnObject;
import cn.springcloud.meisw.generator.util.ApiReturnUtil;
import cn.springcloud.meisw.generator.util.OutputData;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	/*@ExceptionHandler(Exception.class)
	@ResponseBody
	public ApiReturnObject defaultExceptionHandler(HttpServletRequest req, Exception e) {
		e.printStackTrace();
		// return new ApiReturnObject("01","server error", e.getMessage());
		return ApiReturnUtil.error("服务器异常", e.getMessage());
	}*/
	
	@ExceptionHandler(Exception.class)
	public OutputData defaultExceptionHandler(HttpServletRequest req,Exception e){
		OutputData out = new OutputData().returnFail();
		e.printStackTrace();
		out.setMessage(e.getMessage());
		return out;
	}
	
}
