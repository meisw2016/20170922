package cn.springcloud.meisw.jpa.wy.dto;

import java.io.Serializable;

public class DomainRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String domainNo;
	private String codeValue;
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

	@Override
	public String toString() {
		return "DomainRequest [id=" + id + ", domainNo=" + domainNo + ", codeValue=" + codeValue + ", codeZh=" + codeZh + "]";
	}

	public DomainRequest(Long id, String domainNo, String codeValue, String codeZh) {
		super();
		this.id = id;
		this.domainNo = domainNo;
		this.codeValue = codeValue;
		this.codeZh = codeZh;
	}

	public DomainRequest() {
		super();
	}
	
	
}
