package cn.springcloud.meisw.oauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.springcloud.meisw.oauth2.entity.UmRoleUser;


public interface UmRoleUserDao extends JpaRepository<UmRoleUser, Long> {
	
}
