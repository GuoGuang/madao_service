package com.madao.user.dao;

import com.madao.model.pojo.user.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface RoleResourceDao extends JpaRepository<RoleResource, String>,
        JpaSpecificationExecutor<RoleResource>, QuerydslPredicateExecutor<RoleResource> {

    void deleteByRoleIdIn(List<String> roleId);

    void deleteByResourceIdIn(List<String> resId);
}
