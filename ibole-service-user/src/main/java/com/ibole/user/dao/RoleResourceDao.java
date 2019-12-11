package com.ibole.user.dao;


import com.ibole.pojo.user.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 角色_资源管理
 **/

public interface RoleResourceDao extends JpaRepository<RoleResource, String>, JpaSpecificationExecutor<RoleResource> {


	@Modifying
	@Query("delete from RoleResource where us_role_id = :id")
	void deleteByUsRoleId(@Param("id") String id);
}
