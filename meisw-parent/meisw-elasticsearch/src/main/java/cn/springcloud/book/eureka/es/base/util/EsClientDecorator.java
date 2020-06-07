package cn.springcloud.book.eureka.es.base.util;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class EsClientDecorator implements InitializingBean,DisposableBean{

	private static final Logger log = LoggerFactory.getLogger(EsClientDecorator.class);
	
	private RestHighLevelClient restClient;
	private HttpHost[] httpHosts;
	private Integer esRestClientTimeout = 120000;//默认超时时间2分钟
	
	public EsClientDecorator(HttpHost[] httpHosts,Integer esRestClientTimeout){
		this.httpHosts = httpHosts;
		if(esRestClientTimeout != null){
			this.esRestClientTimeout = esRestClientTimeout;
			log.info("esRestClientTimeout esRestClient连接es集群的超时时间为："+this.esRestClientTimeout);
		}
	}
	
	private RestHighLevelClient createRestHighLevelClient(){
		if(restClient != null){
			try{
				restClient.close();
			}catch(IOException e){
				log.error("Es7 restClient关闭连接出错");
			}
		}
		if(httpHosts!=null && httpHosts.length>0){
			restClient = new RestHighLevelClient(RestClient.builder(httpHosts)
					.setHttpClientConfigCallback(new HttpClientConfigCallback(){

						@Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClient) {
	                        RequestConfig.Builder resConfigBuilder = RequestConfig.custom()
	                        		.setConnectTimeout(esRestClientTimeout)
	                        		.setSocketTimeout(esRestClientTimeout)
	                        		.setConnectionRequestTimeout(esRestClientTimeout);
	                        httpClient.setDefaultRequestConfig(resConfigBuilder.build());
	                        return httpClient;
                        }
						
					})
					);
		}
		return restClient;
		
	}
	
	@Override
    public void destroy() throws Exception {
		restClient.close();
    }

	@Override
    public void afterPropertiesSet() throws Exception {
		restClient = new RestHighLevelClient(RestClient.builder(httpHosts));
    }
	
}
