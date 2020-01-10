package com.ibole.article.dao.backstage;

import com.ibole.pojo.article.ArticleTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ArticleTagsDao extends JpaRepository<ArticleTags, String>,
		JpaSpecificationExecutor<ArticleTags>, QuerydslPredicateExecutor<ArticleTags> {

	Integer countByTagsId(String tagsId);
}
