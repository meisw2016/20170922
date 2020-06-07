package cn.springcloud.book.eureka.util.parMo;

import net.sf.json.JSONObject;

/**
 * 监控对象
 * @author Administrator
 *
 */
public class Mo {
	
	private String moId;
	private String moName;
	private JSONObject json;
	
    public String getMoId() {
    	return moId;
    }
	
    public void setMoId(String moId) {
    	this.moId = moId;
    }
	
    public String getMoName() {
    	return moName;
    }
	
    public void setMoName(String moName) {
    	this.moName = moName;
    }
	
    public JSONObject getJson() {
    	return json;
    }
	
    public void setJson(JSONObject json) {
    	this.json = json;
    }
	
	
}
