package cn.springcloud.meisw.websocket.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.springcloud.meisw.websocket.config.WebSocketServer;

/**
 * 
 * <p></p>
 * @author meisw 2019年11月18日 下午6:03:18
 * @ClassName SystemController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = "/system")
public class SystemController {
	
	private static final Logger log = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * 跳转页面，并且
	 * @author meisw 2019年11月18日 下午7:27:59
	 * @Method: socket 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/index/{userId}",method = RequestMethod.GET)
	public ModelAndView socket(@PathVariable String userId) {
		ModelAndView mav = new ModelAndView("/socket1");
		mav.addObject("userId", userId);
		return mav;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@ResponseBody
	@RequestMapping(value = "/socket/push/{cid}")
	public Map pushToWeb(@PathVariable String cid,String message) {
		Map result = new HashMap();
		try {
			WebSocketServer.sendInfo(message, cid);
			result.put("code", 200);
			result.put("msg", "message");
		} catch (IOException e) {
			log.error("消息发送失败!");
		}
		return result;
	}
}
