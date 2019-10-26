package cn.springcloud.meisw.kafka.tencent.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.AttachGroupPolicyRequest;
import com.tencentcloudapi.cam.v20190116.models.AttachGroupPolicyResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月27日 下午2:50:24
 * @ClassName AttachGroupPolicyTest
 * @Description 绑定策略到用户组
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class AttachGroupPolicyTest {
	
	private static final Logger log = LoggerFactory.getLogger(AttachGroupPolicyTest.class);
	
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
			
			AttachGroupPolicyRequest req = new AttachGroupPolicyRequest();
			req.setPolicyId(21779981L);
			req.setAttachGroupId(80541L);
			CamClient client = new CamClient(cred,"",clientProfile);
			AttachGroupPolicyResponse resp = client.AttachGroupPolicy(req);
			log.info("绑定策略policyId={},attachGroupId={}到用户接口{}",req.getPolicyId(),req.getAttachGroupId(),AttachGroupPolicyResponse.toJsonString(resp));
			/**
			 * {"RequestId":"8d61987f-08aa-4a38-aa4f-43a890319e65"}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
