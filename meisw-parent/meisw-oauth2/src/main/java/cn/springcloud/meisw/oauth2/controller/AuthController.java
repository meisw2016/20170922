package cn.springcloud.meisw.oauth2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.jpa.common.OutputData;
import cn.springcloud.meisw.oauth2.entity.UmUser;
import cn.springcloud.meisw.oauth2.service.UmRoleService;
import cn.springcloud.meisw.oauth2.service.UmUserService;

@RestController
@RequestMapping("/auth")
@Api(value = "/auth",description = "用户权限管理")
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private UmUserService umUserService;
	@Autowired
	private UmRoleService umRoleService;
	@Autowired
	private RedisTokenStore redisTokenStore;
	
	@SuppressWarnings("rawtypes")
    @RequestMapping(value= "/user",method = RequestMethod.POST)
	@ApiOperation(value = "/user",notes = "添加用户")
	public OutputData add(@RequestBody UmUser umUser){
		OutputData out = new OutputData().returnSuccess();
		try {
	        umUserService.saveUser(umUser);
        } catch (MeiswException e) {
        	log.error("添加用户服务异常：{}",e);
        	out.returnFail(e.getMessage());
        }
		return out;
	}
	
	@SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ApiOperation(value = "/delete",notes = "删除用户")
	public OutputData deleteUser( @ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) Long id){
		OutputData out = new OutputData().returnSuccess();
		try {
	        umUserService.deleteUser(id);
        } catch (MeiswException e) {
	        log.error("删除用户服务异常：{}",e);
	        out.returnFail(e.getMessage());
        }
		return out;
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ApiOperation(value = "/update",notes = "修改用户")
	@SuppressWarnings("rawtypes")
    public OutputData updateUser(@RequestBody UmUser umUser){
		OutputData out = new OutputData().returnSuccess();
		try {
	        umUserService.updateUser(umUser);
        } catch (MeiswException e) {
	        log.error("修改用户服务异常：{}",e);
	        out.returnFail(e.getMessage());
        }
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
	@ApiOperation(value = "/findAllUser",notes = "获取用户列表")
	public OutputData findAllUser(){
		OutputData out = new OutputData().returnSuccess();
		try {
	        List<UmUser> list = umUserService.findAllUser();
	        out.setData(list);
        } catch (MeiswException e) {
	        log.error("获取用户列表服务异常：{}",e);
	        out.returnFail(e.getMessage());
        }
		return out;
	}
	
//	public OutputData login(@ApiParam(name = "username", value = "username", required = true) @RequestParam(value = "username", required = true) Long username,
//			@ApiParam(name = "password", value = "password", required = true) @RequestParam(value = "password", required = true) Long password){
//		
//	}
}
