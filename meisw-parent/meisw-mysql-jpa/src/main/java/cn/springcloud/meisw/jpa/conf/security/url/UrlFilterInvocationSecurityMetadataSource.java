package cn.springcloud.meisw.jpa.conf.security.url;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import cn.springcloud.meisw.jpa.common.Constance;
import cn.springcloud.meisw.jpa.conf.security.MyProperties;
import cn.springcloud.meisw.jpa.dao.SysPermissionDao;
import cn.springcloud.meisw.jpa.dao.SysRoleDao;
import cn.springcloud.meisw.jpa.dao.SysRolePermissionDao;
import cn.springcloud.meisw.jpa.po.SysPermission;
import cn.springcloud.meisw.jpa.po.SysRole;
import cn.springcloud.meisw.jpa.po.SysRolePermission;

/**
 * <p>
 * 获取访问该url所需要的用户角色权限信息
 * </p>
 *
 * @author : zhengqing
 * @description : 执行完之后到 `UrlAccessDecisionManager` 中认证权限
 * @date : 2019/10/15 14:36
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	// @Autowired
	// MenuMapper menuMapper;
	// @Autowired
	// RoleMenuMapper roleMenuMapper;
	// @Autowired
	// RoleMapper roleMapper;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
	private SysRolePermissionDao sysRolePermissionDao;
	@Autowired
	MyProperties myProperties;
	
	/***
	 * 返回该url所需要的用户权限信息
	 *
	 * @param object: 储存请求url信息
	 * @return: null：标识不需要任何权限都可以访问
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 获取当前请求url
		String requestUrl = ((FilterInvocation)object).getRequestUrl();
		// TODO 忽略url请放在此处进行过滤放行
		for (String ignoreUrl : myProperties.getAuth().getIgnoreUrls()) {
			if (ignoreUrl.equals(requestUrl)) {
				return null;
			}
		}
		
		if (requestUrl.contains("/login")) {
			return null;
		}
		
		// 数据库中所有url
		// List<SysPermission> permissionList = menuMapper.selectList(null);
		List<SysPermission> permissionList = sysPermissionDao.findAll();
		// for (Menu permission : permissionList) {
		for (SysPermission permission : permissionList) {
			// 获取该url所对应的权限
			if (("/api" + permission.getUrl()).equals(requestUrl)) {
				// List<RoleMenu> permissions = roleMenuMapper.selectList(new EntityWrapper<RoleMenu>().eq("menu_id", permission.getId()));
				List<SysRolePermission> permissions = sysRolePermissionDao.queryByPermissionId(permission.getId());
				List<String> roles = new LinkedList<>();
				if (!CollectionUtils.isEmpty(permissions)) {
					permissions.forEach(e -> {
						// Integer roleId = e.getRoleId();
						// Role role = roleMapper.selectById(roleId);
						// roles.add(role.getCode());
						Long roleId = e.getRoleId();
						SysRole role = sysRoleDao.findById(roleId).get();
						roles.add(role.getCode());
						
					});
				}
				// 保存该url对应角色权限信息
				return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
			}
		}
		// 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
		return SecurityConfig.createList(Constance.ROLE_LOGIN);
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return FilterInvocation.class.isAssignableFrom(aClass);
	}
}
