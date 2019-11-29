package cn.springcloud.meisw.eureka.test.cam.role.resp;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

public class Role extends AbstractModel {
	
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
		this.setParamSimple(map, prefix + "roleId", this.RoleId);
		this.setParamSimple(map, prefix + "roleName", this.RoleName);
		this.setParamSimple(map, prefix + "policyDocument", this.PolicyDocument);
		this.setParamSimple(map, prefix + "description", this.Description);
		this.setParamSimple(map, prefix + "addTime", this.AddTime);
		this.setParamSimple(map, prefix + "updateTime", this.UpdateTime);
	}
}
