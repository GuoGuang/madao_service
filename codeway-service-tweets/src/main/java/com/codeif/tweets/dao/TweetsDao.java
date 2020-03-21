package com.codeif.tweets.dao;


import com.codeif.pojo.tweets.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * dao
 **/

public interface TweetsDao extends JpaRepository<Tweets, Long> {
    /**
     * 根据上级ID查询吐槽列表（分页）
     * @param parentId
     * @return
     */
//	Tweets findTweetsByParentid(String parentId);

    /**
     * 更新 点赞数,回复数,分享数或者评论数 的通用更新方法
     */
    @Modifying
    @Query("delete from Category where id in (:ids)")
    void updateTweetsStatus(Tweets tweets);
}
