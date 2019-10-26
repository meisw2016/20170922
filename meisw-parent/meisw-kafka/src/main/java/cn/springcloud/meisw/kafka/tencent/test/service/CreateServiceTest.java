package cn.springcloud.meisw.kafka.tencent.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.springcloud.meisw.kafka.tencent.test.service.client.CreateServiceClient;
import cn.springcloud.meisw.kafka.tencent.test.service.req.CreateServiceRequest;
import cn.springcloud.meisw.kafka.tencent.test.service.resp.CreateServiceResponse;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月27日 下午4:41:24
 * @ClassName CreateServiceTest
 * @Description 创建服务
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class CreateServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(CreateServiceTest.class);
	
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
			
			CreateServiceClient client = new CreateServiceClient(cred,"",clientProfile);
			CreateServiceRequest req = new CreateServiceRequest();
			
			CreateServiceResponse resp = client.CreateService(req);
			log.info("创建服务："+ CreateServiceResponse.toJsonString(resp));
			
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
