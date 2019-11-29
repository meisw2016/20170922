package cn.springcloud.meisw.eureka.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import cn.springcloud.meisw.eureka.test.service.client.ReleaseServiceClient;
import cn.springcloud.meisw.eureka.test.service.req.ReleaseServiceRequest;
import cn.springcloud.meisw.eureka.test.service.resp.ReleaseServiceResponse;

public class ReleaseServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(ReleaseServiceTest.class);
	
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
			
			ReleaseServiceRequest req = new ReleaseServiceRequest();
			
			ReleaseServiceClient client = new ReleaseServiceClient(cred,"",clientProfile);
			
			ReleaseServiceResponse resp = client.ReleaseService(req);
			log.info("服务发布："+ ReleaseServiceResponse.toJsonString(resp));
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
