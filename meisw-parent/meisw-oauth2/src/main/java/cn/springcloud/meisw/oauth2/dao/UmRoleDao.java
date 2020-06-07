package cn.springcloud.meisw.oauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.springcloud.meisw.oauth2.entity.UmRole;


public interface UmRoleDao extends JpaRepository<UmRole, Long> {
	
	@Query(value = "select r.role  from um_t_user u,um_t_role r,um_t_role_user ru where u.id = ru.user_id and r.id = ru.role_id",nativeQuery = true)
	public String getRoles(@Param("userId")Long userId);
	
}
