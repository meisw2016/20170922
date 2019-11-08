package cn.springcloud.meisw.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.po.P8FieldMapping;

public interface P8FieldMappingService {
	
	public void add(P8FieldMapping p8)throws MeiswException;
	
	public void delete(Long id)throws MeiswException;
	
	public P8FieldMapping update(P8FieldMapping p8)throws MeiswException;
	
	public List<P8FieldMapping> getAll()throws MeiswException;
	
	/**
	 * 
	 * @author meisw 2019年11月8日 上午9:30:50
	 * @Method: queryByDataNoAndAbbreviation 
	 * @Description: 多条件查询
	 * @param dataNo
	 * @param abbreviation
	 * @return
	 * @throws MeiswException 
	 * @throws
	 */
	public List<P8FieldMapping> queryByDataNoAndAbbreviation(String dataNo,String abbreviation)throws MeiswException;
	
	/**
	 * 
	 * @author meisw 2019年11月8日 上午9:31:54
	 * @Method: queryByOptionForPage 
	 * @Description: 多条件分页查询
	 * @param dataNo
	 * @param abbreviation
	 * @return
	 * @throws MeiswException 
	 * @throws
	 */
	public Page<P8FieldMapping> queryByOptionForPage(String dataNo,String abbreviation,Pageable pageable)throws MeiswException;
	
	/**
	 * 
	 * @author meisw 2019年11月8日 上午9:32:15
	 * @Method: queryForPage 
	 * @Description: 分页查询
	 * @param page
	 * @param size
	 * @return
	 * @throws MeiswException 
	 * @throws
	 */
	public Page<P8FieldMapping> queryForPage(Integer page,Integer size)throws MeiswException;

}
