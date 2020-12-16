package com.madaoo.base.dao;

import com.madaoo.model.pojo.base.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictDao extends JpaRepository<Dict, String>, JpaSpecificationExecutor<Dict>, QuerydslPredicateExecutor<Dict> {

	@Modifying
	@Query("delete from Dict where id in (:ids)")
	void deleteBatch(@Param("ids") List<String> ids);

	Optional<List<Dict>> findAllByType(String type);

	Optional<List<Dict>> findByParentId(String parentId);
}
