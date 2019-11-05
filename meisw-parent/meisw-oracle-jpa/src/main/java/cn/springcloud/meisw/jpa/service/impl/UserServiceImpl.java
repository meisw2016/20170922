package cn.springcloud.meisw.jpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.springcloud.meisw.jpa.db1.dao.UserDao;
import cn.springcloud.meisw.jpa.db1.po.User;
import cn.springcloud.meisw.jpa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// 清除缓存
	// @CacheEvict(value="users",allEntries=true)
	// 更新缓存
	// @CachePut(value = "emp", key = "targetClass + #p0")
	// @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。
	//
	// 其中value、key和condition的语义与@Cacheable对应的属性类似；allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。
	//
	// 当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Cacheable(value = "user")
	public User selectUserById(Long id) {
		User user = userDao.findById(id).get();
		return user;
	}
	
	@Override
	@Cacheable(value = "user", key = "#p0")
	public User find(Long id) {
		return userDao.findById(id).get();
	}
	
	@Override
	public Page<User> findUserNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		return userDao.findAll(pageable);
	}
	
	@Cacheable(value = "user", key = "'user'.concat(#name)")
	@Override
	public List<User> findByUserName(String name) {
		return userDao.findUsersByName(name);
	}
	
//	@Caching(
//			cacheable = {
//					@Cacheable(value = "/user",key="#name"),
//					@Cacheable(value = "/user",key="#age")
//				}
//			)
	
	@Caching(
			put = {
					@CachePut(value = "/user",key = "#name"),
					@CachePut(value = "/user",key = "#age")
			}
			)
	@Override
	public Long saveUser(User user) {
		return userDao.saveAndFlush(user).getId();
	}

	@CacheEvict(key = "#id",value = "user")//,cacheManager = "cacheManager"
	@Override
	public void deleteUserById(Long id) {
		userDao.deleteById(id);
	}

	@CachePut(key = "#id",value = "user")//,cacheManager = "cacheManager"
	@Override
	public User updateUser(User user) {
		return userDao.saveAndFlush(user);
	}
	
}
