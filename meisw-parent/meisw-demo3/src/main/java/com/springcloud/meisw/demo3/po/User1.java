package com.springcloud.meisw.demo3.po;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "user")
public class User1 implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 用户在小程序中的唯一识别id
     */
    @Column(name = "open_id")
    private String openId;
    /**
     * 用户在同一主体的多个小程序中的唯一识别id
     */
    @Column(name = "union_id")
    private String unionId;
    /**
     * 用户来源，从分享进来、直接搜索、从推广的渠道进来等
     */
    private Integer source = 0;
    /**
     * 用户第一次登录的时间
     */
    @Column(name = "first_login_time", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private String firstLoginTime;
    /**
     * 用户最后一次登录时间
     */
    @Column(name = "last_login_time", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private String lastLoginTime;
    /**
     * 所属appId
     */
    @Column(name = "app_id")
    private String appId;
    /**
     * 小程序名称
     */
    @Column(name = "app_name")
    private String appName;
	
    public Integer getId() {
    	return id;
    }
	
    public void setId(Integer id) {
    	this.id = id;
    }
	
    public String getOpenId() {
    	return openId;
    }
	
    public void setOpenId(String openId) {
    	this.openId = openId;
    }
	
    public String getUnionId() {
    	return unionId;
    }
	
    public void setUnionId(String unionId) {
    	this.unionId = unionId;
    }
	
    public Integer getSource() {
    	return source;
    }
	
    public void setSource(Integer source) {
    	this.source = source;
    }
	
    public String getFirstLoginTime() {
    	return firstLoginTime;
    }
	
    public void setFirstLoginTime(String firstLoginTime) {
    	this.firstLoginTime = firstLoginTime;
    }
	
    public String getLastLoginTime() {
    	return lastLoginTime;
    }
	
    public void setLastLoginTime(String lastLoginTime) {
    	this.lastLoginTime = lastLoginTime;
    }
	
    public String getAppId() {
    	return appId;
    }
	
    public void setAppId(String appId) {
    	this.appId = appId;
    }
	
    public String getAppName() {
    	return appName;
    }
	
    public void setAppName(String appName) {
    	this.appName = appName;
    }

    
}

