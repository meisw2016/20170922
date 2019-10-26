package cn.springcloud.meisw.kafka.tencent.test.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.monitor.v20180724.MonitorClient;
import com.tencentcloudapi.monitor.v20180724.models.DescribeBaseMetricsRequest;
import com.tencentcloudapi.monitor.v20180724.models.DescribeBaseMetricsResponse;

/**
 * 获取基础指标详情
 * <p></p>
 * @author meisw 2019年6月26日 下午1:29:54
 * @ClassName DescribeBaseMetricsTest
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class DescribeBaseMetricsTest {
	
	private static final Logger log = LoggerFactory.getLogger(DescribeBaseMetricsTest.class);
	
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
            httpProfile.setEndpoint("monitor.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            /**
             * 3.实例化一个client选项
             */
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            clientProfile.setHttpProfile(httpProfile);
            
            DescribeBaseMetricsRequest req = new DescribeBaseMetricsRequest();
            req.setNamespace("QCE/CVM");
            req.setMetricName("CPUUsage");
            MonitorClient client = new MonitorClient(cred,"ap-shanghai",clientProfile);
            DescribeBaseMetricsResponse resp = client.DescribeBaseMetrics(req);
            log.info("获取基础指标详情："+DescribeBaseMetricsResponse.toJsonString(resp));
            /**
             * {
	"MetricSet": [{
		"Namespace": "QCE/CVM",
		"MetricName": "CPUUsage",
		"Unit": "%",
		"UnitCname": "",
		"Period": [10, 60, 86400, 300, 3600],
		"Periods": [{
			"Period": "10",
			"StatType": ["avg"]
		}, {
			"Period": "60",
			"StatType": ["avg"]
		}, {
			"Period": "86400",
			"StatType": ["max", "p95"]
		}, {
			"Period": "300",
			"StatType": ["max"]
		}, {
			"Period": "3600",
			"StatType": ["max"]
		}],
		"Meaning": {
			"En": "",
			"Zh": "CPU利用率是通过CVM子机内部监控组件采集上报，数据更加精准"
		},
		"Dimensions": [{
			"Dimensions": ["InstanceId"]
		}]
	}],
	"RequestId": "0b3bb4df-cae4-49f8-abd9-2c22ddb261a0"
}
             */
		}catch(Exception e) {
			log.error("获取基础指标详情服务异常：errorMessage={}",e.getMessage());
		}
	}
}
