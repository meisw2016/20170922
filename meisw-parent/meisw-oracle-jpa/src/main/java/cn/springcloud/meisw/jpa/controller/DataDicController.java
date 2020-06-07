package cn.springcloud.meisw.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.common.OutputData;
import cn.springcloud.meisw.jpa.db1.po.DataDic;
import cn.springcloud.meisw.jpa.service.DataDicService;
import cn.springcloud.meisw.jpa.wy.dto.DataDicRequest;
import cn.springcloud.meisw.jpa.wy.dto.DataDicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "/dic", description = "字典")
@RequestMapping(value = "/dic")
public class DataDicController {
	
	private static final Logger log = LoggerFactory.getLogger(DataDicController.class);
	
	@Autowired
	private DataDicService dataDicService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "/add", notes = "增加")
	public OutputData addDic(@RequestBody DataDicRequest request) {
		OutputData out = new OutputData().returnSuccess();
		log.info("数据字典增加操作入参：{}", request);
		DataDic dataDic = new DataDic();
		BeanUtils.copyProperties(request, dataDic);
		try {
			dataDicService.add(dataDic);
		} catch (MeiswException e) {
			log.error("数据字典增加服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ApiOperation(value = "/del",notes = "删除")
	public OutputData deleteDic(@ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id",required = true)Long id) {
		OutputData out = new OutputData().returnSuccess();
		log.info("数据字典删除操作入参：id={}",id);
		try {
			dataDicService.delete(id);
		} catch (MeiswException e) {
			log.error("数据字典删除服务异常：{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ApiOperation(value = "/update",notes = "更新")
	public OutputData update(@RequestBody DataDicRequest request) {
		OutputData out = new OutputData().returnSuccess();
		DataDic dataDic = new DataDic();
		BeanUtils.copyProperties(request, dataDic);
		try {
			dataDic = dataDicService.update(dataDic);
			out.setData(dataDic);
		} catch (MeiswException e) {
			log.error("数据字典更新服务异常：{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	@ApiOperation(value = "/queryAll",notes = "查询所有数据字典")
	public OutputData queryAll() {
		OutputData out = new OutputData().returnSuccess();
		try {
			List<DataDic> list = dataDicService.queryAll();
			List<DataDicResponse> respList = new ArrayList<DataDicResponse>();
			DataDicResponse resp = null;
			if(!list.isEmpty()) {
				for(DataDic dic:list) {
					resp = new DataDicResponse();
					BeanUtils.copyProperties(dic, resp);
					respList.add(resp);
				}
				out.setData(respList);
			}else {
				out.setData(null);
			}
		} catch (MeiswException e) {
			log.error("数据字典查询服务异常：{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value = "/queryByOption",method = RequestMethod.GET)
	@ApiOperation(value = "/queryByOption",notes = "查询所有数据字典 queryByOption")
	public OutputData queryByOption() {
		OutputData out = new OutputData().returnSuccess();
		try {
			List<DataDic> list = dataDicService.queryByOption();
			List<DataDicResponse> respList = new ArrayList<DataDicResponse>();
			DataDicResponse resp = null;
			if(!list.isEmpty()) {
				for(DataDic dic:list) {
					resp = new DataDicResponse();
					BeanUtils.copyProperties(dic, resp);
					respList.add(resp);
				}
				out.setData(respList);
			}else {
				out.setData(null);
			}
		} catch (MeiswException e) {
			log.error("数据字典查询服务异常：{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
}
