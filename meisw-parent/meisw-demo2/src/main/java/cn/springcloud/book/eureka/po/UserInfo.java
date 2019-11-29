package cn.springcloud.book.eureka.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息
 * @author meisw 2019-11-29
 */
@Entity
@Table(name = "userinfo")
@ApiModel("userinfo")
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("用户ID")
	@Id
	@GeneratedValue
	/**
	 * 用户id
	 */
	private Integer userId;
	
	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String username;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date addtime;
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getAddtime() {
		return addtime;
	}
	
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", username=" + username + ", addtime=" + addtime + "]";
	}
	
}
