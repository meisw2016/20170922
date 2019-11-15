package cn.springcloud.meisw.jpa.conf.security.url;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import cn.springcloud.meisw.jpa.common.Constance;

/**
 * <p> 对访问url进行权限认证处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/15 14:21
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 
	 * @author meisw 2019年11月15日 上午10:06:26
	 * @Method: decide 
	 * @Description: 
	 * @param arg0	当前登录用户的角色信息
	 * @param arg1	请求URL信息
	 * @param arg2	‘UrlFilterInvocationSecurityMetadataSource‘中的getAttributes方法传来的，表示当前请求需要的角色(可能有多个)
	 * @throws AccessDeniedException
	 * @throws InsufficientAuthenticationException 
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
	 */
	@Override
	public void decide(Authentication arg0, Object arg1, Collection<ConfigAttribute> arg2)
	        throws AccessDeniedException, InsufficientAuthenticationException {
		//遍历角色
		for(ConfigAttribute ca: arg2) {
			//当前url请求需要的权限
			String needRole = ca.getAttribute();
			if(Constance.ROLE_LOGIN.equals(needRole)) {
				if(arg0 instanceof AnonymousAuthenticationToken) {
					throw new BadCredentialsException("未登录!");
				}
			}
			//当前用户所具有的角色
			Collection<? extends GrantedAuthority> authorities = arg0.getAuthorities();
			for(GrantedAuthority authority : authorities) {
				//只要包含其中一个角色即可访问
				if(authority.getAuthority().equals(needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("请联系管理员分配权限!");
	}	

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

//    /**
//     * @param authentication: 当前登录用户的角色信息
//     * @param object: 请求url信息
//     * @param collection: `UrlFilterInvocationSecurityMetadataSource`中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
//     * @return: void
//     */
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {
//        // 遍历角色
//        for (ConfigAttribute ca : collection) {
//            // ① 当前url请求需要的权限
//            String needRole = ca.getAttribute();
//            if (Constants.ROLE_LOGIN.equals(needRole)) {
//                if (authentication instanceof AnonymousAuthenticationToken) {
//                    throw new BadCredentialsException("未登录!");
//                } else {
//                    throw new AccessDeniedException("未授权该url！");
//                }
//            }
//
//            // ② 当前用户所具有的角色
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                // 只要包含其中一个角色即可访问
//                if (authority.getAuthority().equals(needRole)) {
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("请联系管理员分配权限！");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
}
