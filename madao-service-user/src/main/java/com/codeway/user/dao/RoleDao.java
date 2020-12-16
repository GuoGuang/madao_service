package com.madaoo.user.dao;

import com.madaoo.model.pojo.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role>, QuerydslPredicateExecutor<Role> {
	/**
	 * 查询当前用户的角色
	 *
	 * @param id 用户id
	 * @return 角色数组
	 */
	@Query(value = "SELECT * FROM us_role WHERE id in (SELECT role_id FROM us_user_role WHERE user_id = :id)", nativeQuery = true)
	Optional<List<Role>> findRolesOfUser(@Param("id") String id);

    @Modifying
    @Query("delete from Role where id in (:ids)")
    void deleteBatch(List<String> ids);
}
