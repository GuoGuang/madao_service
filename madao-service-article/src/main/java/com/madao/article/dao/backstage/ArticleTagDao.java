package com.madao.article.dao.backstage;

import com.madao.model.entity.article.ArticleTag;
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
public interface ArticleTagDao extends JpaRepository<ArticleTag, String>,
		JpaSpecificationExecutor<ArticleTag>, QuerydslPredicateExecutor<ArticleTag> {

	List<ArticleTag> findAllByTagIdIn(List<String> collect);

	List<ArticleTag> findAllByArticleIdIn(List<String> articleIds);

	void deleteByTagIdIn(List<String> tagIds);

	void deleteByArticleIdIn(List<String> articleIds);
}
