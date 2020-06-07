package cn.springcloud.meisw.oauth2.service;

import cn.springcloud.meisw.jpa.common.MeiswException;
import cn.springcloud.meisw.oauth2.entity.UmRole;


public interface UmRoleService {

	public void saveRole(UmRole umRole)throws MeiswException;
	
	public void deleteRole(Long id)throws MeiswException;
}
