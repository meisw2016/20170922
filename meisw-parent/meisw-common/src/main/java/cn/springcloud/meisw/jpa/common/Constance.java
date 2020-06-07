package cn.springcloud.meisw.jpa.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Constance {
	
	public enum Auth {
		AUTH_TOKEN_INVALID("1001","token无效"),
		AUTH_RESOURCE_UNAUTHORIZED("403","资源未授权");
		
		private String code;
		private String name;
		
		private Auth(String code,String name){
			this.code = code;
			this.name = name;
		}
		
        public String getCode() {
        	return code;
        }
		
        public void setCode(String code) {
        	this.code = code;
        }
		
        public String getName() {
        	return name;
        }
		
        public void setName(String name) {
        	this.name = name;
        }
		
		public static Auth getAuth(String code){
			for(Auth auth : Auth.values()){
				if(auth.code.equals(code)){
					return auth;
				}
			}
			return null;
		}
	}
	
	/**
	 * 
	 * @author meisw 2019年11月5日 下午3:15:21
	 * @Method: getCurrentTime 
	 * @Description: 获取当前时间
	 * @return 
	 * @throws
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
     * 接口url
     */
    public static Map<String,String> URL_MAPPING_MAP = new HashMap<>();

    /**
     *  获取项目根目录
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * 密码加密相关
     */
    public static String SALT = "zhengqing";
    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头 - token
     */
    public static final String REQUEST_HEADER = "X-Token";

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * 登录者角色
     */
    public static final String ROLE_LOGIN = "role_login";
}
