package cn.springcloud.book.eureka.swaggerControllTest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.book.eureka.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/test")
@Api(value = "/test",description = "api测试")
public class SwaggerControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(SwaggerControllerTest.class);
	
	@RequestMapping(value = "/abc",method = RequestMethod.POST)
	@ApiOperation(value = "api测试",httpMethod = "POST",notes = "api测试")
	public Map<String,String> test(HttpServletRequest request,
			@ApiParam(name = "id",value = "主键",required = false)@RequestParam(value = "id",required = false)String id
			) {
		log.info("测试入参：{}",id);
		log.info("获取Cookie信息：");
		Map<String,String> result = TokenUtil.getToken(request);
		return result;
	}
}
