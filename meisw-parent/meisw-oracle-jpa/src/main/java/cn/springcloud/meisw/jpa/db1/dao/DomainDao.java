package cn.springcloud.meisw.jpa.db1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.springcloud.meisw.jpa.db1.po.Domain;

public interface DomainDao extends JpaRepository<Domain, Long>{
	
	@Query("from Domain")
	public List<Domain> getAll();
}
