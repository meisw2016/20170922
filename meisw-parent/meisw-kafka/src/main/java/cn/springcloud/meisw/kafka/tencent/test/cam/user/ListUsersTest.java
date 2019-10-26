package cn.springcloud.meisw.kafka.tencent.test.cam.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.ListUsersRequest;
import com.tencentcloudapi.cam.v20190116.models.ListUsersResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月26日 下午2:20:30
 * @ClassName ListUsersTest
 * @Description 拉取子用户
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class ListUsersTest {
	
	private static final Logger log = LoggerFactory.getLogger(ListUsersTest.class);
	
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
			
			ListUsersRequest req = new ListUsersRequest();
			CamClient client = new CamClient(cred,"ap-guangzhou",clientProfile);
			ListUsersResponse resp = client.ListUsers(req);
			log.info("拉取子账号信息："+ListUsersResponse.toJsonString(resp));
			/**
			 * {
	"Data": [{
		"Uin": 100010621861,
		"Name": "meisw",
		"Uid": 4307434,
		"Remark": "meisw",
		"ConsoleLogin": 1,
		"PhoneNum": "15185209365",
		"CountryCode": "86",
		"Email": "admin@admin.com"
	}, {
		"Uin": 100010639420,
		"Name": "子用户",
		"Uid": 4316645,
		"Remark": "子用户备注",
		"ConsoleLogin": 1,
		"PhoneNum": "",
		"CountryCode": "86",
		"Email": ""
	}, {
		"Uin": 100010639482,
		"Name": "test1",
		"Uid": 4316703,
		"Remark": "test1",
		"ConsoleLogin": 1,
		"PhoneNum": "",
		"CountryCode": "86",
		"Email": ""
	}],
	"RequestId": "c76cd9a4-69ec-4c5f-8908-6162a5009933"
}
			 */
		} catch (Exception e) {
			log.error("" + e.getMessage());
		}
	}
}
/**
{"Data":[{"Uin":100010621861,"Name":"meisw","Uid":4307434,"Remark":"meisw","ConsoleLogin":1,"PhoneNum":"15185209365","CountryCode":"86","Email":"admin@admin.com"},
{"Uin":100010639420,"Name":"子用户","Uid":4316645,"Remark":"子用户备注","ConsoleLogin":1,"PhoneNum":"","CountryCode":"86","Email":""},
{"Uin":100010639482,"Name":"test1","Uid":4316703,"Remark":"test1","ConsoleLogin":1,"PhoneNum":"","CountryCode":"86","Email":""}],"RequestId":"8e8e38ea-4b84-4a92-a40d-045aa89bce76"}

*/