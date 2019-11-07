package cn.springcloud.meisw.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.po.P8FieldMapping;

public interface P8FieldMappingService {
	
	public void add(P8FieldMapping p8)throws MeiswException;
	
	public void delete(Long id)throws MeiswException;
	
	public P8FieldMapping update(P8FieldMapping p8)throws MeiswException;
	
	public List<P8FieldMapping> getAll()throws MeiswException;
	
	public Page<P8FieldMapping> queryForPage(Integer page,Integer size)throws MeiswException;
}
