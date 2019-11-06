package cn.springcloud.meisw.jpa.db1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.springcloud.meisw.jpa.db1.po.DataDic;

public interface DataDicDao extends JpaRepository<DataDic, Long>{
	
	@Query("from DataDic")
	public List<DataDic> queryAll();
}
