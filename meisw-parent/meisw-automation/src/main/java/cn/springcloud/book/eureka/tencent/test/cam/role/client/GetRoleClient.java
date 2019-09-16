package cn.springcloud.book.eureka.tencent.test.cam.role.client;

import java.lang.reflect.Type;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.JsonResponseModel;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;

import cn.springcloud.book.eureka.tencent.test.cam.role.req.GetRoleRequest;
import cn.springcloud.book.eureka.tencent.test.cam.role.resp.GetRoleResponse;

public class GetRoleClient extends AbstractClient {
	
	private static String endpoint = "cam.tencentcloudapi.com";
	private static String version = "2019-01-16";
	
	/**
	 * 
	 * @author meisw 2019年6月27日 下午2:05:02
	 * @Method: GetRoleClient 
	 * @Description: 构造client
	 * @param credential 认证信息实例
	 * @param region 产品地域
	 * @throws
	 */
	public GetRoleClient(Credential credential, String region) {
		this(credential, region, new ClientProfile());
	}
	/**
	 * 
	 * 创建一个新的实例
	 * @author meisw 2019年6月27日 下午2:06:17
	 * @Method: GetRoleClient 
	 * @Description: 构造client
	 * @param credential 认证信息实例
	 * @param region 产品地域
	 * @param profile 配置实例
	 * @throws
	 */
	public GetRoleClient(Credential credential, String region, ClientProfile profile) {
		super(GetRoleClient.endpoint, GetRoleClient.version, credential, region, profile);
	}
	
	/**
	 * 获取指定角色的详细信息 @author meisw 2019年6月27日 上午10:42:02 @Method: GetRole @Description: @param req @return @throws TencentCloudSDKException @throws
	 */
	public GetRoleResponse GetRole(GetRoleRequest req) throws TencentCloudSDKException {
		JsonResponseModel<GetRoleResponse> rsp = null;
		try {
			Type type = new TypeToken<JsonResponseModel<GetRoleResponse>>() {}.getType();
			rsp = gson.fromJson(this.internalRequest(req, "GetRole"), type);
		} catch (JsonSyntaxException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		return rsp.response;
	}
}
