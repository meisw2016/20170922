package cn.springcloud.book.eureka.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.springcloud.book.eureka.po.UserInfo;


/**
*  用户信息
* @author meisw 2019-11-29
*/
@Repository
public interface UserinfoRepository extends JpaRepository<UserInfo,Integer> {



}
