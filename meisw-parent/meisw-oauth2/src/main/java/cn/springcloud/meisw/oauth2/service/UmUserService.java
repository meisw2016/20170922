package cn.springcloud.meisw.oauth2.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.oauth2.entity.UmUser;


public interface UmUserService {
	
	public void saveUser(UmUser umUser)throws MeiswException;
	
	public void deleteUser(Long id)throws MeiswException;
	
	public void updateUser(UmUser umUser)throws MeiswException;
	
	public List<UmUser> findAllUser()throws MeiswException;
	
	public MultiValueMap<String,Object> login(UmUser umUser)throws MeiswException;
	
}
