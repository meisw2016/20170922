package cn.springcloud.meisw.eureka.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.AttachUserPolicyRequest;
import com.tencentcloudapi.cam.v20190116.models.AttachUserPolicyResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午2:14:50
 * @ClassName AttachUserPolicyTest
 * @Description 绑定到用户的策略
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class AttachUserPolicyTest {
	
	private static final Logger log = LoggerFactory.getLogger(AttachUserPolicyTest.class);
	
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
			
			AttachUserPolicyRequest req = new AttachUserPolicyRequest();
			req.setPolicyId(1L);//策略ID
			req.setAttachUin(100010639420L);//子账号uin
			
			CamClient client = new CamClient(cred,"",clientProfile);
			AttachUserPolicyResponse resp = client.AttachUserPolicy(req);
			log.info("绑定用户策略："+AttachUserPolicyResponse.toJsonString(resp));
			/**
			 * {"RequestId":"3e69f82a-6035-4826-afb0-369cc7be46da"}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
