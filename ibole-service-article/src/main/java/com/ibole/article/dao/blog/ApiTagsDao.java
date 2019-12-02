package com.ibole.article.dao.blog;

import com.ibole.pojo.article.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 标签
 **/


public interface ApiTagsDao extends JpaRepository<Tags, Long>, JpaSpecificationExecutor<Tags> {


//	ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
