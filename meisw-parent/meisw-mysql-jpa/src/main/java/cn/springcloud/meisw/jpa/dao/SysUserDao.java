package cn.springcloud.meisw.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.springcloud.meisw.jpa.po.SysUser;

public interface SysUserDao extends JpaRepository<SysUser, Long> {
	
	@Query(value = "select * from t_sys_user where username = :username", nativeQuery = true)
	List<SysUser> findByUsername(@Param("username") String username);
	
	@Query(value = "select * from t_sys_user where token = :token", nativeQuery = true)
	public List<SysUser> findByToken(@Param("token") String token);
}
