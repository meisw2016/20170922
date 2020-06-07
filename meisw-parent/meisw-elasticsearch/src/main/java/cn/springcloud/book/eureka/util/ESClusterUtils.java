package cn.springcloud.book.eureka.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.GetUsersRequest;
import org.elasticsearch.client.security.GetUsersResponse;
import org.elasticsearch.client.security.PutUserRequest;
import org.elasticsearch.client.security.PutUserResponse;
import org.elasticsearch.client.security.RefreshPolicy;
import org.elasticsearch.client.security.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESClusterUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ESClusterUtils.class);
	/**
	 * 
	 * @author meisw 2019年8月7日 下午2:34:24
	 * @Method: checkUserIsExist 
	 * @Description: 判断用户是否存在
	 * @param username
	 * @return 
	 * @throws
	 */
	public static boolean checkUserIsExist(String username) {
		log.info("ES 查询用户是否存在如此：username={}",username);
		boolean flag = true;
		RestHighLevelClient client = null;
		try {
			client = ClientUtils.getInstance().getClient();
			GetUsersRequest request = new GetUsersRequest(username);
			GetUsersResponse response = client.security().getUsers(request, RequestOptions.DEFAULT);
			Set<User> set = response.getEnabledUsers();
			if(set == null || set.size()<1) {
				flag = false;
			}
			client.close();
		}catch(Exception e) {
			flag = false;
			log.error("ES 查询用户接口异常,请检测网络、端口是否配置正确");
			if(client != null) {
				try {
					client.close();
				}catch(IOException e1) {
					log.error(e1.getMessage());
				}
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @author meisw 2019年8月7日 下午2:45:09
	 * @Method: createUser 
	 * @Description: 创建用户
	 * @param username
	 * @return 
	 * @throws
	 */
	public static boolean createUser(String username) {
		boolean flag = true;
		RestHighLevelClient client = null;
		log.info("创建用户：loginName = {}",username);
		try {
			log.info("es创建用户：username={}",username);
			client = ClientUtils.getInstance().getClient();
			User user = new User(username,Collections.singletonList("APM"));//一般权限
			log.info("es创建用户结果：username={},fullname={}",user.getUsername(),user.getFullName());
			PutUserRequest request = PutUserRequest.withPassword(user, setPassword(username),true,RefreshPolicy.NONE);
			PutUserResponse response = client.security().putUser(request, RequestOptions.DEFAULT);
			boolean isCreated = response.isCreated();
			flag  = isCreated;
			client.close();
		}catch(Exception e) {
			log.info("创建用户服务异常：errorMessage={}",e.getMessage());
			flag = false;
			if(client != null) {
				try {
					client.close();
				}catch(IOException e1) {
					log.error(e1.getMessage());
				}
			}
		}
		return flag;
	}
	
	private static char[] setPassword(String username) {
		StringBuffer sb = new StringBuffer();
		String pwd = username;
		/**
		 * 去掉特殊符号
		 */
		String regEx = "\\.";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(pwd);
		if(pwd.length()<6) {
			sb.append(pwd).append("0000000");
			pwd = sb.toString().substring(0,6);
		}
		return pwd.toCharArray();
	}
}
