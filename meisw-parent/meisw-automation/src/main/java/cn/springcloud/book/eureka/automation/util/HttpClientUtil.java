package cn.springcloud.book.eureka.automation.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
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
	
	/**
	 * 
	 * @author meisw 2019年9月17日 下午4:57:27
	 * @Method: getUrkParams 
	 * @Description: 将url参数转换成map
	 * @param param	aa=11&bb=22&cc=33
	 * @return 
	 * @throws
	 */
	public static Map<String,Object> getUrkParams(String param){
		Map<String,Object> map = new HashMap<String,Object>(0);
		if(StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for(int i=0;i<params.length;i++) {
			String[] p = params[i].split("=");
			if(p.length==2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @author meisw 2019年9月17日 下午5:00:55
	 * @Method: getUrlParamByMap 
	 * @Description: 将map转换成url
	 * @param map
	 * @return 
	 * @throws
	 */
	public static String getUrlParamByMap(Map<String,Object> map) {
		if(map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey()+"="+entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if(s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}
	
	public static String sendPostJsonParam2(String url,Map<String,String> headers,Map<String,Object> params,String body) {
		String result = "";
		if(params!=null) {
			url = url + "?"+getUrlParamByMap(params);
		}
		OutputStreamWriter out = null;
		InputStream is = null;
		try {
			URL uRL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)uRL.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");//设置请求方式
			connection.setRequestProperty("Accept", "application/json");//设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json");//设置发送数据的格式
			connection.connect();
			out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			out.flush();
			out.close();
			
			//获取状态码
			int code = connection.getResponseCode();
			//读取响应
			if(code == 200) {
				is = connection.getInputStream();
			}else {
				is = connection.getErrorStream();
			}
			int length = connection.getContentLength();//获取长度
			if(length!=-1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while((readLen = is.read(temp))>0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				result = new String(data,"UTF-8");
				log.info("请求http返回结果：{}",result);
			}
		}catch(Exception e) {
			log.error("http请求{}异常{}",url,e);
		}finally {
			try {
				is.close();
				out.close();
			}catch(IOException e) {
				log.error("",e);
			}
		}
		return result;
	}
	
	private static URIBuilder paramsConvert(URIBuilder uriBuilder,Map<String,String> params) {
		if(params!=null) {
			List<NameValuePair> pList = new ArrayList<NameValuePair>();
			Iterator<Entry<String,String>> par = params.entrySet().iterator();
			while(par.hasNext()) {
				Entry<String,String> elem = par.next();
				pList.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			uriBuilder.setParameters(pList);
		}
		return uriBuilder;
	}
	
	private static HttpPost headers2Convert(HttpPost httpPost,Map<String,String> headers) {
		Iterator<Entry<String,String>> parEntry = headers.entrySet().iterator();
		while(parEntry.hasNext()) {
			Entry<String,String> elem = parEntry.next();
			httpPost.addHeader(elem.getKey(),elem.getValue());
		}
		return httpPost;
	}
	
	private static HttpGet headers2Convert(HttpGet httpGet,Map<String,String> headers) {
		Iterator<Entry<String,String>> iteratorHeader = headers.entrySet().iterator();
		while(iteratorHeader.hasNext()) {
			Entry<String,String> h = iteratorHeader.next();
			httpGet.setHeader(h.getKey(),h.getValue());
		}
		return httpGet;
	}
	
	private static HttpPost body2Convert(HttpPost httpPost,String body) {
		if(!StringUtils.isEmpty(body)) {
			StringEntity stringEntity = new StringEntity(body,Consts.UTF_8);
			httpPost.setEntity(stringEntity);
		}
		return httpPost;
	}
	
	private static String getResult(HttpPost httpPost,CloseableHttpResponse response,CloseableHttpClient httpClient) {
		String result = "";
		try {
			response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			log.info("http请求返回状态码：{}",code);
			if(response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity,"UTF-8");
			}else {
				log.error("出错了!{},{}",response.getStatusLine().getReasonPhrase(),EntityUtils.toString(response.getEntity()));
				result = response.getStatusLine()+","+EntityUtils.toString(response.getEntity());
			}
		}catch(ClientProtocolException e) {
			log.error("ClientProtocolException:{}",e);
		}catch(IOException e) {
			log.error("IOException:{}",e);
		}
		return result;
		
	}
	
	/**
	 * 
	 * @author meisw 2019年10月16日 上午8:23:33
	 * @Method: sendPostJsonParam 
	 * @Description: HTTP发送POST请求
	 * @param url	请求地址
	 * @param headers	请求头	
	 * @param params	请求参数
	 * @param body		请求体
	 * @return 
	 * @throws
	 */
	public static String sendPostJsonParam(String url,Map<String,String> headers,Map<String,String> params,String body) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			uriBuilder = paramsConvert(uriBuilder, params);
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost = headers2Convert(httpPost, headers);
			httpPost = body2Convert(httpPost, body);
			result = getResult(httpPost, response, httpClient);
		}catch(URISyntaxException e) {
			log.error("URISyntaxException:{}",e);
		}finally {
			try {
				httpClient.close();
				if(response!= null) {
					response.close();
				}
			}catch(IOException e) {
					e.printStackTrace();
				}
			
		}
		log.error("HTTP请求uyun编排认为结果：{}",result);
		return result;
	}
	
	/**
	 * 
	 * @author meisw 2019年10月16日 上午8:41:54
	 * @Method: doGetParams 
	 * @Description: get请求
	 * @param url
	 * @param headers
	 * @param params
	 * @return 
	 * @throws
	 */
	public static String doGetParams(String url,Map<String,String> headers,Map<String,String> params) {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = null;
		HttpResponse response = null;
		String result = "";
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			uriBuilder = paramsConvert(uriBuilder, params);
			httpGet = new HttpGet(uriBuilder.build());
			httpGet = headers2Convert(httpGet, headers);
			response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity,"UTF-8");
		} catch (URISyntaxException e) {
			log.error("URISyntaxException:{}",e);
		} catch (ClientProtocolException e) {
			log.error("ClientProtocalException:{}",e);
		} catch (IOException e) {
			log.error("IOException:{}",e);
		}
		return result;
	}
}
