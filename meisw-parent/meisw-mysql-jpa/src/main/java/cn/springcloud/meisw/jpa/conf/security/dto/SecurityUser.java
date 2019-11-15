package cn.springcloud.meisw.jpa.conf.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import cn.springcloud.meisw.jpa.po.SysRole;
import cn.springcloud.meisw.jpa.po.SysUser;

/**
 * <p>
 * 安全认证用户详情
 * </p>
 *
 * @description :
 * @date : 2019/10/14 17:46
 */
public class SecurityUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 当前登录用户
	 */
	private transient SysUser currentUserInfo;
	/**
	 * 角色
	 */
	private transient List<SysRole> roleList;
	/**
	 * 当前用户所拥有角色代码
	 */
	private transient String roleCodes;
	
	public SecurityUser() {
	}
	
	public SecurityUser(SysUser user) {
		if (user != null) {
			this.currentUserInfo = user;
		}
	}
	
	public SecurityUser(SysUser user, List<SysRole> roleList) {
		if (user != null) {
			this.currentUserInfo = user;
			this.roleList = roleList;
			if (!CollectionUtils.isEmpty(roleList)) {
				StringJoiner roleCodes = new StringJoiner(",", "[", "]");
				roleList.forEach(e -> roleCodes.add(e.getCode()));
				this.roleCodes = roleCodes.toString();
			}
		}
	}
	
	/**
	 * 获取当前用户所具有的角色
	 *
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (!CollectionUtils.isEmpty(this.roleList)) {
			for (SysRole role : this.roleList) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
				authorities.add(authority);
			}
		}
		// SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
		// authorities.add(authority);
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return currentUserInfo.getPassword();
	}
	
	@Override
	public String getUsername() {
		return currentUserInfo.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public SysUser getCurrentUserInfo() {
		return currentUserInfo;
	}
	
	public void setCurrentUserInfo(SysUser currentUserInfo) {
		this.currentUserInfo = currentUserInfo;
	}
	
	public List<SysRole> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
	public String getRoleCodes() {
		return roleCodes;
	}
	
	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}
	
}
