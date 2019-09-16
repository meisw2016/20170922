package cn.springcloud.book.eureka.automation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.book.eureka.automation.service.BoltdogExecuteService;

@RestController
@RequestMapping(value = "/automation")
//@Api(value = "/automation",description="编排接口")
@ConfigurationProperties(prefix="config",ignoreUnknownFields = false)
@PropertySource("classpath:config.properties")
@Configuration
public class BoltdogExecuteController {
	
	@Autowired
	private BoltdogExecuteService boltdogExecuteService;
	
	@Value("${boltdogExecute.url}")
	private String url;
	
	@Value("${boltdogExecute.id}")
	private String id;
	
	@Value("${boltdogExecute.apikey}")
	private String apikey;
	
//	public OutputData execute(HttpServletRequest request) {
//		OutputData out = new OutputData().returnSuccess();
//		Map<String,String> headers = new HashMap<String,String>();
//		headers.put("Content-Type", "application/json");
//		
//		JSONObject json = new JSONObject();
//		json.put("objectId", "aaaaaaa");
//		json.put("class","qq");
//		json.put("interval", 60);
//		JsonArray execGoal = new JSONArray();
//		JSONObject obj = new JSONObject();
//		obj.put("type", "id");
//		obj.put("value", "safsdlfskdfleifsdfsdlf");
//		execGoal.add(obj);
//		json.put("_execGoal", execGoal);
//		try {
//			out = boltdogExecuteService.execute(request, url, headers, body);
//		}catch(LogException e) {
//			out.returnFail(e.getMessage());
//		}
//		return out;
//	}
}
