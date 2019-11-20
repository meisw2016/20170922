package cn.springcloud.book.eureka.service;

import cn.springcloud.book.eureka.security.IompUser;

/**
 * 
 * <p></p>
 * @author meisw 2019年7月1日 下午5:15:03
 * @ClassName KibanaOperateService
 * @Description 同步云平台登录用户的用户信息，机构信息，权限信息到kibana
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019年7月1日
 * @modify by reason:{方法名}:{原因}
 */
public interface KibanaOperateService {
	
	void permissionConsistent(IompUser iompUser);
}
