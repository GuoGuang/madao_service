package com.madao.article.dao.backstage;

import com.madao.model.entity.article.Category;
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
public interface CategoryDao extends JpaRepository<Category, String>,
        JpaSpecificationExecutor<Category>, QuerydslPredicateExecutor<Category> {

    @Modifying
    @Query("delete from Category where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

}
