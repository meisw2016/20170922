package cn.springcloud.book.eureka.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;

import cn.springcloud.book.eureka.security.IompUser;

/**
 * 
 * <p></p>
 * @author meisw 2019年7月1日 下午4:58:51
 * @ClassName IompSecurityUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年7月1日
 * @modify by reason:{方法名}:{原因}
 */
public class IompSecurityUtil extends SecurityUtils{
	/**
	 * 
	 * @author meisw 2019年7月1日 下午4:59:26
	 * @Method: getCurrentUserInfo 
	 * @Description: 获取当前用户信息
	 * @return 
	 * @throws
	 */
	public static IompUser getCurrentUserInfo() {
		return (IompUser)getSubject().getPrincipal();
	}
	/**
	 * 
	 * @author meisw 2019年7月1日 下午5:02:47
	 * @Method: getResInfoAppCompBysysCode 
	 * @Description: 返回当前用户包含的子系统ID列表
	 * @param sysCode 应用系统配置在同意门户的系统编码
	 * @return 
	 * @throws
	 */
	public static List<Long> getResInfoAppCompBysysCode(String sysCode){
		if(sysCode == null) {
			return null;
		}
		try {
			IompUser iompUser  = getCurrentUserInfo();
			if(iompUser == null) {
				return null;
			}
			List<Long> resId  = iompUser.getPermissionResMap().get(sysCode);
			return resId;
		}catch(Exception e) {
			return null;
		}
	}
	/**
	 * 
	 * @author meisw 2019年7月1日 下午5:04:20
	 * @Method: getCurrentUserId 
	 * @Description: 获取当前用户的用户ID
	 * @return 
	 * @throws
	 */
	public static Long getCurrentUserId() {
		return getCurrentUserInfo().getUserId();
	}
	/**
	 * 
	 * @author meisw 2019年7月1日 下午5:05:00
	 * @Method: getCurrentUserLoginName 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	public static String getCurrentUserLoginName() {
		return getCurrentUserInfo().getLoginName();
	}
}
