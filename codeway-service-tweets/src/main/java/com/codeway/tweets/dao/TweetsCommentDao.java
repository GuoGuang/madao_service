package com.codeway.tweets.dao;


import com.codeway.pojo.tweets.TweetsComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * dao
 **/

public interface TweetsCommentDao extends JpaRepository<TweetsComment, Long> {

}
