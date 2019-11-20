package cn.springcloud.book.eureka.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
/**
 * 文档
 * <p></p>
 * @author meisw 2019年7月1日 下午3:25:17
 * @ClassName EsUser
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年7月1日
 * @modify by reason:{方法名}:{原因}
 */
@Document(indexName = "coreqi", type = "user")
public class EsUser {
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	private String username;
	private String password;
	private Integer enabled;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "EsUser [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

	public EsUser() {
		super();
	}

	public EsUser(String id, String username, String password, Integer enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
}
