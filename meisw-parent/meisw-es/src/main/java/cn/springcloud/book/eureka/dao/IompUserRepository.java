package cn.springcloud.book.eureka.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.springcloud.book.eureka.security.IompUserEs;

public interface IompUserRepository extends ElasticsearchRepository<IompUserEs,String>{
	/**
	 * 
	 * @author meisw 2019年7月2日 上午8:46:25
	 * @Method: findIompUserByLoginName 
	 * @Description: 根据登录名称查询ES数据
	 * @param loginName 登录名称
	 * @return 
	 * @throws
	 */
	public IompUserEs findIompUserByLoginName(String loginName);
}
