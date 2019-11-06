package cn.springcloud.meisw.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.dao.P8FieldMappingDao;
import cn.springcloud.meisw.jpa.db1.po.P8FieldMapping;
import cn.springcloud.meisw.jpa.service.P8FieldMappingService;

@Service
public class P8FieldMappingServiceImpl implements P8FieldMappingService {
	
	@Autowired
	private P8FieldMappingDao p8FieldMappingDao;
	
	@Transactional(timeout = 60,value = "firstTransactionManager",rollbackFor = {MeiswException.class})
	@Override
	public void add(P8FieldMapping p8) throws MeiswException {
		p8FieldMappingDao.saveAndFlush(p8);
	}
	
	@Transactional(timeout = 60,value = "firstTransactionManager",rollbackFor = {MeiswException.class})
	@Override
	public void delete(Long id) throws MeiswException {
		p8FieldMappingDao.deleteById(id);
	}
	
	@Transactional(timeout = 60,value = "firstTransactionManager",rollbackFor = {MeiswException.class})
	@Override
	public P8FieldMapping update(P8FieldMapping p8) throws MeiswException {
		return p8FieldMappingDao.saveAndFlush(p8);
	}
	
	@Override
	public List<P8FieldMapping> getAll() throws MeiswException {
		return p8FieldMappingDao.getAll();
	}
}
