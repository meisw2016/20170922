//package cn.springcloud.book.eureka.util;
//
//import org.apache.http.message.BasicHeader;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class EsUntil {
//	
//	private static final Logger log = LoggerFactory.getLogger(EsUntil.class);
//	
//	public static boolean isConnectionOk(RestHighLevelClient client){
//		if(client == null){
//			return false;
//		}
//		try{
////			boolean result = client.ping(new BasicHeader("Content-Type","application/json;charset=utf-8"),new BasicHeader("Accept","application/json"));
//			RequestOptions requestOptions = new RequestOptions(RequestOptions.Builder);
//			client.ping(requestOptions);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//}
