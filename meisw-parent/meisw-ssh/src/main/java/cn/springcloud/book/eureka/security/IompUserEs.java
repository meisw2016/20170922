package cn.springcloud.book.eureka.security;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
 * <p></p>
 * @author meisw 2019年7月2日 上午8:44:03
 * @ClassName IompUserEs
 * @Description ES查询对象
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年7月2日
 * @modify by reason:{方法名}:{原因}
 */
@Document(indexName = "coreqi2", type = "user2")
public class IompUserEs implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//ES主键ID
	private String userId;//用户ID
	private String loginName;//用户登录名称
	private String userName;//用户名称
	private String password;//密码
	private String orgCode;//机构编号
	private String orgName;//用户所属机构
	private boolean isSpecialUser = false;//是否为特权用户
	private boolean isLoginSucceed = false;//登录是否成功
	
	private List<Long> roleIdList ;//用户角色ID列表
	private List<String> roleCodeList;//用户角色编码列表
	private List<String> roleNameList;//用户角色名称列表
	private List<Long> sysIdList;//用户所属系统ID列表
	
	private List<String> sysNameList;//用户所属系统名称列表
	private List<Long> userGrpIdList;//用户组ID列表
	private List<String> userGrpNameList;//用户组名称列表
	private Map<String,List<Long>> permissionResMap;//当前用户的权限资源ID集合，主要用于数据权限控制
	private List<String> permissionExpriList;//用户权限表达式列表，用于访问控制
	private String ticket;//统一认证服务的服务票据
	private List<String> permissionResNameList;//当前用户权限路径集合，用户有访问权限控制
	private List<String> registerFuncUrlList;
	
	public IompUserEs() {}
	
	public IompUserEs(String id,String loginName,String password) {
		this.id = id;
		this.loginName = loginName;
		this.password = password;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public boolean isSpecialUser() {
		return isSpecialUser;
	}
	
	public void setSpecialUser(boolean isSpecialUser) {
		this.isSpecialUser = isSpecialUser;
	}
	
	public boolean isLoginSucceed() {
		return isLoginSucceed;
	}
	
	public void setLoginSucceed(boolean isLoginSucceed) {
		this.isLoginSucceed = isLoginSucceed;
	}
	
	public List<Long> getRoleIdList() {
		return roleIdList;
	}
	
	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	public List<String> getRoleCodeList() {
		return roleCodeList;
	}
	
	public void setRoleCodeList(List<String> roleCodeList) {
		this.roleCodeList = roleCodeList;
	}
	
	public List<String> getRoleNameList() {
		return roleNameList;
	}
	
	public void setRoleNameList(List<String> roleNameList) {
		this.roleNameList = roleNameList;
	}
	
	public List<Long> getSysIdList() {
		return sysIdList;
	}
	
	public void setSysIdList(List<Long> sysIdList) {
		this.sysIdList = sysIdList;
	}
	
	public List<String> getSysNameList() {
		return sysNameList;
	}
	
	public void setSysNameList(List<String> sysNameList) {
		this.sysNameList = sysNameList;
	}
	
	public List<Long> getUserGrpIdList() {
		return userGrpIdList;
	}
	
	public void setUserGrpIdList(List<Long> userGrpIdList) {
		this.userGrpIdList = userGrpIdList;
	}
	
	public List<String> getUserGrpNameList() {
		return userGrpNameList;
	}
	
	public void setUserGrpNameList(List<String> userGrpNameList) {
		this.userGrpNameList = userGrpNameList;
	}
	
	public Map<String, List<Long>> getPermissionResMap() {
		return permissionResMap;
	}
	
	public void setPermissionResMap(Map<String, List<Long>> permissionResMap) {
		this.permissionResMap = permissionResMap;
	}
	
	public List<String> getPermissionExpriList() {
		return permissionExpriList;
	}
	
	public void setPermissionExpriList(List<String> permissionExpriList) {
		this.permissionExpriList = permissionExpriList;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public List<String> getPermissionResNameList() {
		return permissionResNameList;
	}
	
	public void setPermissionResNameList(List<String> permissionResNameList) {
		this.permissionResNameList = permissionResNameList;
	}
	
	public List<String> getRegisterFuncUrlList() {
		return registerFuncUrlList;
	}
	
	public void setRegisterFuncUrlList(List<String> registerFuncUrlList) {
		this.registerFuncUrlList = registerFuncUrlList;
	}

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IompUserEs [id=" + id + ", userId=" + userId + ", loginName=" + loginName + ", userName=" + userName
		        + ", password=" + password + ", orgCode=" + orgCode + ", orgName=" + orgName + ", isSpecialUser="
		        + isSpecialUser + ", isLoginSucceed=" + isLoginSucceed + ", roleIdList=" + roleIdList + ", roleCodeList="
		        + roleCodeList + ", roleNameList=" + roleNameList + ", sysIdList=" + sysIdList + ", sysNameList=" + sysNameList
		        + ", userGrpIdList=" + userGrpIdList + ", userGrpNameList=" + userGrpNameList + ", permissionResMap="
		        + permissionResMap + ", permissionExpriList=" + permissionExpriList + ", ticket=" + ticket
		        + ", permissionResNameList=" + permissionResNameList + ", registerFuncUrlList=" + registerFuncUrlList + "]";
	}

	
	
}
