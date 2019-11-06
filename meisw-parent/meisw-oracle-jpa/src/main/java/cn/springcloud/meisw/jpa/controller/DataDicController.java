package cn.springcloud.meisw.jpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.service.DataDicService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "/dic",description = "字典")
@RequestMapping(value = "/dic")
public class DataDicController {
	
	private static final Logger log = LoggerFactory.getLogger(DataDicController.class);
	
	@Autowired
	private DataDicService dataDicService;
	
//	public OutputData addDic(@RequestBody )
}
