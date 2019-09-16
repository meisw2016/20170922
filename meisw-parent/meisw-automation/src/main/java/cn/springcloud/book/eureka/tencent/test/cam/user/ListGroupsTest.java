package cn.springcloud.book.eureka.tencent.test.cam.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.ListGroupsRequest;
import com.tencentcloudapi.cam.v20190116.models.ListGroupsResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月27日 上午8:54:22
 * @ClassName ListGroupsTest
 * @Description 查询用户组列表
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class ListGroupsTest {
	
	private static final Logger log = LoggerFactory.getLogger(ListGroupsTest.class);
	
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
			
			ListGroupsRequest req = new ListGroupsRequest();
			
			CamClient client = new CamClient(cred, "", clientProfile);
			ListGroupsResponse resp = client.ListGroups(req);
			log.info("查询用户组列表服务：" + ListGroupsResponse.toJsonString(resp));
			/**
			 * {
	"TotalNum": 2,
	"GroupInfo": [{
		"GroupId": 80541,
		"GroupName": "group1",
		"CreateTime": "2019-06-27 09:03:04",
		"Remark": "用户组备注"
	}, {
		"GroupId": 80540,
		"GroupName": "group",
		"CreateTime": "2019-06-27 08:43:47",
		"Remark": "用户组备注"
	}],
	"RequestId": "abda7130-ceed-4f50-a110-315efb6f60cb"
}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
