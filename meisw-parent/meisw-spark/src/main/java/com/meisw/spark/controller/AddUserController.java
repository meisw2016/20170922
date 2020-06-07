//package cn.springcloud.meisw.eureka.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//import java.util.TreeMap;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 
// * <p></p>
// * @author meisw 2019年6月25日 上午8:41:20
// * @ClassName AddUserController
// * @Description 添加子用户
// * @version V1.0   
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: {修改人} 2019年6月25日
// * @modify by reason:{方法名}:{原因}
// */
//@RestController
//@RequestMapping("/userController")
//public class AddUserController {
//
//	/**
//	 * 
//	 * @author meisw 2019年6月25日 上午8:43:27
//	 * @Method: addUser 
//	 * @Description: 添加子用户
//	 * 接口请求域名：cam.tencentcloudapi.com
//	 * 默认接口请求频率限制：20次/秒
//	 * @return 
//	 * @throws
//	 */
//	public String addUser() {
//		String tmpName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//		Map<String,Object> request = new TreeMap<String,Object>();
//		request.put("Action", "AddUser");
//		request.put("Version", "2019-01-16");
////		request.put("Region", "");
//		request.put("Name", tmpName);
//		request.put("Remark", "test");
//		request.put("ConsoleLogin", 1);
//		request.put("UseApi", "1");
//		request.put("Password", "Admin123bcd456");
//		request.put("NeedResetPassword", 0);
//		request.put("PhoneNum", null);
//		request.put("CountryCode", null);
//		request.put("Email", null);
//		return null;
//	}
//	
//	/**
//	 * SecretId: AKIDLSKwKpi8yZXGjwquZzBIAnBTXO1VE50m  
//	 * SecretKey: ZzJTPBi5Gm8FAtY4QcOxIOJ9pKZeVCmB  
//	 * @author meisw 2019年6月25日 上午8:59:54
//	 * @Method: getBaseParam 
//	 * @Description: TODO
//	 * @return 
//	 * @throws
//	 */
//	public Map<String,Object> getBaseParam(){
//		Map<String,Object> base = new TreeMap<String,Object>();
//		base.put("X-TC-Action", "");
//		base.put("X-TC-Region", "");
//		base.put("X-TC-Timestamp", "");
//		base.put("X-TC-Version", "");
//		base.put("Authorization", "");
//		base.put("X-TC-Token", null);
//		return base;
//	}
//}
