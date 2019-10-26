package cn.springcloud.meisw.kafka.tencent.test.cam.role.resp;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

/**
 * 获取指定角色的相信信息
 * <p>
 * </p>
 * @author meisw 2019年6月27日 上午10:39:53
 * @ClassName GetRoleResponse
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class GetRoleResponse extends AbstractModel {
	
	/**
	 * 角色ID
	 */
	@SerializedName("RoleId")
	@Expose
	private String RoleId;
	/**
	 * 角色名
	 */
	@SerializedName("RoleName")
	@Expose
	private String RoleName;
	/**
	 * 角色信任策略
	 */
	@SerializedName("PolicyDocument")
	@Expose
	private String PolicyDocument;
	/**
	 * 角色描述
	 */
	@SerializedName("Description")
	@Expose
	private String Description;
	/**
	 * 角色创建时间
	 */
	@SerializedName("AddTime")
	@Expose
	private String AddTime;
	/**
	 * 角色最近修改时间
	 */
	@SerializedName("UpdateTime")
	@Expose
	private String UpdateTime;
	
	@SerializedName("ConsoleLogin")
	@Expose
	private Integer ConsoleLogin;
	
	public String getRoleId() {
		return RoleId;
	}
	
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	
	public String getRoleName() {
		return RoleName;
	}
	
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	
	public String getPolicyDocument() {
		return PolicyDocument;
	}
	
	public void setPolicyDocument(String policyDocument) {
		PolicyDocument = policyDocument;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getAddTime() {
		return AddTime;
	}
	
	public void setAddTime(String addTime) {
		AddTime = addTime;
	}
	
	public String getUpdateTime() {
		return UpdateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	
	public Integer getConsoleLogin() {
		return ConsoleLogin;
	}
	
	public void setConsoleLogin(Integer consoleLogin) {
		ConsoleLogin = consoleLogin;
	}
	
	/**
	 * 内部实现,用户禁止调用
	 * @author meisw 2019年6月27日 上午9:31:28
	 * @Method: toMap
	 * @Description: TODO
	 * @param map
	 * @param prefix
	 * @see com.tencentcloudapi.common.AbstractModel#toMap(java.util.HashMap, java.lang.String)
	 */
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "RoleId", this.RoleId);
		this.setParamSimple(map, prefix + "RoleName", this.RoleName);
		this.setParamSimple(map, prefix + "PolicyDocument", this.PolicyDocument);
		this.setParamSimple(map, prefix + "Description", this.Description);
		this.setParamSimple(map, prefix + "AddTime", this.AddTime);
		this.setParamSimple(map, prefix + "UpdateTime", this.UpdateTime);
		this.setParamSimple(map, prefix + "ConsoleLogin", this.ConsoleLogin);
	}
}
