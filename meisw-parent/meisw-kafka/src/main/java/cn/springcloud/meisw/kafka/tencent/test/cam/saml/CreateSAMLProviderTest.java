package cn.springcloud.meisw.kafka.tencent.test.cam.saml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.CreateSAMLProviderRequest;
import com.tencentcloudapi.cam.v20190116.models.CreateSAMLProviderResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午4:32:04
 * @ClassName CreateSAMLProviderTest
 * @Description 创建SAML身份提供商
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class CreateSAMLProviderTest {
	
	private static final Logger log = LoggerFactory.getLogger(CreateSAMLProviderTest.class);
	
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
			
			CreateSAMLProviderRequest req = new CreateSAMLProviderRequest();
			req.setName("idp");
			req.setDescription("22");
//			String samlMeta = "U0FNTE1ldGFkYXRhRG9jdW1lbnQ";
			String samlMeta = "urn:oasis:names:tc:SAML:2.0:protocol";
//			String samlMeta = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
//					"<EntityDescriptor>\r\n" + 
//					"    <IDPSSODescriptor protocolSupportEnumeration=\"urn:oasis:names:tc:SAML:2.0:protocol\">\r\n" + 
//					"        admin\r\n" + 
//					"    </IDPSSODescriptor>\r\n" + 
//					"</EntityDescriptor>";
			req.setSAMLMetadataDocument(samlMeta);
			CamClient client = new CamClient(cred,"",clientProfile);
			CreateSAMLProviderResponse resp = client.CreateSAMLProvider(req);
			log.info("创建SAML身份提供商："+CreateSAMLProviderResponse.toJsonString(resp));
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
