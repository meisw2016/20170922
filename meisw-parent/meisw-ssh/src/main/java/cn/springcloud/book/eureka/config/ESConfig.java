//package cn.springcloud.book.eureka.config;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 
// * <p></p>
// * @author meisw 2019年7月1日 下午2:09:09
// * @ClassName ESConfig
// * @Description 配置ElasticSearch源
// * @version V1.0   
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: {修改人} 2019年7月1日
// * @modify by reason:{方法名}:{原因}
// */
//@Configuration
//public class ESConfig {
//	
//	private static final Logger log = LoggerFactory.getLogger(ESConfig.class);
//	
//	 @Bean
//	    public TransportClient client() throws UnknownHostException {
//	        // 9300是es的tcp服务端口
//	        InetSocketTransportAddress node = new InetSocketTransportAddress(
//	                InetAddress.getByName("192.168.190.129"),
//	                9300);
//
//	        // 设置es节点的配置信息
//	        Settings settings = Settings.builder()
//	                .put("cluster.name", "es")
//	                .build();
//
//	        // 实例化es的客户端对象
//	        TransportClient client = new PreBuiltTransportClient(settings);
//	        client.addTransportAddress(node);
//
//	        return client;
//	    }
//	
////	/**
////	 * elk集群地址
////	 */
////	@Value("${elasticsearch.ip}")
////	private String hostName;
////	/**
////	 * 端口
////	 */
////	@Value("${elasticsearch.port}")
////	private String port;
////	/**
////	 * 集群名称
////	 */
////	@Value("${elasticsearch.cluster.name}")
////	private String clusterName;
////	/**
////	 * 连接池
////	 */
////	@Value("${elasticsearch.pool}")
////	private String poolSize;
////	
////	public TransportClient client() {
////		log.info("初始化开始.......");
////		TransportClient transportClient = null;
////		try {
////			Settings esSetting = Settings.builder().put("client.transport.sniff",true)//增加嗅探机制，找到ES集群
////					.put("thread_pool.search.size",Integer.parseInt(poolSize))//增加线程池个数，暂时为5个
////					.put("cluster.name",clusterName)
////					.build();
////			//配置信息Settings自定义，下面设置为EMPTY
////			transportClient = new PreBuiltTransportClient(esSetting);
////			TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName),Integer.valueOf(port));
////			transportClient.addTransportAddress(transportAddress);
////			
////		}catch(Exception e) {
////			log.error("elasticsearch TransportClient create error !!! {}",e.getMessage());
////		}
////		return transportClient;
////	}
//}
