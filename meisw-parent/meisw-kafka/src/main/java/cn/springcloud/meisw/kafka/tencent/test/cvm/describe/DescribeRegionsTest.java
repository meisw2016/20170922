package cn.springcloud.meisw.kafka.tencent.test.cvm.describe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeRegionsRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeRegionsResponse;

/**
 * 查询地域列表
 * <p>
 * </p>
 * @author meisw 2019年6月26日 上午9:45:13
 * @ClassName DescribeRegionsTest
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class DescribeRegionsTest {
	
	private static final Logger log = LoggerFactory.getLogger(DescribeRegionsTest.class);
	
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
			
			DescribeRegionsRequest request = new DescribeRegionsRequest();
			
			CvmClient client = new CvmClient(cred, "", clientProfile);
			DescribeRegionsResponse resp = client.DescribeRegions(request);
			log.info("========= " + DescribeRegionsResponse.toJsonString(resp));
			/**
			 * {
	"TotalCount": 19,
	"RegionSet": [{
		"Region": "ap-bangkok",
		"RegionName": "亚太地区(曼谷)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-beijing",
		"RegionName": "华北地区(北京)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-chengdu",
		"RegionName": "西南地区(成都)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-chongqing",
		"RegionName": "西南地区(重庆)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-guangzhou",
		"RegionName": "华南地区(广州)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-guangzhou-open",
		"RegionName": "华南地区(广州Open)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-hongkong",
		"RegionName": "东南亚地区(香港)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-mumbai",
		"RegionName": "亚太地区(孟买)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-seoul",
		"RegionName": "亚太地区(首尔)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-shanghai",
		"RegionName": "华东地区(上海)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-shanghai-fsi",
		"RegionName": "华东地区(上海金融)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-shenzhen-fsi",
		"RegionName": "华南地区(深圳金融)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-singapore",
		"RegionName": "东南亚地区(新加坡)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "ap-tokyo",
		"RegionName": "亚太地区(东京)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "eu-frankfurt",
		"RegionName": "欧洲地区(法兰克福)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "eu-moscow",
		"RegionName": "欧洲地区(莫斯科)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "na-ashburn",
		"RegionName": "美国东部(弗吉尼亚)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "na-siliconvalley",
		"RegionName": "美国西部(硅谷)",
		"RegionState": "AVAILABLE"
	}, {
		"Region": "na-toronto",
		"RegionName": "北美地区(多伦多)",
		"RegionState": "AVAILABLE"
	}],
	"RequestId": "fbb8b82c-b5d9-4805-826c-b627aad2cb04"
}
			 */
		} catch (Exception e) {
			log.error("查询地域信息服务异常：errorMessage={}", e);
		}
	}
}
