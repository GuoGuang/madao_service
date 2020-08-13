package com.codeway.user.dao;

import com.codeway.model.pojo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User>, QuerydslPredicateExecutor<User> {

    @Query(value = "SELECT * FROM us_user WHERE id in (SELECT user_id FROM us_user_role WHERE role_id = :roleId)"
            , nativeQuery = true)
    List<User> findUsersOfRole(@Param("roleId") String roleId);

    @Modifying
    @Query("delete from User where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

	Optional<User> findByAccount(String account);
}
