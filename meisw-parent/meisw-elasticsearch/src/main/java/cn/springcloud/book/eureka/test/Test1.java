package cn.springcloud.book.eureka.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test1 {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		/*
		 * HttpHost targetHost = new HttpHost("192.168.254.131", 5601, "http"); // CredentialsProvider credsProvider = new BasicCredentialsProvider(); //
		 * credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "changeme")); // AuthCache authCache = new BasicAuthCache();
		 * // authCache.put(targetHost, new BasicScheme()); // final HttpClientContext context = HttpClientContext.create(); //
		 * context.setCredentialsProvider(credsProvider); // context.setAuthCache(authCache); // // HttpClient client = HttpClientBuilder.create().build(); //
		 * response = client.execute( // new HttpGet(URL_SECURED_BY_BASIC_AUTHENTICATION), context); // // int statusCode =
		 * response.getStatusLine().getStatusCode(); // assertThat(statusCode, equalTo(HttpStatus.SC_OK)); CredentialsProvider provider = new
		 * BasicCredentialsProvider(); UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("elastic", "changeme"); AuthCache authCache =
		 * new BasicAuthCache(); authCache.put(targetHost, new BasicScheme()); // provider.setCredentials(authCache, credentials); AuthScope auth = new
		 * AuthScope("192.168.254.131", 5601, "http"); provider.setCredentials(auth, credentials); HttpClient client =
		 * HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build(); HttpResponse response = client.execute(new HttpGet("Basic")); int
		 * statusCode = response.getStatusLine().getStatusCode(); System.out.println("result=" + statusCode);
		 */
//		String result = postMap("http://192.168.254.131:5601/api/security/v1/login");
		String result = postMap("http://192.168.254.131:5601/login");
		System.out.println("result:"+result);
	}
	
	public static String postMap(String url) {
		Map<String, String> headerMap = new HashMap<String,String>();
		headerMap.put("Authorization", "Basic");
		Map<String, String> contentMap = new HashMap<String,String>();
		contentMap.put("username", "elastic");
		contentMap.put("password", "changeme");
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> content = new ArrayList<NameValuePair>();
		Iterator iterator = contentMap.entrySet().iterator(); // 将content生成entity
		while (iterator.hasNext()) {
			Entry<String, String> elem = (Entry<String, String>)iterator.next();
			content.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
		}
		CloseableHttpResponse response = null;
		try {
			Iterator headerIterator = headerMap.entrySet().iterator(); // 循环增加header
			while (headerIterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>)headerIterator.next();
				post.addHeader(elem.getKey(), elem.getValue());
			}
			if (content.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, "UTF-8");
				post.setEntity(entity);
			}
			response = httpClient.execute(post); // 发送请求并接收返回数据
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity(); // 获取response的body部分
				result = EntityUtils.toString(entity); // 读取reponse的body部分并转化成字符串
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
}
