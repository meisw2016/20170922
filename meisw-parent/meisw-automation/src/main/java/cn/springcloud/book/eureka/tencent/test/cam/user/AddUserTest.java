package cn.springcloud.book.eureka.tencent.test.cam.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.AddUserRequest;
import com.tencentcloudapi.cam.v20190116.models.AddUserResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
/**
 * 添加子用户
 * <p></p>
 * @author meisw 2019年6月25日 上午11:24:36
 * @ClassName AddUserTest
 * @Description 添加子用户
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月25日
 * @modify by reason:{方法名}:{原因}
 */
public class AddUserTest {
	
	private static final Logger log = LoggerFactory.getLogger(AddUserTest.class);
	
	public static void main(String[] args) {
		try {
			/**
			 * 1.实例化认证对象
			 */
			Credential cred = new Credential("AKIDLSKwKpi8yZXGjwquZzBIAnBTXO1VE50m", "ZzJTPBi5Gm8FAtY4QcOxIOJ9pKZeVCmB");
			/**
			 * 2.实例化一个http选项
			 */
			HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("GET"); // post请求(默认为post请求)
            httpProfile.setConnTimeout(30); // 请求连接超时时间，单位为秒(默认60秒)
            httpProfile.setEndpoint("cam.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            /**
             * 实例化要请求的产品(CAM)的client对象，clientProfile是可选的
             */
            CamClient client = new CamClient(cred,"ap-shanghai",clientProfile);
            /**
             * 4.实例化一个添加子用户实例信息对象，每个接口都会对于一个request对象
             */
            AddUserRequest addUser = new AddUserRequest();
            addUser.setName("test1");
            addUser.setRemark("test1");
            /**0:代表子用户无法登录控制台,1:代表子用户可以登录控制台*/
            addUser.setConsoleLogin(1L);
            /**0：代表不生成子用户密钥,1：代表生成子用户密钥*/
            addUser.setUseApi(1L);
            /**0：代表子用户在下次登陆控制台时不需要重置密码,1：代表子用户在下次登录控制台时需要重置密码*/
            addUser.setNeedResetPassword(0L);
            AddUserResponse resp = client.AddUser(addUser);
            /**输出json格式的字符串回包*/
            log.info("========= "+AddUserResponse.toJsonString(resp));
            /**
             * 通过client对象调用
             */
            
		}catch(Exception e) {
			log.error("系统异常：{}",e.getMessage());
		}
	}
}
