package cn.springcloud.meisw.eureka.test.cam.role.resp;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

public class DescribeRoleListResponse extends AbstractModel {
	
	@SerializedName("TotalNum")
	@Expose
	private Integer TotalNum;
	
	@SerializedName("List")
	@Expose
	private Role[] List;
	
	public Integer getTotalNum() {
		return TotalNum;
	}
	
	public void setTotalNum(Integer totalNum) {
		TotalNum = totalNum;
	}
	
	public Role[] getList() {
		return List;
	}
	
	public void setList(Role[] list) {
		this.List = list;
	}
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "TotalNum", this.TotalNum);
		this.setParamArrayObj(map, prefix + "List.", this.List);
	}
}
