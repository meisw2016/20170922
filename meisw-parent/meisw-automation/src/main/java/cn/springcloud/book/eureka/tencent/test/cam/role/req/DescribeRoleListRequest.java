package cn.springcloud.book.eureka.tencent.test.cam.role.req;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;

import cn.springcloud.book.eureka.tencent.test.cam.role.resp.Role;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年6月27日 上午9:17:00
 * @ClassName DescribeRoleListRequest
 * @Description 角色列表请求对象
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class DescribeRoleListRequest extends AbstractModel {
	
	/**
	 * 页码,从1开始
	 */
	@SerializedName("page")
	@Expose
	private Integer page;
	/**
	 * 每页大小,不能大于200
	 */
	@SerializedName("rp")
	@Expose
	private Integer rp;
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getRp() {
		return rp;
	}
	
	public void setRp(Integer rp) {
		this.rp = rp;
	}
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "page", this.page);
		this.setParamSimple(map, prefix + "rp", this.rp);
	}
	
}
