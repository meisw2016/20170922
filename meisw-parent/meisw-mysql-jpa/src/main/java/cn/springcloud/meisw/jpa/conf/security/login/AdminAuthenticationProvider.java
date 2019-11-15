package cn.springcloud.meisw.jpa.conf.security.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cn.springcloud.meisw.jpa.conf.Constants;
import cn.springcloud.meisw.jpa.conf.security.dto.SecurityUser;
import cn.springcloud.meisw.jpa.conf.security.service.impl.UserDetailsServiceImpl;
import cn.springcloud.meisw.jpa.dao.SysUserDao;
import cn.springcloud.meisw.jpa.po.SysUser;
import cn.springcloud.meisw.jpa.util.PasswordUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *  <p> 自定义认证处理 </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/10/12 14:49
 */
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
//    private UserMapper userMapper;
    private SysUserDao sysUserDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        SecurityUser userInfo = (SecurityUser) userDetailsService.loadUserByUsername(userName);

        boolean isValid = PasswordUtils.isValidPassword(password, userInfo.getPassword(), userInfo.getCurrentUserInfo().getSalt());
        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException("密码错误！");
        }

        // 前后端分离情况下 处理逻辑...
        // 更新登录令牌
//        String token = PasswordUtils.encodePassword(String.valueOf(System.currentTimeMillis()), userInfo.getCurrentUserInfo().getSalt());
        // 当前用户所拥有角色代码
        String roleCodes = userInfo.getRoleCodes();
        // 生成jwt访问令牌
        String jwt = Jwts.builder()
                // 用户角色
                .claim(Constants.ROLE_LOGIN, roleCodes)
                // 主题 - 存用户名
                .setSubject(authentication.getName())
                // 过期时间 - 30分钟
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                // 加密算法和密钥
                .signWith(SignatureAlgorithm.HS512, Constants.SALT)
                .compact();


//        User user = userMapper.selectById(userInfo.getCurrentUserInfo().getId());
//        user.setToken(jwt);
//        userMapper.updateById(user);
        SysUser user = sysUserDao.findById(userInfo.getCurrentUserInfo().getId()).get();
        user.setToken(jwt);
        sysUserDao.saveAndFlush(user);
        userInfo.getCurrentUserInfo().setToken(jwt);
        return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
