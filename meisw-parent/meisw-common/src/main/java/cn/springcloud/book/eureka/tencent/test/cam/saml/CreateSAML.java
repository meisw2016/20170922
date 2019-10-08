package cn.springcloud.book.eureka.tencent.test.cam.saml;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.CreateSAMLProviderRequest;
import com.tencentcloudapi.cam.v20190116.models.GetSAMLProviderRequest;
import com.tencentcloudapi.cam.v20190116.models.GetSAMLProviderResponse;
import com.tencentcloudapi.cam.v20190116.models.ListSAMLProvidersRequest;
import com.tencentcloudapi.cam.v20190116.models.ListSAMLProvidersResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

public class CreateSAML {
	
	private static final Logger log = LoggerFactory.getLogger(CreateSAML.class);
	
	// @Before
	// public void init() {
	// log.info("开始测试....");
	// }
	// @After
	// public void after() {
	// log.info("测试结束.....");
	// }
	public static void main(String[] args) {
		querySAML();
		test1();
		test2();
	}
	
	public static void querySAML() {
		log.info("querySAML .....");
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
			
			/**
			 * 4.实例化要请求产品的client对象，clientProfile是可选的
			 */
			CamClient client = new CamClient(cred, "ap-shanghai", clientProfile);
			
			GetSAMLProviderRequest saml = new GetSAMLProviderRequest();
			saml.setName("1");
			GetSAMLProviderResponse resp = client.GetSAMLProvider(saml);
			String result = GetSAMLProviderRequest.toJsonString(resp);
			log.info("查询身份提供商结果：{}", result);
		} catch (Exception e) {
			log.error("请求服务器异常：" + e.getMessage());
		}
		
	}
	
	public static void test1() {
		log.info("test1 ....");
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
			
			CreateSAMLProviderRequest saml = new CreateSAMLProviderRequest();
			saml.setName("test1");
			saml.setDescription("test1身份提供商");
			// saml.setSAMLMetadataDocument(SAMLMetadataDocument);
		} catch (Exception e) {
			log.error("服务调用异常：{}", e.getMessage());
		}
	}
	
	public static void test2() {
		log.info("test2 ....");
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
			// httpProfile.setEndpoint("cam.api.qcloud.com"); // 指定接入地域域名(默认就近接入)
			httpProfile.setEndpoint("cam.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
			/**
			 * 3.实例化一个client选项
			 */
			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
			clientProfile.setHttpProfile(httpProfile);
			
			/**
			 * 4.实例化要请求产品的client对象，clientProfile是可选的
			 */
			CamClient client = new CamClient(cred, "ap-shanghai", clientProfile);
			
			ListSAMLProvidersRequest req = new ListSAMLProvidersRequest();
			ListSAMLProvidersResponse resp = client.ListSAMLProviders(req);
			String result = ListSAMLProvidersResponse.toJsonString(resp);
			log.info("查询身份提供商结果：{}", result);
		} catch (Exception e) {
			log.error("获取SAML身份提供商列表服务异常：{}", e);
		}
	}
}
