package cn.springcloud.book.eureka.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import cn.springcloud.book.eureka.dao.EsUserRepository;
import cn.springcloud.book.eureka.dao.IompUserRepository;
import cn.springcloud.book.eureka.po.EsUser;
import cn.springcloud.book.eureka.security.IompUserEs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController2 {
	
	private static final Logger log = LoggerFactory.getLogger(TestController2.class);
	
	@Autowired
	private EsUserRepository esUserRepository;
	
	@Autowired
	private IompUserRepository iompUserRepository;
	
	@Before
	public void initRepositoryData() {
		// 清除ES中数据以影响测试结果
		esUserRepository.deleteAll();
		esUserRepository.save(new EsUser("1", "fanqi", "admin", 1));
		esUserRepository.save(new EsUser("2", "fanqi", "admin", 1));
		esUserRepository.save(new EsUser("3", "fanqi", "admin", 1));
		
		
		iompUserRepository.save(new IompUserEs("1","admin","admiin"));
		iompUserRepository.save(new IompUserEs("2","admin2","admiin2"));
		iompUserRepository.save(new IompUserEs("3","admin3","admiin3"));
		iompUserRepository.save(new IompUserEs("4","admin4","admiin4"));
	}
	
	@Test
	public void contextLoads3() {
		IompUserEs result = iompUserRepository.findIompUserByLoginName("admin");
		log.info("登录名称为{}的用户信息：{}","admin",result);
	}
	
	@Test
	public void contextLoads2() {
		List<EsUser> result = esUserRepository.findEsUserByUsername("fanqi");
		System.out.println("-------  开始遍历结果数据 -------");
		for(EsUser user : result) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void contextLoads() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<EsUser> result = esUserRepository.findEsUserByUsername("fanqi", pageable);
		// assertThat(result.getTotalElements()).isEqualTo(3);
		System.out.println("-----开始遍历结果数据-----");
		for (EsUser user : result.getContent()) {
			System.out.println(user.toString());
		}
	}
}
