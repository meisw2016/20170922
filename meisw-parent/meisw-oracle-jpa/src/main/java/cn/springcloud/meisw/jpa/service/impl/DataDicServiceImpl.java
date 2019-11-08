package cn.springcloud.meisw.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springcloud.meisw.jpa.common.CastEntityUtil;
import cn.springcloud.meisw.jpa.common.CastEntityUtil2;
import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.common.ObjectConvertUtils;
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

	@Override
	public List<DataDic> queryByOption() throws MeiswException {
		List<Object[]> list = dataDicDao.select();
//		List<DataDic> dataDicList = CastEntityUtil.castEntity(list, DataDic.class);
//		List<DataDic> dataDicList = CastEntityUtil2.castEntity(list, DataDic.class);
		List<DataDic> dataDicList = null;
		try {
			dataDicList = ObjectConvertUtils.objectToBean(list, DataDic.class);
		} catch (Exception e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
		return dataDicList;
	}
}
