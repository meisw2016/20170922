package cn.springcloud.meisw.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.springcloud.meisw.jpa.po.SysUserRole;

public interface SysUserRoleDao extends JpaRepository<SysUserRole, Long>{
	
	@Query(value = "select * from t_sys_user_role where user_id = :userId",nativeQuery = true)
	public List<SysUserRole> queryByUserId(@Param("userId")Long userId);
}
