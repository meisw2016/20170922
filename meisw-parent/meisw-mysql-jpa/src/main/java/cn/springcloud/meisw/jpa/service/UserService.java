package cn.springcloud.meisw.jpa.service;

import org.springframework.data.domain.Page;

import cn.springcloud.meisw.jpa.po.User;


public interface UserService {
	
	public User save(User user);
	
	public void remove(Long id);
	
	public User selectUserById(Long id);
	
	public User find(Long id);
	
	/**
	 * 分页查询所有用户信息
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findUserNoCriteria(Integer page,Integer size);
}
