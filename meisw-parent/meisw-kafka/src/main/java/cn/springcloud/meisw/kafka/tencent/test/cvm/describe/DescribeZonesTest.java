package cn.springcloud.meisw.kafka.tencent.test.cvm.describe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;

/**
 * 查询可用区列表
 * <p></p>
 * @author meisw 2019年6月26日 上午9:48:02
 * @ClassName DescribeZonesTest
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class DescribeZonesTest {
	
	private static final Logger log = LoggerFactory.getLogger(DescribeZonesTest.class);
	
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
            httpProfile.setEndpoint("cvm.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            
            DescribeZonesRequest req = new DescribeZonesRequest();
//            req.set("Region", "ap-bangkok");
            CvmClient client = new CvmClient(cred,"ap-beijing",clientProfile);
            DescribeZonesResponse resp = client.DescribeZones(req);
            
            log.info("查询可用区列表服务："+DescribeZonesResponse.toJsonString(resp));
            /**
             * {
	"TotalCount": 5,
	"ZoneSet": [{
		"Zone": "ap-beijing-1",
		"ZoneName": "北京一区",
		"ZoneId": "800001",
		"ZoneState": "AVAILABLE"
	}, {
		"Zone": "ap-beijing-2",
		"ZoneName": "北京二区",
		"ZoneId": "800002",
		"ZoneState": "AVAILABLE"
	}, {
		"Zone": "ap-beijing-3",
		"ZoneName": "北京三区",
		"ZoneId": "800003",
		"ZoneState": "AVAILABLE"
	}, {
		"Zone": "ap-beijing-4",
		"ZoneName": "北京四区",
		"ZoneId": "800004",
		"ZoneState": "AVAILABLE"
	}, {
		"Zone": "ap-beijing-5",
		"ZoneName": "北京五区",
		"ZoneId": "800005",
		"ZoneState": "AVAILABLE"
	}],
	"RequestId": "61680415-a92a-4053-96fd-f10ed789fa98"
}
             */
		}catch(Exception e) {
			log.error("查询可用区列表服务异常：errorMessage={}",e);
		}
	}
}
