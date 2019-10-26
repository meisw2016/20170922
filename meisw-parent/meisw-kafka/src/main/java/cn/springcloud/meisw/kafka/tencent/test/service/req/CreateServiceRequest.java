package cn.springcloud.meisw.kafka.tencent.test.service.req;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;
/**
 * 
 * <p></p>
 * @author meisw 2019年6月27日 下午4:46:37
 * @ClassName CreateServiceRequest
 * @Description 创建服务
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class CreateServiceRequest extends AbstractModel {
	
	/**
	 * 用户自定义的服务名称,如果没传,则系统自动生成一个唯一名称
	 */
	@SerializedName("ServiceName")
	@Expose
	private String ServiceName;
	
	/**
	 * 用户自动有的服务描述
	 */
	@SerializedName("ServiceDesc")
	@Expose
	private String ServiceDesc;
	
	/**
	 * 服务的前端请求类型：如：HTTP,HTTPS,
	 */
	@SerializedName("Protocol")
	@Expose
	private String Protocol;
	
	public String getServiceName() {
		return ServiceName;
	}
	
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	
	public String getServiceDesc() {
		return ServiceDesc;
	}
	
	public void setServiceDesc(String serviceDesc) {
		ServiceDesc = serviceDesc;
	}
	
	public String getProtocol() {
		return Protocol;
	}
	
	public void setProtocol(String protocol) {
		Protocol = protocol;
	}
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "ServiceName", ServiceName);
		this.setParamSimple(map, prefix + "ServiceDesc", ServiceDesc);
		this.setParamSimple(map, prefix + "Protocol", Protocol);
	}
	
}
