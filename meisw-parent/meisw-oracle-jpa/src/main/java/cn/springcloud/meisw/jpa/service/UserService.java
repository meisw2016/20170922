package cn.springcloud.meisw.jpa.service;

import org.springframework.data.domain.Page;

import cn.springcloud.meisw.jpa.db1.po.User;


public interface UserService {
	
	public User selectUserById(Long id);
	
	public User find(Long id);
	
	public User findByUserName(String name);
	
	public Long saveUser(User user);
	
	/**
	 * 分页查询所有用户信息
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findUserNoCriteria(Integer page,Integer size);
}
