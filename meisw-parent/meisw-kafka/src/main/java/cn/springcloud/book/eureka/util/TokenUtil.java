package cn.springcloud.book.eureka.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class TokenUtil {
	
	private static final Logger log = LoggerFactory.getLogger(TokenUtil.class);
	
	private TokenUtil() {}
	
	/**
	 * 
	 * @author meisw 2019年9月23日 下午4:50:26
	 * @Method: getToken 
	 * @Description: 获取token及Cookie信息
	 * @param request
	 * @return 
	 * @throws
	 */
	public static Map<String,String>  getToken(HttpServletRequest request) {
		log.info("获取cookie信息");
		Map<String,String> result = new HashMap<String,String>();
		JsonObject json = new JsonObject();
		String token = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				log.debug("cookie名称为：{}",cookie.getName());
				if("token".equals(cookie.getName())) {
					token = cookie.getValue();
					result.put("token", token);
				}
				json.addProperty(cookie.getName(), cookie.getValue());
				result.put("Cookie", json.toString());
			}
			log.info("Cookie信息：{}",json.toString());
		}
		return result;
	}
}
