package cn.springcloud.book.eureka.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurlUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CurlUtil.class);
	
	public static void main(String[] args) {
		
		//String[] cmds = {"curl","192.168.254.131:9200/_cluster/health?pretty"};
		//String[] cmds = {"curl","-H","Content-Type:application/json","-XPOST","-u elastic","http://192.168.254.131:9200/_xpack/security/user/elastic/_password","-d","{\"password\":\"changeme\"}"};
//		String[] cmds= {"curl","-u elastic:changeme","-XGET","http://192.168.254.131:5601/api/kibana/settings"};
		String[] cmds= {"curl","-u elastic:changeme","-XGET","http://192.168.254.131:5601/api/security/v1/login"};
		//String[] cmds = {"curl -u elastic:changeme -XGET \"http://192.168.254.131:5601/api/kibana/settings\""};
		
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p ;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;
			
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line = br.readLine()) != null) {
				log.info("\t{}",line);
			}
			br.close();
		}catch(IOException e) {
			log.error("请求服务器异常：errorMessage={}",e);
		}
	}
	
}
