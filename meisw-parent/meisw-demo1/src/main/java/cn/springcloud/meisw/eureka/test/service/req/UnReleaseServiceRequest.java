package cn.springcloud.meisw.eureka.test.service.req;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月28日 上午9:31:45
 * @ClassName UnReleaseServiceRequest
 * @Description 下线服务请求对象
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月28日
 * @modify by reason:{方法名}:{原因}
 */
public class UnReleaseServiceRequest extends AbstractModel {
	
	/**
	 * 待下线服务的唯一ID
	 */
	@SerializedName("ServiceId")
	@Expose
	private String ServiceId;
	/**
	 * 待下线的环境名称，当前支持三个环境，测试：test，预发：prepub，线上：release
	 */
	@SerializedName("EnvironmentName")
	@Expose
	private String EnvironmentName;
	/**
	 * 本次的下线描述
	 */
	@SerializedName("UnReleaseDesc")
	@Expose
	private String UnReleaseDesc;
	
	public String getServiceId() {
		return ServiceId;
	}
	
	public void setServiceId(String serviceId) {
		ServiceId = serviceId;
	}
	
	public String getEnvironmentName() {
		return EnvironmentName;
	}
	
	public void setEnvironmentName(String environmentName) {
		EnvironmentName = environmentName;
	}
	
	public String getUnReleaseDesc() {
		return UnReleaseDesc;
	}
	
	public void setUnReleaseDesc(String unReleaseDesc) {
		UnReleaseDesc = unReleaseDesc;
	}
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "ServiceId", "ServiceId");
		this.setParamSimple(map, prefix + "EnvironmentName", "EnvironmentName");
		this.setParamSimple(map, prefix + "UnReleaseDesc", "UnReleaseDesc");
	}
}
