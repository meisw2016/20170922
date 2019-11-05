package cn.springcloud.meisw.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.springcloud.meisw.jpa.db1.po.User;


public interface UserService {
	
	public User selectUserById(Long id);
	
	public User find(Long id);
	
	public List<User> findByUserName(String name);
	
	public List<User> findAllUser();
	
	public Long saveUser(User user);
	
	public void deleteUserById(Long id);
	
	public User updateUser(User user);
	
	/**
	 * 分页查询所有用户信息
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findUserNoCriteria(Integer page,Integer size);
}
