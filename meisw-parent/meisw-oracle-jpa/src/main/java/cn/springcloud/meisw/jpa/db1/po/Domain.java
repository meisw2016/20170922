package cn.springcloud.meisw.jpa.db1.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "do_main")
public class Domain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 域编号
	 */
	@Column(name = "domain_no")
	private String domainNo;
	/**
	 * 代码取值
	 */
	@Column(name = "code_value")
	private String codeValue;
	/**
	 * 代码中文名
	 */
	@Column(name = "code_zh")
	private String codeZh;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDomainNo() {
		return domainNo;
	}
	
	public void setDomainNo(String domainNo) {
		this.domainNo = domainNo;
	}
	
	public String getCodeValue() {
		return codeValue;
	}
	
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	
	public String getCodeZh() {
		return codeZh;
	}
	
	public void setCodeZh(String codeZh) {
		this.codeZh = codeZh;
	}
	
}
