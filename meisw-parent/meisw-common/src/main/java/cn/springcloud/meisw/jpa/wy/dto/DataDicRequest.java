package cn.springcloud.meisw.jpa.wy.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "DataDicRequest",description="字典请求实体")
public class DataDicRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键ID",name = "id",required = false,example="idddddd")
	private Long id;
	@ApiModelProperty(value = "dataName",name = "dataName",required = false,example="dataName")
	private String dataName;
	@ApiModelProperty(value = "description",name = "description",required = false,example="description")
	private String description;
	@ApiModelProperty(value = "sensitiveData",name = "sensitiveData",required = false,example="sensitiveData")
	private String sensitiveData;
	@ApiModelProperty(value = "sourceDataStart",name = "sourceDataStart",required = false,example="sourceDataStart")
	private String sourceDataStart;
	@ApiModelProperty(value = "sourceDataLast",name = "sourceDataLast",required = false,example="sourceDataLast")
	private String sourceDataLast;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataName() {
		return dataName;
	}
	
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSensitiveData() {
		return sensitiveData;
	}
	
	public void setSensitiveData(String sensitiveData) {
		this.sensitiveData = sensitiveData;
	}
	
	public String getSourceDataStart() {
		return sourceDataStart;
	}
	
	public void setSourceDataStart(String sourceDataStart) {
		this.sourceDataStart = sourceDataStart;
	}
	
	public String getSourceDataLast() {
		return sourceDataLast;
	}
	
	public void setSourceDataLast(String sourceDataLast) {
		this.sourceDataLast = sourceDataLast;
	}
	
	@Override
	public String toString() {
		return "DataDicRequest [id=" + id + ", dataName=" + dataName + ", description=" + description + ", sensitiveData="
		        + sensitiveData + ", sourceDataStart=" + sourceDataStart + ", sourceDataLast=" + sourceDataLast + "]";
	}
	
}
