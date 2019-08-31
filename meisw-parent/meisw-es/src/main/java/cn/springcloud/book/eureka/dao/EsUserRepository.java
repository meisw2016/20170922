package cn.springcloud.book.eureka.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.springcloud.book.eureka.po.EsUser;
import cn.springcloud.book.eureka.security.IompUserEs;

public interface EsUserRepository extends ElasticsearchRepository<EsUser,String>{
	/**
	 * 第一种方式，类型与JPA，编写一个ElasticsearchRepository
	 * 第一个泛型为Bean的类型
	 * 第二个泛型为Bean的主键类型
	 * @author meisw 2019年7月1日 下午3:28:22
	 * @Method: findEsUserByUsername 
	 * @Description: TODO
	 * @param username
	 * @param pageable
	 * @return 
	 * @throws
	 */
	Page<EsUser> findEsUserByUsername(String username,Pageable pageable);
	
	public List<EsUser> findEsUserByUsername(String username);
	
	/**
	 * 
	 * @author meisw 2019年7月2日 上午8:46:25
	 * @Method: findIompUserByLoginName 
	 * @Description: 根据登录名称查询ES数据
	 * @param loginName 登录名称
	 * @return 
	 * @throws
	 */
//	public List<IompUserEs> findIompUserByLoginName(String loginName);
}
