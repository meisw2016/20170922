package cn.springcloud.meisw.jpa.db1.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <p>
 * </p>
 * @author meisw 2019年11月6日 上午10:40:31
 * @ClassName P8FieldMapping
 * @Description 映射P8字段
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月6日
 * @modify by reason:{方法名}:{原因}
 */
@Entity
@Table(name = "P8_FIELD_MAPPING")
public class P8FieldMapping implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	/**
	 * 数据像编号
	 */
	@Column(name = "data_no", nullable = false)
	private String dataNo;
	/**
	 * 环境表示
	 */
	@Column(name = "env_code", nullable = false)
	private String envCode;
	/**
	 * 英文简称
	 */
	@Column(name = "abbreviation", nullable = false)
	private String abbreviation;
	/**
	 * 中文名称
	 */
	@Column(name = "cn_name", nullable = false)
	private String cnName;
	/**
	 * 业务目的
	 */
	@Column(name = "business_purpose")
	private String businessPurpose;
	/**
	 * 业务定义
	 */
	@Column(name = "bussiness_def", nullable = false)
	private String businessDef;
	/**
	 * 数据格式
	 */
	@Column(name = "data_format", nullable = false)
	private String dataFormat;
	/**
	 * 有效阈值
	 */
	@Column(name = "effective_threshold", nullable = false)
	private String effectiveThreshold;
	/**
	 * JAVA规范命名
	 */
	@Column(name = "specific_name")
	private String specificName;
	/**
	 * 域编号
	 */
	@Column(name = "region_no")
	private String regionNo;
	
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
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
