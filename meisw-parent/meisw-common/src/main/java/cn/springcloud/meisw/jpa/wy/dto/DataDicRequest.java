package cn.springcloud.meisw.jpa.wy.dto;

import java.io.Serializable;

public class DataDicRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String dataName;
	private String description;
	private String sensitiveData;
	private String sourceDataStart;
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
