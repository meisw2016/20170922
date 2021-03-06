package cn.springcloud.book.eureka.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class RequestTools {
	public static String processHttpRequest(String url, String requestMethod, Map<String, String> paramsMap) {
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		if ("post".equals(requestMethod)) {
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "application/json");
			for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				String value = paramsMap.get(key);
				formparams.add(new BasicNameValuePair(key, value));
			}
			return doRequest(httppost, null, formparams);
		} else if ("get".equals(requestMethod)) {
			HttpGet httppost = new HttpGet(url);
			for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				String value = paramsMap.get(key);
				formparams.add(new BasicNameValuePair(key, value));
			}
			return doRequest(null, httppost, formparams);
		}
		return "";
	}

	private static String doRequest(HttpPost httpPost, HttpGet httpGet, List<BasicNameValuePair> formparams) {
		try {
			CloseableHttpResponse response = null;
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams); // 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(25000).setConnectTimeout(3000)
					.build();
			if (null != httpPost) {
				uefEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
				httpPost.setEntity(uefEntity);
				httpPost.setConfig(requestConfig);
				response = HttpClientPoolUtil.getHttpClient().execute(httpPost);
			} else {
				httpGet.setConfig(requestConfig);
				response = HttpClientPoolUtil.getHttpClient().execute(httpGet);
			}
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			if (null == str || "".equals(str)) {
				return "";
			} else {
				return str;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return "";
	}

	public static String processPostJson(String postUrl, JSONObject jsonObj) throws ClientProtocolException, Exception {
		HttpPost post = new HttpPost(postUrl);
		post.setHeader("Content-Type", "application/json");
//		post.addHeader("Authorization", "Basic YWRtaW46");
		String str = null;
		StringEntity s = new StringEntity(jsonObj.toJSONString(), "utf-8");
		s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(25000).setConnectTimeout(3000).build();

		post.setEntity(s);
		post.setConfig(requestConfig);

		CloseableHttpResponse response = HttpClientPoolUtil.getHttpClient().execute(post);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instreams = entity.getContent();
			str = convertStreamToString(instreams);
			post.abort();
		}
		return str;
	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
