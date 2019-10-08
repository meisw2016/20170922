package cn.springcloud.book.eureka.tencent.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.ListAttachedUserPoliciesRequest;
import com.tencentcloudapi.cam.v20190116.models.ListAttachedUserPoliciesResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午3:08:47
 * @ClassName ListAttachedUserPoliciesTest
 * @Description 查询子账号关联的策略列表
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class ListAttachedUserPoliciesTest {
	
	private static final Logger log = LoggerFactory.getLogger(ListAttachedUserPoliciesTest.class);
	
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
			clientProfile.setHttpProfile(httpProfile);//100010639420
			
			ListAttachedUserPoliciesRequest req = new ListAttachedUserPoliciesRequest();
			req.setTargetUin(100010639420L);
			CamClient client = new CamClient(cred,"ap-beijing",clientProfile);
			ListAttachedUserPoliciesResponse resp = client.ListAttachedUserPolicies(req);
			log.info("查询子账号关联的策略："+ListAttachedUserPoliciesRequest.toJsonString(resp));
			/**
			 * {
	"TotalNum": 1,
	"List": [{
		"PolicyId": 1,
		"PolicyName": "AdministratorAccess",
		"AddTime": "2019-06-26 14:30:18",
		"CreateMode": 2,
		"PolicyType": ""
	}],
	"RequestId": "78df789f-de8b-44a3-b363-e454078fc67d"
}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
