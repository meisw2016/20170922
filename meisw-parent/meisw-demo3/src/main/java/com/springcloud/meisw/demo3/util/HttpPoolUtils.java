package com.springcloud.meisw.demo3.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpPoolUtils {
	
	private final static Logger LOG = LoggerFactory.getLogger(HttpPoolUtils.class);
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;
	/** 默认UA */
	private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36";
	/** 默认编码 */
	public static final String DEFAULT_ENCODING = "UTF-8";
	/** 最大连接数 */
	public final static int MAX_TOTAL_CONNECTIONS = 5;
	/** 每个路由最大连接数 */
	public final static int MAX_PER_ROUTE = 2;
	/** 连接超时时间 */
	public static final int CONNECT_TIMEOUT = 50000;
	/** 等待数据超时时间 */
	public static final int SO_TIMEOUT = 20000;
	/** 连接池连接不足超时等待时间 */
	public static final int CONN_MANAGER_TIMEOUT = 500;
	
	private static final RequestConfig DEFAULT_REQUESTCONFIG = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT)
	        .setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(CONN_MANAGER_TIMEOUT)
	        .setExpectContinueEnabled(false).build();
	
	static {
		try {
			SSLContext sslContext = SSLContexts.custom().build();
			// TODO 如有需要自行添加相关证书 sslContext.init...
			
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
			        .register("http", PlainConnectionSocketFactory.INSTANCE)
			        .register("https", new SSLConnectionSocketFactory(sslContext)).build();
			
			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			httpclient = HttpClients.custom().setConnectionManager(connManager)
			        .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
			        .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
			        .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy()).setUserAgent(DEFAULT_USER_AGENT).build();
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
			connManager.setDefaultSocketConfig(socketConfig);
			MessageConstraints messageConstraints = MessageConstraints.custom().build();
			ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
			        .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
			        .setMessageConstraints(messageConstraints).build();
			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
			connManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
		} catch (Exception e) {
			LOG.error("some error is init, please check it.", e);
		}
	}
	
	/**
	 * post请求
	 *
	 * @param url 请求URL
	 * @param params 参数
	 * @param contentType 格式
	 * @param userAgent UA
	 * @param encoding 编码
	 * @return
	 */
	public static String post(String url, Map<String, String> params, String contentType, String userAgent, String encoding) {
		String data = "";
		HttpPost httpPost = new HttpPost();
		CloseableHttpResponse response = null;
		try {
			httpPost.setURI(new URI(url));
			if (contentType != null && contentType != "") {
				httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
			} else {
				httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
			}
			if (userAgent != null && userAgent != "") {
				httpPost.setHeader(HttpHeaders.USER_AGENT, userAgent);
			}
			RequestConfig requestConfig = RequestConfig.copy(DEFAULT_REQUESTCONFIG).build();
			httpPost.setConfig(requestConfig);
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				for (Entry<String, String> entry : params.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			// LOG.debug(String.format("[HttpPoolUtils Post] begin invoke url:
			// %s , params: %s",url,params.toString()));
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				data = EntityUtils.toString(entity, encoding);
				// LOG.debug(String.format("[HttpPoolUtils Post]Debug response,
				// url :%s , response string :%s",url,data));
			}
		} catch (Exception e) {
			LOG.error("[HttpPoolUtils Post] is error. ", e);
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpPost.reset();
		}
		return data;
	}
	
	/**
	 * Get请求方式
	 *
	 * @param url 请求URL
	 * @param params 参数
	 * @param contentType 格式
	 * @param userAgent UA
	 * @param encoding 编码
	 * @return
	 */
	public static String get(String url, Map<String, String> params, String contentType, String userAgent, String encoding) {
		String data = "";
		HttpGet httpGet = new HttpGet();
		CloseableHttpResponse response = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			boolean first = true;
			if (params != null) {
				for (Entry<String, String> entry : params.entrySet()) {
					if (first && !url.contains("?")) {
						sb.append("?");
					} else {
						sb.append("&");
					}
					sb.append(entry.getKey());
					sb.append("=");
					String value = entry.getValue();
					sb.append(URLEncoder.encode(value, "UTF-8"));
					first = false;
				}
			}
			
			// LOG.info("[HttpPoolUtils Get] begin invoke:" + sb.toString());
			httpGet.setURI(new URI(sb.toString()));
			RequestConfig requestConfig = RequestConfig.copy(DEFAULT_REQUESTCONFIG).build();
			httpGet.setConfig(requestConfig);
			if (contentType != null && contentType != "") {
				httpGet.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
			} else {
				httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
			}
			if (userAgent != null && userAgent != "") {
				httpGet.setHeader(HttpHeaders.USER_AGENT, userAgent);
			}
			
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				data = EntityUtils.toString(entity, encoding);
			}
			// LOG.debug(String.format("[HttpPoolUtils Get]Debug url:%s ,
			// response data %s:",sb.toString(),data));
		} catch (Exception e) {
			LOG.error(String.format("[HttpPoolUtils Get]invoke get error, url:%s, para:%s", url, params.toString()), e);
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpGet.reset();
		}
		return data;
	}
	
	public static void main0(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			long startPer = System.currentTimeMillis();
			String url = "https://www.baidu.com/s?wd=" + r.nextInt(5000);
			String data = get(url, Collections.<String, String> emptyMap(), null, null, HttpPoolUtils.DEFAULT_ENCODING);
			System.out.println("结果长度:" + data.length());
			System.out.println("单次请求耗时ms:" + (System.currentTimeMillis() - startPer));
		}
		System.out.println("查询总耗时ms:" + (System.currentTimeMillis() - start));
	}
	
	public static void main1(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		String url = "http://samworld.samonkey.com/v2_0/media/detail/343";
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", "F60D72944B9E236E7C7D219851DB5C62");
		params.put("deviceType", "IOS");
		params.put("version", "v3.1.3");
		String userAgent = "VRStore/3.1.3 (iPhone; iOS 9.3.2; Scale/3.00)";
		String contentType = "application/json;charset=UTF-8";
		String acceptEncoding = "gzip, deflate";
		String encoding = "UTF-8";
		// String data = HttpPoolUtils.get(url, params, "application/json",
		// userAgent, "UTF-8");
		String data = "";
		HttpGet httpGet = new HttpGet();
		CloseableHttpResponse response = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			boolean first = true;
			if (params != null) {
				for (Entry<String, String> entry : params.entrySet()) {
					if (first && !url.contains("?")) {
						sb.append("?");
					} else {
						sb.append("&");
					}
					sb.append(entry.getKey());
					sb.append("=");
					String value = entry.getValue();
					sb.append(URLEncoder.encode(value, "UTF-8"));
					first = false;
				}
			}
			
			// LOG.info("[HttpPoolUtils Get] begin invoke:" + sb.toString());
			httpGet.setURI(new URI(sb.toString()));
			RequestConfig requestConfig = RequestConfig.copy(DEFAULT_REQUESTCONFIG).build();
			httpGet.setConfig(requestConfig);
			if (contentType != null && contentType != "") {
				httpGet.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
			} else {
				httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
			}
			if (userAgent != null && userAgent != "") {
				httpGet.setHeader(HttpHeaders.USER_AGENT, userAgent);
			}
			if (acceptEncoding != null && acceptEncoding != "") {
				httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, acceptEncoding);
			}
			httpGet.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-Hans-CN;q=1, pl-PL;q=0.9");
			httpGet.setHeader(HttpHeaders.ACCEPT, "*/*");
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				data = EntityUtils.toString(entity, encoding);
			}
			// LOG.debug(String.format("[HttpPoolUtils Get]Debug url:%s ,
			// response data %s:",sb.toString(),data));
		} catch (Exception e) {
			LOG.error(String.format("[HttpPoolUtils Get]invoke get error, url:%s, para:%s", url, params.toString()), e);
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpGet.reset();
		}
		
		System.out.println(data);
		System.out.println("查询总耗时ms:" + (System.currentTimeMillis() - start));
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> param = new HashMap();
		param.put("access_token", "70603C5A83DDC1507B00AC52E55C13EA");
		param.put("unionid", "1");
		String result = HttpPoolUtils
		        .get("https://graph.qq.com/oauth2.0/me", param, null, null, HttpPoolUtils.DEFAULT_ENCODING);
		System.out.println(result);
		
		long start = System.currentTimeMillis();
		long startPer = System.currentTimeMillis();
		String url = "http://nextjoy.cn/programMan/list_demo.php";
		String data = get(url, Collections.<String, String> emptyMap(), null, null, HttpPoolUtils.DEFAULT_ENCODING);
		System.out.println("结果长度:" + data.length());
		System.out.println("Data:" + data);
		System.out.println("单次请求耗时ms:" + (System.currentTimeMillis() - startPer));
		System.out.println("查询总耗时ms:" + (System.currentTimeMillis() - start));
	}
}
