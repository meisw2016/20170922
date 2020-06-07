package cn.springcloud.meisw.jpa.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色-权限表
 * <p>
 * </p>
 * @author meisw 2019年11月14日 下午2:19:51
 * @ClassName SysRolePermission
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月14日
 * @modify by reason:{方法名}:{原因}
 */
@Entity
@Table(name = "t_sys_role_permission")
public class SysRolePermission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "permission_id")
	private Long permissionId;
	@Column(name = "gmt_create")
	private Date gmtCreate;
	@Column(name = "gmt_modified")
	private Date gmtModified;
	
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
	
	public Long getPermissionId() {
		return permissionId;
	}
	
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	public Date getGmtModified() {
		return gmtModified;
	}
	
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}
