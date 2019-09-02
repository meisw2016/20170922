package cn.springcloud.book.eureka.tencent.test.sts.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sts.v20180813.StsClient;
import com.tencentcloudapi.sts.v20180813.models.AssumeRoleRequest;
import com.tencentcloudapi.sts.v20180813.models.AssumeRoleResponse;
/**
 * 申请角色临时凭证
 * <p></p>
 * @author meisw 2019年6月26日 上午10:08:25
 * @ClassName AssumeRoleTest
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class AssumeRoleTest {
	
	private static final Logger log = LoggerFactory.getLogger(AssumeRoleTest.class);
	
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
            httpProfile.setEndpoint("sts.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            /**
             * 实例化要请求的产品(CAM)的client对象，clientProfile是可选的
             */
            //CamClient client = new CamClient(cred,"ap-shanghai",clientProfile);
            StsClient client = new StsClient(cred,"ap-shanghai",clientProfile);
            /**
             * 4.实例化一个添加子用户实例信息对象，每个接口都会对于一个request对象
             */
            AssumeRoleRequest assume = new AssumeRoleRequest();
            assume.setRoleArn("qcs::cam::uin/100010639420:role/4611686018427828258");
            assume.setRoleSessionName("tencent");
            assume.setDurationSeconds(7200);
            
            AssumeRoleResponse resp = client.AssumeRole(assume);
            /**输出json格式的字符串回包*/
            log.info("========= "+AssumeRoleResponse.toJsonString(resp));
            /**
             * 通过client对象调用
             */
            
		}catch(Exception e) {
			log.error("申请角色临时凭证服务异常：{}",e.getMessage());
		}
	}
}
