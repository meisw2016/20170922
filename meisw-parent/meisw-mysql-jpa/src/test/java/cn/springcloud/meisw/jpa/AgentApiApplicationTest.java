/*package cn.springcloud.meisw.jpa;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.springcloud.meisw.jpa.dao.UserDao;
import cn.springcloud.meisw.jpa.po.User;
import cn.springcloud.meisw.jpa.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgentApiApplicationTest {

	private static Logger log = LoggerFactory.getLogger(AgentApiApplicationTest.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void contextLoads(){
//		userDao.save(new User("AAA",10));
//		userDao.save(new User("BBB",20));
//		userDao.save(new User("CCC",30));
//		userDao.save(new User("DDD",40));
//		userDao.save(new User("EEE",50));
//		userDao.save(new User("FFF",60));
//		userDao.save(new User("GGG",70));
		
		//测试findAll,查询所有记录
//		Assert.assertEquals(10, userDao.findAll().size());
//		
//		//测试findByName,查询姓名为FFF的user
//		Assert.assertEquals(60, userDao.findByName("FFF").getAge().longValue());
		
		User user = userService.selectUserById(15L);
		log.info("user:{}",user);
	}
}
*/