package cn.springcloud.meisw.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.dao.DomainDao;
import cn.springcloud.meisw.jpa.db1.po.Domain;
import cn.springcloud.meisw.jpa.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {
	
	@Autowired
	private DomainDao domainDao;
	
	@Override
	public void add(Domain domain) throws MeiswException {
		domainDao.saveAndFlush(domain);
	}
	
	@Override
	public void delete(Domain domain) throws MeiswException {
		domainDao.delete(domain);
	}
	
	@Override
	public Domain update(Domain domain) throws MeiswException {
		return domainDao.saveAndFlush(domain);
	}
	
	@Override
	public List<Domain> getAll() throws MeiswException {
		return domainDao.getAll();
	}
}
