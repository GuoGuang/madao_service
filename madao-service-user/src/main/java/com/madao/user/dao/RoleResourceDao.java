package com.madao.user.dao;

import com.madao.model.entity.user.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface RoleResourceDao extends JpaRepository<RoleResource, String>,
		JpaSpecificationExecutor<RoleResource>, QuerydslPredicateExecutor<RoleResource> {

	void deleteByRoleIdIn(List<String> roleId);

	void deleteByResourceIdIn(List<String> resId);
}
