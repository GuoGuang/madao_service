package com.ibole.tweets.dao;


import com.ibole.pojo.tweets.TweetsComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * dao
 **/

public interface TweetsCommentDao extends JpaRepository<TweetsComment, Long> {

}
