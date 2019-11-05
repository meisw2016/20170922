package cn.springcloud.meisw.jpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.po.User;
import cn.springcloud.meisw.jpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/user")
@Api(value = "/user",description = "用户测试")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
//	@Autowired
//	private UserDao userDao;
	@Autowired
	private UserService userService;
	
//	@Cacheable(value="poemInfo",key = "targetClass + methodName +#p0")  //自动根据方法生成缓存
	@Caching(
			cacheable = {
					@Cacheable(value = "/users",key="#name"),
					@Cacheable(value = "/users",key="#age")
				}
//			put = {
//				@CachePut(value= "/poemInfo",key = "#result.name"),
//				@CachePut(value= "/poemInfo",key = "#result.age")	
//			}
			)
//    @PostMapping(value = "/poemInfo")
//	@ApiOperation(value = "/poemInfo",notes = "用户查询")
//    public Map<String,Object> User(@RequestParam("name") String name, @RequestParam("age")Integer age) {
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put("status", "200");
//        if(StringUtils.isEmpty(age) || StringUtils.isEmpty(name)){
//        	result.put("msg", "name or age 不能为空!");
//        	result.put("status", "500");
//            return result;
//        }
//        cn.springcloud.meisw.jpa.po.User user = userDao.findByNameAndAge(name, age);
//        result.put("data", user);
//        return result;
//    }
	
	@RequestMapping(value = "/put",method = RequestMethod.POST)
	@ApiOperation(value = "/put",notes = "用户新增")
	public User put(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/able",method = RequestMethod.GET)
	@ApiOperation(value = "/able",notes = "用户查询")
	public User cacheable(@ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id",required = true)Long id) {
		return userService.selectUserById(id);
	}
	
	@RequestMapping(value = "/evit",method = RequestMethod.POST)
	@ApiOperation(value = "/evit",notes = "用户删除")
	public String evit(@ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id",required = true)Long id) {
		userService.remove(id);
		return "OK";
	}
}
