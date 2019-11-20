package cn.springcloud.boot.ssh.autoinstall.softwareManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.page.PageMethod;

import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.MsSoftwareManage;
import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.QueryModel;
import cn.springcloud.boot.ssh.autoinstall.softwareManage.repository.mapper.MsSoftwareManageMapper;
import cn.springcloud.boot.ssh.autoinstall.websocket.service.SoftwareManageHelper;


/**
 * @项目名称: npam-disposal模块
 * @类名称: MsSoftwareManageService
 * @类描述: #服务类
 * @功能描述:
 * @创建人: Sunthiny
 * @创建时间: 2019-03-12 22:24:21
 * @修改备注:
 * @修改记录: 修改时间 修改人员 修改原因
 *        -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2017宇信科技-版权所有
 */
@Service
public class MsSoftwareManageService {
	@Autowired
	private MsSoftwareManageMapper msSoftwareManageMapper;

	@Autowired
	private SoftwareManageHelper softwareManageHelper;

	/**
	 * 
	 * @Description:分页查询
	 * @param queryModel
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MsSoftwareManage> query(QueryModel queryModel) {
		PageMethod.startPage(queryModel.getPage(), queryModel.getSize());
		List<MsSoftwareManage> list = msSoftwareManageMapper.query(queryModel);
		PageMethod.clearPage();
		return list;
	}

	/**
	 * @方法名称: uninstall
	 * @方法描述: 软件卸载后删除对应信息
	 * @参数与返回说明: int
	 * @算法描述:
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, RuntimeException.class })
	public int uninstall(Map<String, Object> map) {
		return msSoftwareManageMapper.uninstall(map);
	}

	/**
	 * @方法名称: install
	 * @方法描述: 软件安装后添加对应信息
	 * @参数与返回说明: int
	 * @算法描述:
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, RuntimeException.class })
	public int install(Map<String, Object> map) {
		String smHost = map.get("smHost").toString();
		String smSoftware = map.get("smSoftware").toString();
		
		QueryModel model = new QueryModel();
		StringBuilder sb = new StringBuilder(40);
		sb.append("{\"smHost\":\"").append(smHost).append("\",\"smSoftware\":\"").append(smSoftware).append("\"}");
		model.setCondition(sb.toString());
		model.setPage(1);
		model.setSize(1000);
		List<MsSoftwareManage> list = msSoftwareManageMapper.query(model);
		if (!list.isEmpty()) {
	        msSoftwareManageMapper.uninstall(map);//重复数据直接覆盖
		}
		
		MsSoftwareManage msSoftwareManage = new MsSoftwareManage();
		msSoftwareManage.setSmHost(smHost);
		msSoftwareManage.setSmSoftware(smSoftware);
		Map<String, String> config = softwareManageHelper.getInfo(smHost, smSoftware);
		msSoftwareManage.setSmVersion(getPropValue(config, "_Ver", smSoftware));
		msSoftwareManage.setSmPath(getPropValue(config, "Install_Dir", smSoftware));
		msSoftwareManage.setSmOs(config.get("Release") != null?config.get("Release"):"");
		msSoftwareManage.setSmEnv(config.get("CPUCORES") + "-" + config.get("MemTotal"));
		return msSoftwareManageMapper.install(msSoftwareManage);
	}

	/**
	 * @方法名称: getPropValue
	 * @方法描述: 从主机查回的基础配置config中获取安装软件所需的属性
	 * @参数与返回说明: int
	 * @算法描述: 遍历config的key,匹配和software相符的属性
	 */
	public String getPropValue(Map<String, String> config, String prop, String software) {
		String value = null;
		for (Map.Entry<String, String> entry : config.entrySet()) {
			if (entry.getKey().toLowerCase().indexOf(software) != -1 && entry.getKey().indexOf(prop) != -1) {
				value = entry.getValue();
			}
		}
		value = value == null ? "" : value;
		return value;
	}
}
