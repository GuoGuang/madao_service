package com.codeif.tweets.dao;


import com.codeif.pojo.tweets.TweetsComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * dao
 **/

public interface TweetsCommentDao extends JpaRepository<TweetsComment, Long> {

}
