package cn.springcloud.meisw.jpa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.service.UserService;

@RestController
@RequestMapping(value = "/user")
@Api(value = "/user",description = "用户测试")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
//	@Cacheable(value="poemInfo",key = "targetClass + methodName +#p0")  //自动根据方法生成缓存
//	@Caching(
//			cacheable = {
//					@Cacheable(value = "/users",key="#name")
//				}
//			put = {
//				@CachePut(value= "/poemInfo",key = "#result.name"),
//				@CachePut(value= "/poemInfo",key = "#result.age")	
//			}
//			)
	
    @PostMapping(value = "/query")
	@ApiOperation(value = "/query",notes = "用户查询")
    public Map<String,Object> query(@RequestParam("name") String name) {
		long start = System.currentTimeMillis();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", "200");
        if(StringUtils.isEmpty(name)){
        	result.put("msg", "name or age 不能为空!");
        	result.put("status", "500");
            return result;
        }
        cn.springcloud.meisw.jpa.db1.po.User user = userService.findByUserName(name);
        result.put("data", user);
        log.info("调用接口费时：{}",String.valueOf(System.currentTimeMillis()-start));
        return result;
    }
	@PostMapping(value = "/add")
	@ApiOperation(value = "/add",notes = "用户新增")
	public Map<String,Object> add(@RequestParam("name") String name, @RequestParam("age")Integer age) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", "200");
		if(StringUtils.isEmpty(age) || StringUtils.isEmpty(name)){
			result.put("msg", "name or age 不能为空!");
			result.put("status", "500");
			return result;
		}
		cn.springcloud.meisw.jpa.db1.po.User  user = new cn.springcloud.meisw.jpa.db1.po.User();
		user.setName(name);
		user.setAge(10);
		Long id = userService.saveUser(user);
		result.put("data", id);
		return result;
	}
}
