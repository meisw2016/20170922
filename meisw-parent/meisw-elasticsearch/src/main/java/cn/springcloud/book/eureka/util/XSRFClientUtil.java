package cn.springcloud.book.eureka.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

public class XSRFClientUtil {
	
	public static String getToken(HttpServletRequest request,String url) throws Exception {
		String code = "1111";
		String token = "";
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpget.setHeader("Authorization", code);
		httpget.setHeader("x-csrf-token", "fetch");
		Header headers[] = httpget.getAllHeaders();
		for(Header h:headers) {
			if("set-cookie".equals(h.getName())) {
				token = token + h.getValue()+";";
			}
			if("x-csrf-token".equals(h.getName())) {
				token = token+":"+h.getValue();
			}
		}
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		HttpResponse res = httpClient.execute(httpget);
		
		return token;
	}
	
	public static String getResult() {
		return null;
	}
}
