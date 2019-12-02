package com.ibole.article.dao.backstage;

import com.ibole.pojo.article.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 标签
 **/


public interface TagsDao extends JpaRepository<Tags, String>, JpaSpecificationExecutor<Tags> {


	@Modifying
	@Query("delete from Dict where id in (:ids)")
	void deleteBatch(List<String> ids);

//	ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
