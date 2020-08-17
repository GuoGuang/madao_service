package com.codeway.article.dao.blog;

import com.codeway.model.pojo.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiTagsDao extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

}
