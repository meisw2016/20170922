package cn.springcloud.meisw.jpa.db1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.springcloud.meisw.jpa.db1.po.P8FieldMapping;

public interface P8FieldMappingDao extends JpaRepository<P8FieldMapping,Long>{
	
	@Query("from P8FieldMapping")
	public List<P8FieldMapping> getAll();
}
