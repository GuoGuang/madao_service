package com.ibole.article.dao.backstage;

import com.ibole.pojo.article.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 文章分类
 **/


public interface CategoryDao extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {

    @Modifying
    @Query("delete from Category where id in (:ids)")
    void deleteBatch(List<String> ids);

}
