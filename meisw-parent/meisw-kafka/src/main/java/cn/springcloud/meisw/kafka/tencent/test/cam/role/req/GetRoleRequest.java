package cn.springcloud.meisw.kafka.tencent.test.cam.role.req;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

public class GetRoleRequest extends AbstractModel {
	
	@SerializedName("RoleId")
	@Expose
	private String RoleId;
	@SerializedName("RoleName")
	@Expose
	private String RoleName;
	
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
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix+"RoleId", RoleId);
		this.setParamSimple(map, prefix+"RoleName", RoleName);
	}
	
}
