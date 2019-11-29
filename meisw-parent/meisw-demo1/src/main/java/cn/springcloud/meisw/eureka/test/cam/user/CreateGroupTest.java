package cn.springcloud.meisw.eureka.test.cam.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.CreateGroupRequest;
import com.tencentcloudapi.cam.v20190116.models.CreateGroupResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 创建用户组
 * <p>
 * </p>
 * @author meisw 2019年6月27日 上午8:39:15
 * @ClassName CreateGroupTest
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class CreateGroupTest {
	
	private static final Logger log = LoggerFactory.getLogger(CreateGroupTest.class);
	
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
			
			CreateGroupRequest request = new CreateGroupRequest();
			request.setGroupName("group2");
			request.setRemark("用户组备注");
			
			CamClient client = new CamClient(cred,"",clientProfile);
			
			CreateGroupResponse resp = client.CreateGroup(request);
			log.info("创建用户组服务："+CreateGroupResponse.toJsonString(resp));
			/**
			 * {"GroupId":80541,"RequestId":"8084b405-8a00-41b8-bf69-e9783cd8704f"}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}

/**
{"GroupId":80540,"RequestId":"8ad7ebb3-37ce-4325-959b-38b8c742aa6f"}
*/