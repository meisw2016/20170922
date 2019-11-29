package cn.springcloud.meisw.eureka.test.cam.role.client;

import java.lang.reflect.Type;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.JsonResponseModel;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;

import cn.springcloud.meisw.eureka.test.cam.role.req.DescribeRoleListRequest;
import cn.springcloud.meisw.eureka.test.cam.role.resp.DescribeRoleListResponse;

public class DescribeRoleListClient extends AbstractClient {
	
	private static String endpoint = "cam.tencentcloudapi.com";
	private static String version = "2019-01-16";
	
	/**
	 * 构造client
	 * @param credential 认证信息实例
	 * @param region 产品地域
	 */
	public DescribeRoleListClient(Credential credential, String region) {
		this(credential, region, new ClientProfile());
	}
	
	/**
	 * 构造client
	 * @param credential 认证信息实例
	 * @param region 产品地域
	 * @param profile 配置实例
	 */
	public DescribeRoleListClient(Credential credential, String region, ClientProfile profile) {
		super(DescribeRoleListClient.endpoint, DescribeRoleListClient.version, credential, region, profile);
	}
	
	/**
	 * 获取角色列表 @author meisw 2019年6月27日 上午10:36:07 @Method: DescribeRoleList @Description: TODO @param req @return @throws TencentCloudSDKException @throws
	 */
	public DescribeRoleListResponse DescribeRoleList(DescribeRoleListRequest req) throws TencentCloudSDKException {
		JsonResponseModel<DescribeRoleListResponse> rsp = null;
		try {
			Type type = new TypeToken<JsonResponseModel<DescribeRoleListResponse>>() {}.getType();
			rsp = gson.fromJson(this.internalRequest(req, "DescribeRoleList"), type);
		} catch (JsonSyntaxException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		return rsp.response;
	}
	
}
