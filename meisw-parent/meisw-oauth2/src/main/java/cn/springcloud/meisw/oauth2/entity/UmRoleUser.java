package cn.springcloud.meisw.oauth2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "um_t_role_user")
public class UmRoleUser implements Serializable {
	
    private static final long serialVersionUID = -8511248937296690096L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "user_id")
	private Long userId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "UmRoleUser [id=" + id + ", roleId=" + roleId + ", userId=" + userId + "]";
	}
	
}
