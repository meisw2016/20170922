package cn.springcloud.meisw.jpa.po;

import java.io.Serializable;
/**
 * 权限表
 * <p>
 * </p>
 * @author meisw 2019年11月14日 下午2:14:03
 * @ClassName SysPermission
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月14日
 * @modify by reason:{方法名}:{原因}
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_sys_permission")
public class SysPermission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "url")
	private String url;
	@Column(name = "gmt_create")
	private Date gmtCreate;
	@Column(name = "gmt_modified")
	private Date gmtModified;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
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
