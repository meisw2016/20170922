package cn.springcloud.book.eureka.tencent.test.service.resp;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;
/**
 * 
 * <p></p>
 * @author meisw 2019年6月27日 下午4:21:16
 * @ClassName ReleaseServiceResponse
 * @Description 发布服务响应对象
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年6月27日
 * @modify by reason:{方法名}:{原因}
 */
public class ReleaseServiceResponse extends AbstractModel {
	
	/**
	 * 公共错误码：0表示成功，其他值表示失败
	 */
	@SerializedName("Code")
	@Expose
	private Integer Code;
	
	/**
	 * 业务侧错误码。成功时返回Success
	 */
	@SerializedName("CodeDesc")
	@Expose
	private String CodeDesc;
	
	/**
	 * 模块错误信息描述，与接口相关
	 */
	@SerializedName("Message")
	@Expose
	private String Message;
	
	/**
	 * 服务发布时间，后续可以按此时间回滚
	 */
	@SerializedName("ReleaseTime")
	@Expose
	private String ReleaseTime;
	
	/**
	 * 发布时的备注信息填写
	 */
	@SerializedName("ReleaseDesc")
	@Expose
	private String ReleaseDesc;
	
	@Override
	protected void toMap(HashMap<String, String> map, String prefix) {
		this.setParamSimple(map, prefix + "Code", "Code");
		this.setParamSimple(map, prefix + "CodeDesc", "CodeDesc");
		this.setParamSimple(map, prefix + "Message", "Message");
		this.setParamSimple(map, prefix + "ReleaseTime", "ReleaseTime");
		this.setParamSimple(map, prefix + "ReleaseDesc", "ReleaseDesc");
	}
	
}
