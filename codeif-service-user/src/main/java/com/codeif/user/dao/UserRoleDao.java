package com.codeif.user.dao;


import com.codeif.pojo.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

/**
 * 用户_角色管理
 **/

public interface UserRoleDao extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole>, QuerydslPredicateExecutor<UserRole> {


    @Modifying
    @Query("delete from UserRole where us_user_id in (:id)")
    void deleteBytUsUserId(@Param("id") String id);
}
