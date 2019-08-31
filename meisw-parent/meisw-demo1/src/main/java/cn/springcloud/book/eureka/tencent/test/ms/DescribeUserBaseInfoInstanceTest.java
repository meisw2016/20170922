package cn.springcloud.book.eureka.tencent.test.ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ms.v20180408.MsClient;
import com.tencentcloudapi.ms.v20180408.models.DescribeUserBaseInfoInstanceRequest;
import com.tencentcloudapi.ms.v20180408.models.DescribeUserBaseInfoInstanceResponse;

/**
 * 获取用户基础信息
 * <p></p>
 * @author meisw 2019年6月26日 下午1:22:08
 * @ClassName DescribeUserBaseInfoInstanceTest
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class DescribeUserBaseInfoInstanceTest {
	private static final Logger log = LoggerFactory.getLogger(DescribeUserBaseInfoInstanceTest.class);
	
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
            httpProfile.setEndpoint("ms.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            
            DescribeUserBaseInfoInstanceRequest req = new DescribeUserBaseInfoInstanceRequest();
            MsClient client = new MsClient(cred,"",clientProfile);
            DescribeUserBaseInfoInstanceResponse resp = client.DescribeUserBaseInfoInstance(req);
            log.info("获取用户基础信息服务："+DescribeUserBaseInfoInstanceResponse.toJsonString(resp));
            /**
             * {
	"UserUin": 100000396207,
	"UserAppid": 1254465672,
	"TimeStamp": 1561606140,
	"RequestId": "77002eaf-a5d8-441b-8c66-e8d52b135494"
}
             */
		}catch(Exception e) {
			log.error("获取用户基础信息服务异常：errorMessage={}",e.getMessage());
		}
	}
}
