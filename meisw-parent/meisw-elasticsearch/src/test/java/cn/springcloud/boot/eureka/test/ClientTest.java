package cn.springcloud.boot.eureka.test;


import com.alibaba.fastjson.JSONObject;

import cn.springcloud.book.eureka.util.HttpClientUtils;

public class ClientTest {
	public static void main(String[] args) {
		String url = "http://192.168.254.131:5601/api/security/v1/login";
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("username", "elastic");
		jsonParam.put("password", "changeme");
		JSONObject result = HttpClientUtils.httpPost(url, jsonParam);
		System.out.println(result);
	}
}
