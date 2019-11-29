package cn.springcloud.meisw.eureka.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.DetachGroupPolicyRequest;
import com.tencentcloudapi.cam.v20190116.models.DetachGroupPolicyResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 解除绑定到用户组的策略
 * <p>
 * </p>
 * @author meisw 2019年6月27日 下午3:11:03
 * @ClassName DetachGroupPolicyTest
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class DetachGroupPolicyTest {
	
	private static final Logger log = LoggerFactory.getLogger(DetachGroupPolicyTest.class);
	
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
			httpProfile.setEndpoint("cvm.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
			/**
			 * 3.实例化一个client选项
			 */
			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
			clientProfile.setHttpProfile(httpProfile);
			
			DetachGroupPolicyRequest req  = new DetachGroupPolicyRequest();
			req.setPolicyId(1L);
			req.setDetachGroupId(1L);
			
			CamClient client = new CamClient(cred,"",clientProfile);
			
			DetachGroupPolicyResponse resp = client.DetachGroupPolicy(req);
			log.info("解决绑定到用户组的策略：policyId={},detachGroupId={}",req.getPolicyId(),req.getDetachGroupId(),DetachGroupPolicyResponse.toJsonString(resp));
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
	
}
