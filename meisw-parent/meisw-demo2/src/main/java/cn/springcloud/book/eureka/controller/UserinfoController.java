package cn.springcloud.book.eureka.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.springcloud.book.eureka.dao.UserinfoRepository;
import cn.springcloud.book.eureka.po.UserInfo;
import cn.springcloud.book.eureka.util.ApiReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户信息
 * @author meisw 2019-11-29
 */
@RestController
@RequestMapping("/UserInfo")
@Api(value = "/用户信息",description = "用户信息")
public class UserinfoController {
	
	@Autowired
	private UserinfoRepository userinfoRepository;
	
	/**
	 * 新增或编辑
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或编辑",notes = "新增或编辑")
	public Object save(@RequestBody UserInfo UserInfo) {
		return userinfoRepository.save(UserInfo);
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除",notes = "删除")
	public Object delete(@ApiParam(name = "id",value = "用户ID",required = true) @RequestParam(value = "id", required = true)Integer id) {
		Optional<UserInfo> UserInfo = userinfoRepository.findById(id);
		if (UserInfo.isPresent()) {
			userinfoRepository.deleteById(id);
			return ApiReturnUtil.success("删除成功");
		} else {
			return ApiReturnUtil.error("没有找到该对象");
		}
	}
	
	/**
	 * 查询
	 */
	@PostMapping("/find")
	@ApiOperation(value = "查询",notes = "查询")
	public Object find(@ApiParam(name = "id",value = "用户ID",required = true) @RequestParam(value = "id", required = true)Integer id) {
		Optional<UserInfo> UserInfo = userinfoRepository.findById(id);
		if (UserInfo.isPresent()) {
			return ApiReturnUtil.success(UserInfo.get());
		} else {
			return ApiReturnUtil.error("没有找到该对象");
		}
	}
	
	/**
	 * 分页查询
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页查询",notes = "分页查询")
	public Object list(UserInfo UserInfo, @RequestParam(required = false, defaultValue = "0") int pageNumber,
	        @RequestParam(required = false, defaultValue = "10") int pageSize) {
		
		// 创建匹配器，需要查询条件请修改此处代码
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		
		// 创建实例
		Example<UserInfo> example = Example.of(UserInfo, matcher);
		// 分页构造
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		return userinfoRepository.findAll(example, pageable);
	}
	
}
