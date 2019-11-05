package cn.springcloud.meisw.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.meisw.jpa.annotation.Meisw;
import cn.springcloud.meisw.jpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
@Api(value = "/user", description = "用户测试")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	// @Cacheable(value="poemInfo",key = "targetClass + methodName +#p0") //自动根据方法生成缓存
	// @Caching(
	// cacheable = {
	// @Cacheable(value = "/users",key="#name")
	// }
	// put = {
	// @CachePut(value= "/poemInfo",key = "#result.name"),
	// @CachePut(value= "/poemInfo",key = "#result.age")
	// }
	// )
	
	/**
	 * 
	 * @author meisw 2019年11月1日 上午9:29:58 @Method: query @Description: 用户查询，通过name查询 @param name @return @throws
	 */
	@Meisw
	@PostMapping(value = "/query")
	@ApiOperation(value = "/query", notes = "用户查询")
	public Map<String, Object> query(@RequestParam("name") String name) {
		long start = System.currentTimeMillis();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "200");
		if (StringUtils.isEmpty(name)) {
			result.put("msg", "name or age 不能为空!");
			result.put("status", "500");
			return result;
		}
		List<cn.springcloud.meisw.jpa.db1.po.User> user = userService.findByUserName(name);
		result.put("data", user);
		log.info("调用接口费时：{}", String.valueOf(System.currentTimeMillis() - start));
		return result;
	}
	
	/**
	 * 
	 * @author meisw 2019年11月1日 上午9:30:15 @Method: add @Description: 用户新增 @param name @param age @return @throws
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "/add", notes = "用户新增")
	public Map<String, Object> add(@RequestParam("name") String name, @RequestParam("age") Integer age) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "200");
		if (StringUtils.isEmpty(age) || StringUtils.isEmpty(name)) {
			result.put("msg", "name or age 不能为空!");
			result.put("status", "500");
			return result;
		}
		cn.springcloud.meisw.jpa.db1.po.User user = new cn.springcloud.meisw.jpa.db1.po.User();
		user.setName(name);
		user.setAge(10);
		Long id = userService.saveUser(user);
		result.put("data", id);
		return result;
	}
	
	@PostMapping(value = "/update")
	@ApiOperation(value = "/update", notes = "用户新增")
	public Map<String, Object> update(@RequestParam("name") String name, @RequestParam("age") Integer age,
	        @RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "200");
		if (StringUtils.isEmpty(age) || StringUtils.isEmpty(name) || StringUtils.isEmpty(id)) {
			result.put("msg", "name or age or id 不能为空!");
			result.put("status", "500");
			return result;
		}
		cn.springcloud.meisw.jpa.db1.po.User user = new cn.springcloud.meisw.jpa.db1.po.User();
		user = userService.selectUserById(id);
		user.setName(name);
		user.setAge(10);
		user = userService.updateUser(user);
		result.put("data", user);
		return result;
	}
	
	@PostMapping(value = "/delete")
	@ApiOperation(value = "/delete", notes = "用户删除")
	public Map<String, Object> delete(@RequestParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "200");
		if (StringUtils.isEmpty(id)) {
			result.put("msg", "id不能为空!");
			result.put("status", "500");
			return result;
		}
		userService.deleteUserById(id);
		result.put("msg", "id=" + id + "的用户删除成功!");
		return result;
	}
}
