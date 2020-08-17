package com.codeway.user.dao;

import com.codeway.model.pojo.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, String>,
		JpaSpecificationExecutor<UserRole>, QuerydslPredicateExecutor<UserRole> {

	void deleteByUserIdIn(List<String> userIds);

	void deleteByRoleIdIn(List<String> roleId);
}
