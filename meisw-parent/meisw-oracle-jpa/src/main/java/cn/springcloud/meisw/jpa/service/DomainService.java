package cn.springcloud.meisw.jpa.service;

import java.util.List;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.po.Domain;

public interface DomainService {

	public void add(Domain domain)throws MeiswException;
	
	public void delete(Domain domain)throws MeiswException;
	
	public Domain update(Domain domain)throws MeiswException;
	
	public List<Domain> getAll()throws MeiswException;
}
