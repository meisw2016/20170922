package cn.springcloud.meisw.kafka.tencent.test.service.client;

import java.lang.reflect.Type;

import cn.springcloud.meisw.kafka.tencent.test.service.req.CreateServiceRequest;
import cn.springcloud.meisw.kafka.tencent.test.service.resp.CreateServiceResponse;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.JsonResponseModel;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;

public class CreateServiceClient extends AbstractClient {
	
	private static String endpoint = "cam.tencentcloudapi.com";
	private static String version = "2019-01-16";
	
	public CreateServiceClient(Credential credential, String region) {
		this(credential, region, new ClientProfile());
	}
	
	public CreateServiceClient(Credential credential, String region, ClientProfile profile) {
		super(CreateServiceClient.endpoint, CreateServiceClient.version, credential, region, profile);
	}
	
	/**
	 * 
	 * @author meisw 2019年6月27日 下午6:20:39
	 * @Method: ReleaseService 
	 * @Description: 创建服务
	 * @param req
	 * @return
	 * @throws TencentCloudSDKException 
	 * @throws
	 */
	public CreateServiceResponse CreateService(CreateServiceRequest req) throws TencentCloudSDKException {
		JsonResponseModel<CreateServiceResponse> rsp = null;
		try {
			Type type = new TypeToken<JsonResponseModel<CreateServiceResponse>>() {}.getType();
			rsp = gson.fromJson(this.internalRequest(req, "CreateService"), type);
		} catch (JsonSyntaxException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		return rsp.response;
	}
}
