package cn.springcloud.meisw.kafka.tencent.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.GetPolicyRequest;
import com.tencentcloudapi.cam.v20190116.models.GetPolicyResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 查看策略详情
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午2:05:06
 * @ClassName GetPolicyTest
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class GetPolicyTest {
	
	private static final Logger log = LoggerFactory.getLogger(GetPolicyTest.class);
	
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
			
			GetPolicyRequest req = new GetPolicyRequest();
			req.setPolicyId(15636817L);
			CamClient client = new CamClient(cred,"ap-shanghai",clientProfile);
			
			GetPolicyResponse resp = client.GetPolicy(req);
			log.info("查看策略详情服务："+GetPolicyResponse.toJsonString(resp));
			/**
			 * {
	"PolicyName": "QcloudAccessForLVBRole",
	"Description": "腾讯云直播(LVB)操作权限含日志服务(CLS)的增删改查日志集，增删改查日志主题，搜索下载上传日志。",
	"Type": 2,
	"AddTime": "2019-04-11 17:08:55",
	"UpdateTime": "2019-04-11 17:08:55",
	"PolicyDocument": "{\"version\":\"2.0\",\"statement\":[{\"effect\":\"allow\",\"action\":[\"cls:pushLog\",\"cls:searchLog\",\"cls:listLogset\",\"cls:getLogset\",\"cls:listTopic\",\"cls:getTopic\",\"cls:createTopic\",\"cls:modifyTopic\",\"cls:deleteTopic\",\"cls:createLogset\",\"cls:modifyLogset\",\"cls:deleteLogset\",\"cls:downloadLog\",\"cls:getIndex\",\"cls:modifyIndex\"],\"resource\":\"*\"}]}",
	"RequestId": "2c2f874c-4019-4a84-837c-aae4f00946fd"
}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
	
}
