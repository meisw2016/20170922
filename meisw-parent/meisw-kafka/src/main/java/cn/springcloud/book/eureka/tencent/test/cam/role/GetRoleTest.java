package cn.springcloud.book.eureka.tencent.test.cam.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import cn.springcloud.book.eureka.tencent.test.cam.role.client.DescribeRoleListClient;
import cn.springcloud.book.eureka.tencent.test.cam.role.client.GetRoleClient;
import cn.springcloud.book.eureka.tencent.test.cam.role.req.GetRoleRequest;
import cn.springcloud.book.eureka.tencent.test.cam.role.resp.GetRoleResponse;

public class GetRoleTest {
	
	private static final Logger log = LoggerFactory.getLogger(GetRoleTest.class);
	
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
			
			GetRoleClient client = new GetRoleClient(cred,"",clientProfile);
			GetRoleRequest req = new GetRoleRequest();
//			req.setRoleId("4611686018427828258");
			req.setRoleName("test");
			GetRoleResponse resp =client.GetRole(req);
			log.info("获取roleId={}或roleName={}指定角色的详细信息{}：",req.getRoleId(),req.getRoleName(),GetRoleResponse.toJsonString(resp));
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
