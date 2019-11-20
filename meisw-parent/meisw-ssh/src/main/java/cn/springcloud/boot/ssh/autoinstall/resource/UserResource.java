package cn.springcloud.boot.ssh.autoinstall.resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.com.yusys.yusp.autoinstall.domain.ResultDto;
import cn.com.yusys.yusp.autoinstall.service.UserService;
import cn.com.yusys.yusp.autoinstall.utils.RsaUtil;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private UserService userService;

	/**
	 * 获取公钥
	 *
	 * @return 返回公钥
	 */
	@GetMapping("/publickey")
	public ResultDto<String> getPublicKey() {
		ResultDto<String> resultDto = new ResultDto<String>();
		String publicKey;
		try {
			publicKey = userService.getKey("public_key");
			resultDto.setData(publicKey);
		} catch (IOException e) {
			resultDto.setMessage("获取公钥失败");
		}
		return resultDto;
	}

	/**
	 * @param data 用户登录信息
	 * @return
	 * @Title: getUserByUserId
	 * @date 2018年10月29日
	 * @Description: 验证登录信息
	 */
	@PostMapping("/{userId}")
	public ResultDto<Object> getUserByUserId(@RequestBody Map<String, String> data) throws Exception {
		PrivateKey privateKey = RsaUtil.getPrivateKey(userService.getKey("private_key"));
		String pwd = RsaUtil.decrypt(data.get("password"), privateKey);
		String username = data.get("username");
		if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
			return new ResultDto<>(0, 0, "用户名或密码不能为空", "");
		}
		if (!"admin".equals(username) || !username.equals(pwd)) {
			return new ResultDto<>(0, 0, "用户名或密码错误", "");
		}
		return new ResultDto<>(1, 1, "登录成功", "");
	}

}