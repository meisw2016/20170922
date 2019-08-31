package cn.springcloud.book.eureka.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springcloud.book.eureka.dao.EsUserRepository;
import cn.springcloud.book.eureka.po.EsUser;
import cn.springcloud.book.eureka.security.IompUser;
import cn.springcloud.book.eureka.service.KibanaOperateService;
@Service
public class KibanaOperateServiceImpl implements KibanaOperateService{
	
	private static final Logger log = LoggerFactory.getLogger(KibanaOperateServiceImpl.class);
	@Autowired
	private EsUserRepository esUserRepository;
	@Override
	public void permissionConsistent(IompUser iompUser) {
		//使用登录名称查询ES中的用户是否存在，不存在则新增
		List<EsUser> result = esUserRepository.findEsUserByUsername("fanqi");
		if(result == null && result.size()<1) {
			log.warn("用户：{}不存在，需要创建",iompUser.getLoginName());
			EsUser esUser = new EsUser();
			esUser.setUsername(iompUser.getLoginName());
			esUser.setPassword(iompUser.getLoginName());
			esUser.setEnabled(1);
			try {
				esUserRepository.save(esUser);
				
				//根据userId查询用户所属的所有机构，并删除  [kibana的机构]
				
			}catch(Exception e) {
				log.error("执行创建用户API失败");
			}
		}
	}
	
}
