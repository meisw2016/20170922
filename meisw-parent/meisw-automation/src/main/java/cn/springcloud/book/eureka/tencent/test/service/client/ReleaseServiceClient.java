package cn.springcloud.book.eureka.tencent.test.service.client;

import java.lang.reflect.Type;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.JsonResponseModel;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;

import cn.springcloud.book.eureka.tencent.test.service.req.ReleaseServiceRequest;
import cn.springcloud.book.eureka.tencent.test.service.resp.ReleaseServiceResponse;

public class ReleaseServiceClient extends AbstractClient {
	
	private static String endpoint = "cam.tencentcloudapi.com";
	private static String version = "2019-01-16";
	
	public ReleaseServiceClient(Credential credential, String region) {
		this(credential, region, new ClientProfile());
	}
	
	public ReleaseServiceClient(Credential credential, String region, ClientProfile profile) {
		super(ReleaseServiceClient.endpoint, ReleaseServiceClient.version, credential, region, profile);
	}
	
	/**
	 * 
	 * @author meisw 2019年6月27日 下午4:30:25
	 * @Method: ReleaseService 
	 * @Description: 服务发布
	 * @param req
	 * @return
	 * @throws TencentCloudSDKException 
	 * @throws
	 */
	public ReleaseServiceResponse ReleaseService(ReleaseServiceRequest req)throws TencentCloudSDKException {
		JsonResponseModel<ReleaseServiceResponse> rsp = null;
		try {
			Type type = new TypeToken<JsonResponseModel<ReleaseServiceResponse>>() {}.getType();
			rsp = gson.fromJson(this.internalRequest(req, "ReleaseService"), type);
		}catch(JsonSyntaxException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		return rsp.response;
	}
}
