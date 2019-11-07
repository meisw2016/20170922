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
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.common.OutputData;
import cn.springcloud.meisw.jpa.db1.po.Domain;
import cn.springcloud.meisw.jpa.service.DomainService;
import cn.springcloud.meisw.jpa.wy.dto.DomainRequest;
import cn.springcloud.meisw.jpa.wy.dto.DomainResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/domain", description = "域信息")
@RequestMapping(value = "/domain")
public class DomainController {
	
	private static final Logger log = LoggerFactory.getLogger(DomainController.class);
	
	@Autowired
	private DomainService domainService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "/add", notes = "增加")
	public OutputData add(@RequestBody DomainRequest request) {
		OutputData out = new OutputData().returnSuccess();
		Domain domain = new Domain();
		try {
			BeanUtils.copyProperties(request, domain);
			domainService.add(domain);
		} catch (MeiswException e) {
			log.error("域信息添加服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "/update", notes = "更新")
	public OutputData update(@RequestBody DomainRequest request) {
		OutputData out = new OutputData().returnSuccess();
		Domain domain = new Domain();
		BeanUtils.copyProperties(request, domain);
		try {
			domain = domainService.update(domain);
			DomainResponse resp = new DomainResponse();
			BeanUtils.copyProperties(domain, resp);
			out.setData(resp);
		} catch (MeiswException e) {
			log.error("域信息更新服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "/delete", notes = "删除")
	public OutputData delete(@RequestBody DomainRequest request) {
		OutputData out = new OutputData().returnSuccess();
		Domain domain = new Domain();
		BeanUtils.copyProperties(request, domain);
		try {
			domainService.delete(domain);
		} catch (MeiswException e) {
			log.error("域信息删除服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ApiOperation(value = "/queryAll", notes = "查询")
	public OutputData queryAll() {
		OutputData out = new OutputData().returnSuccess();
		try {
			List<Domain> list = domainService.getAll();
			List<DomainResponse> respList = new ArrayList<DomainResponse>();
			DomainResponse resp = null;
			if (!list.isEmpty()) {
				for (Domain domain : list) {
					resp = new DomainResponse();
					BeanUtils.copyProperties(domain, resp);
					respList.add(resp);
				}
				out.setData(respList);
			} else {
				out.setData(null);
			}
		} catch (MeiswException e) {
			log.error("域信息查询服务异常：{}", e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
}
