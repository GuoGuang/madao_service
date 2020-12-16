package com.madaoo.user.dao;


import com.madaoo.model.pojo.user.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ResourceDao extends JpaRepository<Resource, String>, JpaSpecificationExecutor<Resource>, QuerydslPredicateExecutor<Resource> {

    @Query(value = "SELECT * FROM us_resource WHERE id IN (SELECT resource_id FROM us_role_resource WHERE role_id in (:resId))", nativeQuery = true)
    Set<Resource> findResourceByRoleIds(@Param("resId") List<String> resId);

    @Modifying
    @Query("delete from Resource where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);
}
