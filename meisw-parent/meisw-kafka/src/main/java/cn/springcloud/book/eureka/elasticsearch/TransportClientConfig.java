package cn.springcloud.book.eureka.elasticsearch;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class TransportClientConfig {
	
	private static final Logger log = LoggerFactory.getLogger(TransportClientConfig.class);
	
	@Value("${elasticsearch.host}")
	private String host;
	
	@Value("${elasticsearch.cluster-name}")
	private String cluster;
	
	/*public TransportClient client() {
		try {
			Settings settings = Settings.builder().put
		}catch(UnknownHostException e) {
			log.error("异常：{}",e);
		}
	}*/
}
