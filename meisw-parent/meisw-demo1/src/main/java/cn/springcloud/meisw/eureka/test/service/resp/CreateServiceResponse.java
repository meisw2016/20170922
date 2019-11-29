package cn.springcloud.meisw.eureka.test.service.resp;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

public class CreateServiceResponse extends AbstractModel {
	
	/**
	 * 公共错误码,0:表示成功，其他值表示失败
	 */
	@SerializedName("Code")
	@Expose
	private Integer Code;
	
	/**
	 * 业务侧错误码
	 */
	@SerializedName("CodeDesc")
	@Expose
	private String CodeDesc;
	
	/**
	 * 模块错误
	 */
	@SerializedName("Message")
	@Expose
	private String Message;
	
	/**
	 * 服务名
	 */
	@SerializedName("ServiceName")
	@Expose
	private String ServiceName;
	
	/**
	 * 服务描述
	 */
	@SerializedName("ServiceDesc")
	@Expose
	private String ServiceDesc;
	
	/**
	 * 服务的域名（由系统自动生成）
	 */
	@SerializedName("SubDomain")
	@Expose
	private String SubDomain;
	
	/**
	 * 服务唯一ID，由系统生成，全局唯一
	 */
	@SerializedName("ServiceId")
	@Expose
	private String ServiceId;
	
	/**
	 * 创建时间
	 */
	@SerializedName("CreateTime")
	@Expose
	private String CreateTime;
	
	public Integer getCode() {
		return Code;
	}
	
	public void setCode(Integer code) {
		Code = code;
	}
	
	public String getCodeDesc() {
		return CodeDesc;
	}
	
	public void setCodeDesc(String codeDesc) {
		CodeDesc = codeDesc;
	}
	
	public String getMessage() {
		return Message;
	}
	
	public void setMessage(String message) {
		Message = message;
	}
	
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
	
	public String getSubDomain() {
		return SubDomain;
	}
	
	public void setSubDomain(String subDomain) {
		SubDomain = subDomain;
	}
	
	public String getServiceId() {
		return ServiceId;
	}
	
	public void setServiceId(String serviceId) {
		ServiceId = serviceId;
	}
	
	public String getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "Code", "Code");
		this.setParamSimple(map, prefix + "CodeDesc", "CodeDesc");
		this.setParamSimple(map, prefix + "Message", "Message");
		this.setParamSimple(map, prefix + "ServiceName", "ServiceName");
		this.setParamSimple(map, prefix + "ServiceDesc", "ServiceDesc");
		this.setParamSimple(map, prefix + "SubDomain", "SubDomain");
		this.setParamSimple(map, prefix + "ServiceId", "ServiceId");
		this.setParamSimple(map, prefix + "CreateTime", "CreateTime");
	}
}
