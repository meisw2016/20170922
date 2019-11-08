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
 * @author meisw 2019年11月6日 上午10:36:31
 * @ClassName DataDic
 * @Description 数据字典
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月6日
 * @modify by reason:{方法名}:{原因}
 */
@Entity
@Table(name = "data_dic")
public class DataDic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 数据项名称
	 */
	@Column(name = "data_name")
	private String dataName;
	/**
	 * 字段描述
	 */
	@Column(name = "description")
	private String description;
	/**
	 * 是否敏感数据：0：是，1：否
	 */
	@Column(name = "sensitive_data")
	private String sensitiveData;
	
	/**
	 * 源数据起始保留位数
	 */
	@Column(name = "source_data_start")
	private String sourceDataStart;
	/**
	 * 源数据结尾保留位数
	 */
	@Column(name = "source_data_last")
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

	public DataDic(Long id, String dataName, String description, String sensitiveData, String sourceDataStart,
	        String sourceDataLast) {
		super();
		this.id = id;
		this.dataName = dataName;
		this.description = description;
		this.sensitiveData = sensitiveData;
		this.sourceDataStart = sourceDataStart;
		this.sourceDataLast = sourceDataLast;
	}
	public DataDic() {
		super();
	}

}
