package cn.springcloud.meisw.oauth2.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import cn.springcloud.meisw.oauth2.dao.UmRoleDao;
import cn.springcloud.meisw.oauth2.dao.UmUserDao;
import cn.springcloud.meisw.oauth2.dto.CustomUserDetail;
import cn.springcloud.meisw.oauth2.entity.UmUser;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private UmUserDao umUserDao;
	
	@Autowired
	private UmRoleDao umRoleDao;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	     @Override
	     protected UserDetailsService userDetailsService() {
	         BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return new UserDetailsService(){
	            @Override
	            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	                log.info("username:{}",username);
	                UmUser user = umUserDao.findByUsername(username);
	                if(user != null){
	                	CustomUserDetail customUserDetail = new CustomUserDetail();
	                    customUserDetail.setUsername(user.getAccount());
	                    customUserDetail.setPassword("{bcrypt}"+bCryptPasswordEncoder.encode(user.getPassword()));
	                    List<GrantedAuthority> list = AuthorityUtils.createAuthorityList(umRoleDao.getRoles(user.getId()));
	                    customUserDetail.setAuthorities(list);
	                    return customUserDetail;
	                }else {//返回空
	                    return null;
	                }
	 
	            }
	        };
	     }
	 
	     @Bean
	     PasswordEncoder passwordEncoder() {
	         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	     }
}
