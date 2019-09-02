package cn.springcloud.book.eureka.tencent.test.cam.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import cn.springcloud.book.eureka.tencent.test.cam.role.client.DescribeRoleListClient;
import cn.springcloud.book.eureka.tencent.test.cam.role.req.DescribeRoleListRequest;
import cn.springcloud.book.eureka.tencent.test.cam.role.resp.DescribeRoleListResponse;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午2:38:28
 * @ClassName DescribeRoleListTest
 * @Description 获取指定角色的详细信息
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class DescribeRoleListTest {
	
	private static final Logger log = LoggerFactory.getLogger(DescribeRoleListTest.class);
	
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
			DescribeRoleListClient client = new DescribeRoleListClient(cred,"",clientProfile);
			DescribeRoleListRequest req = new DescribeRoleListRequest();
			req.setPage(1);//页码,从1开始
			req.setRp(200);//每页大小,不能大于200
			DescribeRoleListResponse resp = client.DescribeRoleList(req);
			log.info("获取角色列表服务："+DescribeRoleListResponse.toJsonString(resp));
			/**
			 * {
	"TotalNum": 1,
	"List": [{
		"RoleId": "4611686018427828258",
		"RoleName": "test",
		"PolicyDocument": "{\"version\":\"2.0\",\"statement\":[{\"action\":[\"name/sts:AssumeRole\"],\"effect\":\"allow\",\"principal\":{\"qcs\":[\"qcs::cam::uin/100000396207:root\"],\"service\":[\"apigateway.qcloud.com\",\"blueking.cloud.tencent.com\",\"mariadb.qcloud.com\",\"msp.cloud.tencent.com\",\"tcb.cloud.tencent.com\"]}}]}",
		"Description": "test",
		"AddTime": "2019-06-24 10:22:35",
		"UpdateTime": "2019-06-24 17:47:35"
	}]
}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
