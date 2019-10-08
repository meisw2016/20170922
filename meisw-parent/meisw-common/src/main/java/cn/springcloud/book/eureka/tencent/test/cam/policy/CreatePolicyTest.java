package cn.springcloud.book.eureka.tencent.test.cam.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.CreatePolicyRequest;
import com.tencentcloudapi.cam.v20190116.models.CreatePolicyResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
/**
 * 
 * <p></p>
 * @author meisw 2019年6月26日 下午2:13:38
 * @ClassName CreatePolicyTest
 * @Description 创建策略
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class CreatePolicyTest {
	private static final Logger log = LoggerFactory.getLogger(CreatePolicyTest.class);
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
            
            CreatePolicyRequest req = new CreatePolicyRequest();
            req.setPolicyName("QcloudAccessForCloudAuditCARole2222");
            req.setDescription("腾讯云云审计服务(CA)创建跟踪集功能操作权限含查询和创建对象存储桶(COS)22222");
            req.setPolicyDocument("{\"version\":\"2.0\",\"statement\":[{\"action\":\"cvm:*\",\"effect\":\"allow\",\"resource\":\"*\"}]}");
            CamClient client = new CamClient(cred,"ap-shanghai",clientProfile);
            CreatePolicyResponse resp = client.CreatePolicy(req);
            log.info("创建策略服务："+CreatePolicyResponse.toJsonString(resp));
            /**
             * {"PolicyId":21779981,"RequestId":"95da7a2b-0c66-4d93-b3d0-71619ffa8604"}
             */
		}catch(Exception e) {
			log.error("创建策略服务异常：errorMessage={}",e);
		}
	}
}
