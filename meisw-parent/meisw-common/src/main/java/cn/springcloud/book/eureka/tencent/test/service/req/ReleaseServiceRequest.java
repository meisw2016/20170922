package cn.springcloud.book.eureka.tencent.test.service.req;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;
/**
 * 
 * <p></p>
 * @author meisw 2019年6月27日 下午4:13:54
 * @ClassName ReleaseServiceRequest
 * @Description 发布服务请求对象
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class ReleaseServiceRequest extends AbstractModel {
	
	/**
	 * 待发布服务的唯一ID
	 */
	@SerializedName("ServiceId")
	@Expose
	private String ServiceId;
	/**
	 * 待发布的环境名称，当前支持三个环境，测试：test，预发：prepub，线上：release
	 */
	@SerializedName("EnvironmentName")
	@Expose
	private String EnvironmentName;
	/**
	 * 本次的发布描述
	 */
	@SerializedName("ReleaseDesc")
	@Expose
	private String ReleaseDesc;
	
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
	
	public String getReleaseDesc() {
		return ReleaseDesc;
	}
	
	public void setReleaseDesc(String releaseDesc) {
		ReleaseDesc = releaseDesc;
	}
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "ServiceId", "ServiceId");
		this.setParamSimple(map, prefix + "EnvironmentName", "EnvironmentName");
		this.setParamSimple(map, prefix + "ReleaseDesc", "ReleaseDesc");
	}
	
}
