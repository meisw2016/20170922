package cn.springcloud.meisw.jpa.wy.dto;

import java.io.Serializable;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年11月6日 下午2:11:01
 * @ClassName P8FieldMappingResponse
 * @Description P8映射响应实体
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月6日
 * @modify by reason:{方法名}:{原因}
 */
public class P8FieldMappingResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String dataNo;
	private String envCode;
	private String abbreviation;
	private String cnName;
	private String businessPurpose;
	private String businessDef;
	private String dataFormat;
	private String effectiveThreshold;
	private String specificName;
	private String regionNo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataNo() {
		return dataNo;
	}
	
	public void setDataNo(String dataNo) {
		this.dataNo = dataNo;
	}
	
	public String getEnvCode() {
		return envCode;
	}
	
	public void setEnvCode(String envCode) {
		this.envCode = envCode;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public String getCnName() {
		return cnName;
	}
	
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	public String getBusinessPurpose() {
		return businessPurpose;
	}
	
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}
	
	public String getBusinessDef() {
		return businessDef;
	}
	
	public void setBusinessDef(String businessDef) {
		this.businessDef = businessDef;
	}
	
	public String getDataFormat() {
		return dataFormat;
	}
	
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
	public String getEffectiveThreshold() {
		return effectiveThreshold;
	}
	
	public void setEffectiveThreshold(String effectiveThreshold) {
		this.effectiveThreshold = effectiveThreshold;
	}
	
	public String getSpecificName() {
		return specificName;
	}
	
	public void setSpecificName(String specificName) {
		this.specificName = specificName;
	}
	
	public String getRegionNo() {
		return regionNo;
	}
	
	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
	}
	
}
