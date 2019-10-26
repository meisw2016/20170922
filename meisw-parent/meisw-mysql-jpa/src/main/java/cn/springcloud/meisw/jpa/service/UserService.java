package cn.springcloud.meisw.jpa.service;

import cn.springcloud.meisw.jpa.po.User;


public interface UserService {
	
	public User selectUserById(Long id);
	
	public User find(Long id);
}
