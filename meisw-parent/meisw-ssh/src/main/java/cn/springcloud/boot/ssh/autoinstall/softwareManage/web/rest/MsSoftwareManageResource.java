package cn.springcloud.boot.ssh.autoinstall.softwareManage.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.MsSoftwareManage;
import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.QueryModel;
import cn.springcloud.boot.ssh.autoinstall.softwareManage.domain.ResultDto;
import cn.springcloud.boot.ssh.autoinstall.softwareManage.service.MsSoftwareManageService;



/**
 * @项目名称: npam-disposal模块
 * @类名称: MsSoftwareManageResource
 * @类描述: #资源类
 * @功能描述:
 * @创建人: Sunthiny
 * @创建时间: 2019-03-12 22:24:21
 * @修改备注:
 * @修改记录: 修改时间 修改人员 修改原因
 *        -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2017宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/mssoftwaremanage")
public class MsSoftwareManageResource {

	private static Logger logger = LoggerFactory.getLogger(MsSoftwareManageResource.class);
	
	@Autowired
	private MsSoftwareManageService msSoftwareManageService;
	
	/**
	 * 
	 * @Description:分页查询（含条件，默认为空）
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/querypage")
	public ResultDto<List<MsSoftwareManage>> getList(QueryModel queryModel) {
		List<MsSoftwareManage> list = msSoftwareManageService.query(queryModel);
		ResultDto<List<MsSoftwareManage>> resultDto = new ResultDto<List<MsSoftwareManage>>(list);
        return resultDto;
	}

	/**
     * @方法名称: uninstall
     * @方法描述: 软件卸载后删除对应信息
     * @参数与返回说明: List
     * @算法描述:
     */
    @PostMapping("/uninstall")
    public ResultDto<Integer> uninstall(@RequestBody Map<String, Object> map) {
        int result = msSoftwareManageService.uninstall(map);
        return new ResultDto<Integer>(result);
    }
    
    /**
     * @方法名称: install
     * @方法描述: 软件安装后添加对应信息
     * @参数与返回说明: ResultDto<Integer>
     * @算法描述:
     */
    @PostMapping("/install")
    public ResultDto<Integer> install(@RequestBody Map<String, Object> map) {
        int result = msSoftwareManageService.install(map);
        return new ResultDto<Integer>(result);
    }
	

}
