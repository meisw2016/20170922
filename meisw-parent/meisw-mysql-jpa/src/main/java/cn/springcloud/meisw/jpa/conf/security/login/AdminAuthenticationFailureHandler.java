package cn.springcloud.meisw.jpa.conf.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import cn.springcloud.meisw.jpa.common.OutputData;
import cn.springcloud.meisw.jpa.util.ResponseUtils;

/**
 * <p>
 * 认证失败处理 - 前后端分离情况下返回json数据格式
 * </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/10/12 15:33
 */
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private static final Logger log = LoggerFactory.getLogger(AdminAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response,
	        AuthenticationException e) throws IOException, ServletException {
		OutputData out = new OutputData().returnFail();
		if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
			out.setMessage(e.getMessage());
		} else
			if (e instanceof LockedException) {
				// result = ApiResult.fail("账户被锁定，请联系管理员!");
				out.setMessage("账户被锁定，请联系管理员!");
			} else
				if (e instanceof CredentialsExpiredException) {
					// result = ApiResult.fail("证书过期，请联系管理员!");
					out.setMessage("证书过期，请联系管理员!");
				} else
					if (e instanceof AccountExpiredException) {
						// result = ApiResult.fail("账户过期，请联系管理员!");
						out.setMessage("账户过期，请联系管理员!");
					} else
						if (e instanceof DisabledException) {
							// result = ApiResult.fail("账户被禁用，请联系管理员!");
							out.setMessage("账户被禁用，请联系管理员!");
						} else {
							log.error("登录失败：", e);
							// result = ApiResult.fail("登录失败!");
							out.setMessage("登录失败!");
						}
		ResponseUtils.out(response, out.returnSuccess());
	}
	
}
