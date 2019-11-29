package cn.springcloud.meisw.eureka.test.cam.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.AddUserToGroupRequest;
import com.tencentcloudapi.cam.v20190116.models.AddUserToGroupResponse;
import com.tencentcloudapi.cam.v20190116.models.GroupIdOfUidInfo;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月27日 上午8:45:12
 * @ClassName AddUserToGroupTest
 * @Description 将用户加入到用户组
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class AddUserToGroupTest {
	
	private static final Logger log = LoggerFactory.getLogger(AddUserToGroupTest.class);
	
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
			
			AddUserToGroupRequest req = new AddUserToGroupRequest();
			GroupIdOfUidInfo info = new GroupIdOfUidInfo();
			info.setGroupId(80574L);
			info.setUid(4316703L);
			GroupIdOfUidInfo[] arr = new GroupIdOfUidInfo[] {info};
			req.setInfo(arr);
			
			CamClient client = new CamClient(cred,"",clientProfile);
			AddUserToGroupResponse resp = client.AddUserToGroup(req);
			log.info("用户加入到用户组服务："+AddUserToGroupResponse.toJsonString(resp));
			/**
			 * {"RequestId":"57ae9738-d60f-47e4-b1e2-23bc4962fcc5"}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
	
}
