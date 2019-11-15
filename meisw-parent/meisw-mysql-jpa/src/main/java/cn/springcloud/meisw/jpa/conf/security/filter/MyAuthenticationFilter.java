package cn.springcloud.meisw.jpa.conf.security.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.springcloud.meisw.jpa.conf.Constants;
import cn.springcloud.meisw.jpa.conf.security.dto.SecurityUser;
import cn.springcloud.meisw.jpa.conf.security.login.AdminAuthenticationEntryPoint;
import cn.springcloud.meisw.jpa.conf.security.service.impl.UserDetailsServiceImpl;
import cn.springcloud.meisw.jpa.util.MultiReadHttpServletRequest;
import cn.springcloud.meisw.jpa.util.MultiReadHttpServletResponse;

//import com.zhengqing.config.Constants;
//import com.zhengqing.config.security.dto.SecurityUser;
//import com.zhengqing.config.security.login.AdminAuthenticationEntryPoint;
//import com.zhengqing.config.security.service.impl.UserDetailsServiceImpl;
//import com.zhengqing.utils.MultiReadHttpServletRequest;
//import com.zhengqing.utils.MultiReadHttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * <p>
 * 访问鉴权 - 每次访问接口都会经过此
 * </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 16:17
 */
@Component
public class MyAuthenticationFilter extends OncePerRequestFilter {
	
	private static final Logger log  = LoggerFactory.getLogger(MyAuthenticationFilter.class);
	
	@Autowired
	AdminAuthenticationEntryPoint authenticationEntryPoint;
	
	private final UserDetailsServiceImpl userDetailsService;
	
	protected MyAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
		System.out.println("请求头类型： " + request.getContentType());
		if ((request.getContentType() == null && request.getContentLength() > 0) || (request.getContentType() != null
		        && !request.getContentType().contains(Constants.REQUEST_HEADERS_CONTENT_TYPE))) {
			filterChain.doFilter(request, response);
			return;
		}
		
		MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
		MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(response);
		StopWatch stopWatch = new StopWatch();
		try {
			stopWatch.start();
			// 记录请求的消息体
			logRequestBody(wrappedRequest);
			
			// SecurityContext context = SecurityContextHolder.getContext();
			// if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
			// filterChain.doFilter(wrappedRequest, wrappedResponse);
			// return;
			// }
			
			// 前后端分离情况下，前端登录后将token储存在cookie中，每次访问接口时通过token去拿用户权限
			String jwtToken = wrappedRequest.getHeader(Constants.REQUEST_HEADER);
			log.debug("后台检查令牌:{}", jwtToken);
			if (StringUtils.isNotBlank(jwtToken)) {
				// JWT相关start ===========================================
				// 获取jwt中的信息
				Claims claims = Jwts.parser().setSigningKey(Constants.SALT).parseClaimsJws(jwtToken.replace("Bearer", ""))
				        .getBody();
				// 获取当前登录用户名
				System.out.println("获取当前登录用户名: " + claims.getSubject());
				// TODO 如需使用jwt特性在此做处理~
				// JWT相关end ===========================================
				
				// 检查token
				SecurityUser securityUser = userDetailsService.getUserByToken(jwtToken);
				if (securityUser == null || securityUser.getCurrentUserInfo() == null) {
					throw new BadCredentialsException("TOKEN已过期，请重新登录！");
				}
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null,
				        securityUser.getAuthorities());
				// 全局注入角色权限信息和登录用户基本信息
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(wrappedRequest, wrappedResponse);
		} catch (ExpiredJwtException e) {
			// jwt令牌过期
			SecurityContextHolder.clearContext();
			this.authenticationEntryPoint.commence(wrappedRequest, response, null);
		} catch (AuthenticationException e) {
			SecurityContextHolder.clearContext();
			this.authenticationEntryPoint.commence(wrappedRequest, response, e);
		} finally {
			stopWatch.stop();
			long usedTimes = stopWatch.getTotalTimeMillis();
			// 记录响应的消息体
			logResponseBody(wrappedRequest, wrappedResponse, usedTimes);
		}
		
	}
	
	private String logRequestBody(MultiReadHttpServletRequest request) {
		MultiReadHttpServletRequest wrapper = request;
		if (wrapper != null) {
			try {
				String bodyJson = wrapper.getBodyJsonStrByJson(request);
				String url = wrapper.getRequestURI().replace("//", "/");
				System.out.println("-------------------------------- 请求url: " + url + " --------------------------------");
				Constants.URL_MAPPING_MAP.put(url, url);
				log.info("`{}` 接收到的参数: {}", url, bodyJson);
				return bodyJson;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void logResponseBody(MultiReadHttpServletRequest request, MultiReadHttpServletResponse response, long useTime) {
		MultiReadHttpServletResponse wrapper = response;
		if (wrapper != null) {
			byte[] buf = wrapper.getBody();
			if (buf.length > 0) {
				String payload;
				try {
					payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					payload = "[unknown]";
				}
				log.info("`{}`  耗时:{}ms  返回的参数: {}", Constants.URL_MAPPING_MAP.get(request.getRequestURI()), useTime, payload);
			}
		}
	}
	
}
