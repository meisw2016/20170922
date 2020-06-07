package cn.springcloud.book.eureka.util.parMo;

import java.util.List;

/**
 * 监控指标
 * @author Administrator
 *
 */
public class Par {
	
	private String parId;
	private String parName;
	private List<Mo> moList;
	
    public String getParId() {
    	return parId;
    }
	
    public void setParId(String parId) {
    	this.parId = parId;
    }
	
    public String getParName() {
    	return parName;
    }
	
    public void setParName(String parName) {
    	this.parName = parName;
    }
	
    public List<Mo> getMoList() {
    	return moList;
    }
	
    public void setMoList(List<Mo> moList) {
    	this.moList = moList;
    }
	
	
}
