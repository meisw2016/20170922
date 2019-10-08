package cn.springcloud.book.eureka.tencent.test.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.monitor.v20180724.MonitorClient;
import com.tencentcloudapi.monitor.v20180724.models.Dimension;
import com.tencentcloudapi.monitor.v20180724.models.GetMonitorDataRequest;
import com.tencentcloudapi.monitor.v20180724.models.GetMonitorDataResponse;
import com.tencentcloudapi.monitor.v20180724.models.Instance;

/**
 * 获取云产品的监控数据
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午1:40:30
 * @ClassName GetMonitorDataTest
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class GetMonitorDataTest {
	
	private static final Logger log = LoggerFactory.getLogger(GetMonitorDataTest.class);
	
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
			
			GetMonitorDataRequest req  = new GetMonitorDataRequest();
			req.setNamespace("QCE/CVM");
			//Dimension dis = new Dimension();
			Dimension[] arr = new Dimension [] {new Dimension()};
			Instance ins = new Instance();
			ins.setDimensions(arr);
			Instance[] iarry = new Instance[] {ins};
			req.setInstances(iarry);
			MonitorClient client = new MonitorClient(cred, "ap-beijing",clientProfile);
			GetMonitorDataResponse resp = client.GetMonitorData(req);
			log.info("获取云产品的监控数据:"+GetMonitorDataResponse.toJsonString(resp));
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
	
}
