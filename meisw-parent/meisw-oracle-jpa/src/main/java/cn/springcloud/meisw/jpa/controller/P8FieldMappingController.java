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
import cn.springcloud.meisw.jpa.db1.po.P8FieldMapping;
import cn.springcloud.meisw.jpa.service.P8FieldMappingService;
import cn.springcloud.meisw.jpa.wy.dto.P8FieldMappingRequest;
import cn.springcloud.meisw.jpa.wy.dto.P8FieldMappingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "/p8", description = "映射P8字段")
@RequestMapping(value = "/p8")
public class P8FieldMappingController {
	
	private static final Logger log = LoggerFactory.getLogger(P8FieldMappingController.class);
	
	@Autowired
	private P8FieldMappingService p8FieldMappingService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "/add", notes = "增加")
	public OutputData addP8(@RequestBody P8FieldMappingRequest request) {
		OutputData out = new OutputData().returnSuccess();
		log.info("映射P8字段增加 操作入参：{}", request);
		try {
			P8FieldMapping p8 = new P8FieldMapping();
			BeanUtils.copyProperties(request, p8);
			p8FieldMappingService.add(p8);
		} catch (MeiswException e) {
			log.error("映射P8字段增加服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ApiOperation(value = "/del", notes = "删除")
	public OutputData deleteP8(
	        @ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {
		OutputData out = new OutputData().returnSuccess();
		log.info("映射P8字段删除 操作入参：id={}", id);
		try {
			p8FieldMappingService.delete(id);
		} catch (MeiswException e) {
			log.error("映射P8字段删除服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ApiOperation(value = "/update",notes = "更新")
	public OutputData updateP8(@RequestBody P8FieldMappingRequest request) {
		OutputData out = new OutputData().returnSuccess();
		log.info("映射P8字段更新操作入参:{}",request);
		try {
			P8FieldMapping p8 = new P8FieldMapping();
			BeanUtils.copyProperties(request, p8);
			p8 = p8FieldMappingService.update(p8);
			out.setData(p8);
		}catch(MeiswException e) {
			log.error("映射P8字段更新操作服务异常：{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	@ApiOperation(value = "/queryAll",notes = "查询所有")
	public OutputData queryAll() {
		OutputData out = new OutputData().returnSuccess();
		try {
			List<P8FieldMapping> list = p8FieldMappingService.getAll();
			List<P8FieldMappingResponse> responseList = new ArrayList<P8FieldMappingResponse>();
			P8FieldMappingResponse response = null;
			if(!list.isEmpty()) {
				for(P8FieldMapping p8:list) {
					response = new P8FieldMappingResponse();
					BeanUtils.copyProperties(p8, response);
					responseList.add(response);
				}
			}
			out.setData(responseList);
		} catch (MeiswException e) {
			log.error("映射P8字段查询服务操作：{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
}
