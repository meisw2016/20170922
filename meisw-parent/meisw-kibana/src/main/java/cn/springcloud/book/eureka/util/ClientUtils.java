package cn.springcloud.book.eureka.util;

import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ClientUtils {
	
	private static ClientUtils clientUtils;
	
	public static ClientUtils getInstance() {
		if (clientUtils == null) {
			clientUtils = new ClientUtils();
		}
		return clientUtils;
	}
	
	public RestHighLevelClient getClient() {
		// 凭证注册器
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
		        RestClient.builder(new HttpHost("192.168.254.131", 9200, "http"))
		                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
			                @Override
			                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				                httpClientBuilder.disableAuthCaching();
				                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
			                }
		                }));
		return restHighLevelClient;
	}
}
