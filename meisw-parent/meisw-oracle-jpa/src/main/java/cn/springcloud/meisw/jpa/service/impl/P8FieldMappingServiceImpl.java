package cn.springcloud.meisw.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@Caching(cacheable = @Cacheable("p8"), evict = {@CacheEvict(value = "p8", allEntries = true)})
	@Transactional(timeout = 60, rollbackFor = {MeiswException.class})
	@Override
	public void add(P8FieldMapping p8) throws MeiswException {
		p8FieldMappingDao.saveAndFlush(p8);
	}
	
	@CacheEvict(value = "p8", allEntries = true)
	@Transactional(timeout = 60, rollbackFor = {MeiswException.class})
	@Override
	public void delete(Long id) throws MeiswException {
		p8FieldMappingDao.deleteById(id);
	}
	
	@Caching(cacheable = @Cacheable("p8"), evict = {@CacheEvict(value = "p8", allEntries = true)})
	@Transactional(timeout = 60, rollbackFor = {MeiswException.class})
	@Override
	public P8FieldMapping update(P8FieldMapping p8) throws MeiswException {
		return p8FieldMappingDao.saveAndFlush(p8);
	}
	
	@Caching(cacheable = @Cacheable("p8"), evict = {@CacheEvict(value = "p8", allEntries = true)})
	@Override
	public List<P8FieldMapping> getAll() throws MeiswException {
		return p8FieldMappingDao.getAll();
	}
	
	@Caching(cacheable = @Cacheable("p8"), evict = {@CacheEvict(value = "p8", allEntries = true)})
	@Override
	public Page<P8FieldMapping> queryForPage(Integer page, Integer size) throws MeiswException {
		return p8FieldMappingDao.findAll(new PageRequest(page, size));
	}
	
	@Override
	public List<P8FieldMapping> queryByDataNoAndAbbreviation(String dataNo, String abbreviation) throws MeiswException {
		return p8FieldMappingDao
		        .findAll((Root<P8FieldMapping> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
			        List<Predicate> list = new ArrayList<>();
			        if (dataNo != null) {
				        list.add(criteriaBuilder.equal(root.get("dataNo"), dataNo));
			        }
			        if (abbreviation != null) {
				        list.add(criteriaBuilder.equal(root.get("abbreviation"), abbreviation));
			        }
			        Predicate[] p = new Predicate[list.size()];
			        return criteriaBuilder.and(list.toArray(p));
		        });
	}
	
	@Override
	public Page<P8FieldMapping> queryByOptionForPage(String dataNo, String abbreviation, Pageable pageable)
	        throws MeiswException {
		return p8FieldMappingDao
		        .findAll((Root<P8FieldMapping> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
			        Predicate predicate = null;
			        if (dataNo != null) {
				        predicate = criteriaBuilder.equal(root.get("dataNo"), dataNo);
			        }
			        if (abbreviation != null) {
				        predicate = criteriaBuilder.equal(root.get("abbreviation"), abbreviation);
			        }
			        return predicate;
		        }, pageable);
	}
}
