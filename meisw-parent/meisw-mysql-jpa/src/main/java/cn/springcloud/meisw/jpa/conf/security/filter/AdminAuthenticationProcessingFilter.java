package cn.springcloud.meisw.jpa.conf.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.springcloud.meisw.jpa.common.Constance;
import cn.springcloud.meisw.jpa.conf.security.login.AdminAuthenticationFailureHandler;
import cn.springcloud.meisw.jpa.conf.security.login.AdminAuthenticationSuccessHandler;
import cn.springcloud.meisw.jpa.conf.security.login.CusAuthenticationManager;
import cn.springcloud.meisw.jpa.po.SysUser;
import cn.springcloud.meisw.jpa.util.MultiReadHttpServletRequest;

/**
 * <p>
 * 自定义用户密码校验过滤器
 * </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:32
 */
@Component
public class AdminAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final Logger log = LoggerFactory.getLogger(AdminAuthenticationProcessingFilter.class);
	
	/**
	 * @param authenticationManager: 认证管理器
	 * @param adminAuthenticationSuccessHandler: 认证成功处理
	 * @param adminAuthenticationFailureHandler: 认证失败处理
	 */
	public AdminAuthenticationProcessingFilter(CusAuthenticationManager authenticationManager,
	        AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler,
	        AdminAuthenticationFailureHandler adminAuthenticationFailureHandler) {
		super(new AntPathRequestMatcher("/login", "POST"));
		this.setAuthenticationManager(authenticationManager);
		this.setAuthenticationSuccessHandler(adminAuthenticationSuccessHandler);
		this.setAuthenticationFailureHandler(adminAuthenticationFailureHandler);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	        throws AuthenticationException {
		if (request.getContentType() == null || !request.getContentType().contains(Constance.REQUEST_HEADERS_CONTENT_TYPE)) {
			throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
		}
		
		UsernamePasswordAuthenticationToken authRequest;
		try {
			MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
			// 将前端传递的数据转换成jsonBean数据格式
			SysUser user = JSONObject.parseObject(wrappedRequest.getBodyJsonStrByJson(wrappedRequest), SysUser.class);
			authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
			authRequest.setDetails(authenticationDetailsSource.buildDetails(wrappedRequest));
		} catch (Exception e) {
			throw new AuthenticationServiceException(e.getMessage());
		}
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
}
