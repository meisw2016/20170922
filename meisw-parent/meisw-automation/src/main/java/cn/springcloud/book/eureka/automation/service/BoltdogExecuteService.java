package cn.springcloud.book.eureka.automation.service;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.springcloud.book.eureka.automation.util.HttpClientUtil;
import cn.springcloud.book.eureka.automation.util.LogException;
import cn.springcloud.book.eureka.automation.util.OutputData;

@Service
public class BoltdogExecuteService {
	
	private static final Logger log = LoggerFactory.getLogger(BoltdogExecuteService.class);
	
	/**
	 * 
	 * @author meisw 2019年9月16日 下午5:09:08
	 * @Method: execute 
	 * @Description: 编排接口请求服务
	 * @param request
	 * @param url
	 * @param headers
	 * @param body
	 * @return
	 * @throws LogException 
	 * @throws
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public OutputData execute(HttpServletRequest request,String url,Map<String,String> headers,String body) throws LogException{
		OutputData out = new OutputData().returnSuccess();
		String result = null;
		log.info("request:{}",request);
		//获取token
		String token = getToken(request);
		headers.put("token", token);
		result = HttpClientUtil.doPostMap(url, headers, body);
		if(StringUtils.isEmpty(result)) {
			out.returnFail();
			out.setMessage("获取编排接口请求服务信息为null");
		}
		out.setData(result);
		return out;
	}
	
	/**
	 * 
	 * @author meisw 2019年9月16日 下午5:06:16
	 * @Method: getToken 
	 * @Description: 获取token
	 * @param request
	 * @return 
	 * @throws
	 */
	private String getToken(HttpServletRequest request) {
		log.debug("获取token信息");
		String token = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				log.debug("cookie名称：{}",cookie.getName());
				if("token".equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}
		log.info("获取token信息：{}",token);
		return token;
	}
}
