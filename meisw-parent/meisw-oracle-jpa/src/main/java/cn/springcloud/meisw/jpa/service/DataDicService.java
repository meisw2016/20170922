package cn.springcloud.meisw.jpa.service;

import java.util.List;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.po.DataDic;

public interface DataDicService {
	
	public void add(DataDic dataDic)throws MeiswException;
	
	public void delete(Long id)throws MeiswException;
	
	public DataDic update(DataDic dataDic)throws MeiswException;
	
	public List<DataDic> queryAll()throws MeiswException;
}
