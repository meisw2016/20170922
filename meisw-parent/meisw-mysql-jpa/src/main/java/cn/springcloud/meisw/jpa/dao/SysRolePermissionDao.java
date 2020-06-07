package cn.springcloud.meisw.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.springcloud.meisw.jpa.po.SysRolePermission;

public interface SysRolePermissionDao extends JpaRepository<SysRolePermission, Long> {
	
	@Query(value = "select * from t_sys_role_permission where permission_id = :permissionId", nativeQuery = true)
	public List<SysRolePermission> queryByPermissionId(@Param("permissionId") long permissionId);
}
