package cn.springcloud.meisw.jpa.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.springcloud.meisw.jpa.conf.common.CacheConstance;
import cn.springcloud.meisw.jpa.dao.UserDao;
import cn.springcloud.meisw.jpa.po.User;
import cn.springcloud.meisw.jpa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
//	清除缓存
//	@CacheEvict(value="users",allEntries=true)
//	更新缓存
//	@CachePut(value = "emp", key = "targetClass + #p0")
//	@CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。
//
//    其中value、key和condition的语义与@Cacheable对应的属性类似；allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。
//
//   当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。
	
	@Autowired
	private UserDao userDao;
	
	@Override
//	@Cacheable(value=CacheConstance.DEMO_CACHE_NAME,key = "'user_'+id")
	@Cacheable(cacheNames = CacheConstance.DEMO_CACHE_NAME,key = "#id")
	public User selectUserById(Long id) {
		log.info("userService selectUserById (id={}) start ...",id); 
		User user = userDao.findById(id).get();
		log.info("userService selectUserById (id={}) end ...",id); 
		return user;
	}

	@Override
//	@Cacheable(value="users",key="#p0")
    public User find(Long id) {
	    return userDao.findById(id).get();
    }

	@Override
    public Page<User> findUserNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size,Sort.Direction.ASC,"id");
	    return userDao.findAll(pageable);
    }

	@Override
//	@CachePut(value = "users",key = "#user.id")
//	@CachePut(value = CacheConstance.DEMO_CACHE_NAME,key = CacheConstance.CACEH_KEY)//保存用户数据
//	@CachePut(value = CacheConstance.DEMO_CACHE_NAME,key = CacheConstance.CACEH_KEY)//保存用户数据
//	@CachePut(cacheNames = CacheConstance.DEMO_CACHE_NAME,key = "#id")//保存用户数据
	@CachePut(cacheNames = "users",key = "#id")//保存用户数据
	public User save(User user) {
		log.info("userService save (p:{})  start ...",user);
		User result = userDao.saveAndFlush(user);
		log.info("userService save end ...");
		return result;
	}

	@Override
//	@CacheEvict(value = "users")
//	@CacheEvict(value = CacheConstance.DEMO_CACHE_NAME,key = "'user_'+#id")//清除缓存
	@CacheEvict(value = CacheConstance.DEMO_CACHE_NAME,key = "#id",allEntries = true)//清除缓存
	public void remove(Long id) {
		log.info("userService remove start (id={})...",id);
		userDao.deleteById(id);
		log.info("userService remove end   ...");
	}
	
}
