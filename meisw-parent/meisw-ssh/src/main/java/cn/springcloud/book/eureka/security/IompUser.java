package cn.springcloud.book.eureka.security;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p></p>
 * @author meisw 2019年7月1日 下午4:37:38
 * @ClassName IompUser
 * @Description 定义Authentication类，扩展shiro框架的Subject使得可以包含除用户名外更多的信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年7月1日
 * @modify by reason:{方法名}:{原因}
 */
public class IompUser implements Serializable{
	
	private static final String SUPER_USER_ROLE_CODE = "superAdmin";//超级管理员角色
	private Long userId;//用户ID
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
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
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
	
	public String getUserRoleInfo() {
		StringBuilder roleStr = new StringBuilder();
		if(roleNameList != null) {
			for(String roleName : roleNameList) {
				if(roleStr.length()!= 0) {
					roleStr.append(",");
				}
				roleStr.append(roleName);
			}
		}
		return roleStr.toString();
	}
	
	/**
	 * 
	 * @author meisw 2019年7月1日 下午4:54:18
	 * @Method: isSuperUser 
	 * @Description: 判断当前用户是否为超级管理员用户
	 * @return 
	 * @throws
	 */
	public boolean isSuperUser() {
		boolean isSuperUser = false;
		if(roleCodeList != null) {
			for(String roleCode : roleCodeList) {
				if(SUPER_USER_ROLE_CODE.equals(roleCode)) {
					isSuperUser = true;
					break;
				}
			}
		}
		return isSuperUser;
	}
}
