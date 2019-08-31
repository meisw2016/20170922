package cn.springcloud.book.eureka.tencent.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.CreatePolicyRequest;
import com.tencentcloudapi.cam.v20190116.models.CreatePolicyResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;

/**
 * 创建角色
 * <p>
 * </p>
 * @author meisw 2019年6月25日 下午7:53:04
 * @ClassName CreateRoleTest
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月25日
 * @modify by reason:{方法名}:{原因}
 */
public class CreateRoleTest {
	
	private static final Logger log = LoggerFactory.getLogger(CreateRoleTest.class);
	
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
			
			CamClient client = new CamClient(cred, "ap-shanghai", clientProfile);
			
			CreatePolicyRequest cpr = new CreatePolicyRequest();
			cpr.setPolicyDocument("{\"version\":\"2.0\",\"statement\":[{\"effect\":\"allow\",\"action\":[\"cls:pushLog\",\"cls:searchLog\",\"cls:listLogset\",\"cls:getLogset\",\"cls:listTopic\",\"cls:getTopic\",\"cls:createTopic\",\"cls:modifyTopic\",\"cls:deleteTopic\",\"cls:createLogset\",\"cls:modifyLogset\",\"cls:deleteLogset\",\"cls:downloadLog\",\"cls:getIndex\",\"cls:modifyIndex\"],\"resource\":\"*\"}]}");
			cpr.setPolicyName("QcloudAccessForLVBRole2");
			cpr.setDescription("腾讯云直播(LVB)操作权限含日志服务(CLS)的增删改查日志集2");
			
			CreatePolicyResponse resp = client.CreatePolicy(cpr);
			log.info("创建策略服务："+CreatePolicyResponse.toJsonString(resp));
			/**
			 * {
	"PolicyId": 21784080,
	"RequestId": "c8008cd0-ee1f-4470-9c80-94637967cdd2"
}
			 */
		} catch (Exception e) {
			log.error("创建策略服务异常：errorMessage={}", e.getMessage());
		}
	}
}
