package cn.springcloud.meisw.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.db1.dao.DataDicDao;
import cn.springcloud.meisw.jpa.db1.po.DataDic;
import cn.springcloud.meisw.jpa.service.DataDicService;

@Service
public class DataDicServiceImpl implements DataDicService {
	
	@Autowired
	private DataDicDao dataDicDao;
	
	@Override
	public void add(DataDic dataDic) throws MeiswException {
		dataDicDao.saveAndFlush(dataDic);
	}
	
	@Override
	public void delete(Long id) throws MeiswException {
		dataDicDao.deleteById(id);
	}
	
	@Override
	public DataDic update(DataDic dataDic) throws MeiswException {
		return dataDicDao.saveAndFlush(dataDic);
	}
	
	@Override
	public List<DataDic> queryAll() throws MeiswException {
		return dataDicDao.queryAll();
	}
}
