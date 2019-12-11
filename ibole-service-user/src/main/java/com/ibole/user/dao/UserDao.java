package com.ibole.user.dao;

import com.ibole.pojo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户管理
 **/

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


	@Modifying
	@Query("delete from User where id in (:ids)")
	void deleteBatch(List<String> ids);

}
