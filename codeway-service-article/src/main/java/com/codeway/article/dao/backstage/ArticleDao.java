package com.codeway.article.dao.backstage;

import com.codeway.model.pojo.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, String>,
        JpaSpecificationExecutor<Article>, QuerydslPredicateExecutor<Article> {

    @Modifying
    @Query("delete from Article where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

    /**
     * 审核文章
     *
     * @param id
     */
	@Modifying
	@Query("update Article set state='1' where id=:id")
	void examine(@Param("id") String id);

}
