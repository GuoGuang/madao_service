package com.codeway.article.dao.backstage;

import com.codeway.pojo.article.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsDao extends JpaRepository<Tags, String>,
        JpaSpecificationExecutor<Tags>, QuerydslPredicateExecutor<Tags> {

    @Modifying
    @Query("delete from Tags where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

}
