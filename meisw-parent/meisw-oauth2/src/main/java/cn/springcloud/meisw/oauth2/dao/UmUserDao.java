package cn.springcloud.meisw.oauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.springcloud.meisw.oauth2.entity.UmUser;

public interface UmUserDao extends JpaRepository<UmUser, Long> {
	
	@Query(value = "select count(*) from um_t_user where account = :account", nativeQuery = true)
	public long queryCountByAccount(@Param("account") String accoun);
	
	@Query(value = "select * from um_t_user where account = :username", nativeQuery = true)
	public UmUser findByUsername(@Param("username") String username);
}
