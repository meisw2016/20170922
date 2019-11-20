package cn.springcloud.boot.ssh.autoinstall.softwareManage.repository.mapper;

import java.util.List;
import java.util.Map;

import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.MsSoftwareManage;
import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.QueryModel;


/**
 * @项目名称: npam-disposal模块
 * @类名称: MsSoftwareManageMapper
 * @类描述: #Dao类
 * @功能描述: 
 * @创建人: Sunthiny
 * @创建时间: 2019-03-12 22:24:21
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2017宇信科技-版权所有
 */
public interface MsSoftwareManageMapper {

	/**
	 * 分页查询
	 * @param queryModel
	 * @return
	 */
	List<MsSoftwareManage> query(QueryModel queryModel);

	/**
	 * 卸载后删除表数据
	 * @param map
	 * @return
	 */
	int uninstall(Map<String, Object> map);

	/**
	 * @方法名称: install
	 * @方法描述: 软件安装后添加对应信息
	 * @参数与返回说明: int
	 * @算法描述:
	 */
	int install(MsSoftwareManage msSoftwareManage);
	
}