package com.madao.base.dao;

import com.madao.model.entity.base.OptLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface OptLogDao extends JpaRepository<OptLog, String>, JpaSpecificationExecutor<OptLog>, QuerydslPredicateExecutor<OptLog> {


    @Modifying
    @Query("delete from OptLog where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);
}
