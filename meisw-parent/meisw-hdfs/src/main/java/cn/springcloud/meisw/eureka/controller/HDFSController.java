package cn.springcloud.meisw.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.eureka.util.HDFSUtil;
import cn.springcloud.meisw.eureka.util.OutputData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/hdfs")
@Api(value = "/hdfs",description = "HDFS文件操作管理")
public class HDFSController {
	
	@Value("${hdfs.hadoopHome}")
	private String hadoopHome;
	
	@Value("${hdfs.address}")
	private String hdfsUrl;
	
	@Value("${hdfs.user}")
	private String user;
	
	@Autowired
	private HDFSUtil hdfsUtil;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "上传文件",httpMethod = "POST",notes = "上传文件",produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/updateHDFS",method = RequestMethod.POST)
	public OutputData uploadHDFS(
			@ApiParam(name = "srcPath",value = "源文件地址",required = true)@RequestParam(value = "srcPath",required = true)String srcPath,
			@ApiParam(name = "dstPath",value = "上传地址",required = true)@RequestParam(value = "dstPath",required = true)String dstPath
			) {
		OutputData out = new OutputData().returnFail();
		try {
			hdfsUtil.updateHDFS(srcPath, dstPath, hdfsUrl, user);
			out.returnSuccess().setMessage("文件上传成功!");
		}catch(Exception e) {
			out.returnFail(e.getMessage());
		}
		return out;
	}
}
