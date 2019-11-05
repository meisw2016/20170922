package cn.springcloud.meisw.jpa.db1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.springcloud.meisw.jpa.db1.po.User;


public interface UserDao extends JpaRepository<User,Long>{
	
	User findByName(String name);
	
	User findByNameAndAge(String name,Integer age);
	
	@Query("from User u where u.name = :name")
	User findUser(@Param("name") String name);
	
	@Query("from User u where u.name = :name")
	List<User> findUsersByName(@Param("name")String name);
	
}
