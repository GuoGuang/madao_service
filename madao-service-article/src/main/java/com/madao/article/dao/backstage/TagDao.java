package com.madao.article.dao.backstage;

import com.madao.model.pojo.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TagDao extends JpaRepository<Tag, String>,
		JpaSpecificationExecutor<Tag>, QuerydslPredicateExecutor<Tag> {

	@Modifying
	@Query("delete from Tag where id in (:ids)")
	void deleteBatch(@Param("ids") List<String> ids);

	@Query(value = "SELECT * FROM ar_tag WHERE id in (SELECT tag_id FROM ar_article_tag WHERE article_id = :articleId)", nativeQuery = true)
	Optional<ArrayList<Tag>> findTagsByArticleId(String articleId);

}
