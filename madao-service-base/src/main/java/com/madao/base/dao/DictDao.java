package com.madao.base.dao;

import com.madao.model.entity.base.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface DictDao extends JpaRepository<Dict, String>, JpaSpecificationExecutor<Dict>, QuerydslPredicateExecutor<Dict> {

    @Modifying
    @Query("delete from Dict where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

    Optional<List<Dict>> findAllByType(String type);

    Optional<List<Dict>> findByParentId(String parentId);
}
