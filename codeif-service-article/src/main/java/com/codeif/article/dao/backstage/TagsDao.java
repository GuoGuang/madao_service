package com.codeif.article.dao.backstage;

import com.codeif.pojo.article.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * 标签
 **/


public interface TagsDao extends JpaRepository<Tags, String>,
        JpaSpecificationExecutor<Tags>, QuerydslPredicateExecutor<Tags> {

    @Modifying
    @Query("delete from Tags where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

//	ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
