package cn.springcloud.meisw.jpa.conf.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cn.springcloud.meisw.jpa.common.OutputData;
import cn.springcloud.meisw.jpa.conf.security.dto.SecurityUser;
import cn.springcloud.meisw.jpa.po.SysUser;
import cn.springcloud.meisw.jpa.util.ResponseUtils;

/**
 *  <p> 认证成功处理 </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/10/12 15:31
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        SysUser user = new SysUser();
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
//        user.setToken(securityUser.getCurrentUserInfo().getToken());
//        user.setToken(securityUser.get);
        ResponseUtils.out(response, new OutputData().returnSuccess("登录成功!"));
    }
}
