package cn.springcloud.book.eureka.automation.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String doPostMap2(String url,Map<String,String> hreaders,String body) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> content = new ArrayList<NameValuePair>();
			Iterator iterator = content.iterator();
			while(iterator.hasNext()) {
				Entry<String,String> elem = (Entry<String, String>)iterator.next();
				content.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			
			httpPost.setEntity(new StringEntity(body));
			
			response = httpClient.execute(httpPost);
			if(response!=null && response.getStatusLine().getStatusCode()==200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity,"UTF-8");
				log.debug("result:{}",result);
				return result;
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				httpClient.close();
				if(response!=null) {
					response.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
