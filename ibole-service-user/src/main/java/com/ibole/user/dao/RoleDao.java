package com.ibole.user.dao;

import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 角色管理
 **/

public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    @Query(value = "SELECT * FROM us_user WHERE id in (SELECT us_user_id FROM us_user_role WHERE us_role_id = :roleId)"
            , nativeQuery = true)
    List<User> findUsersOfRole(String roleId);

    /**
     * 查询当前用户的角色
     *
     * @param id 用户id
     * @return 角色数组
     */

    @Query(value = "SELECT * FROM us_role WHERE id in (SELECT us_role_id FROM us_user_role WHERE us_user_id = :id)", nativeQuery = true)
    List<Role> findRolesOfUser(@Param("id") String id);

    @Modifying
    @Query("delete from Role where id in (:ids)")
    void deleteBatch(List<String> ids);
}
