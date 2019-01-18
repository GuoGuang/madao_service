package com.youyd.article.dao;

import com.youyd.article.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description: 答案
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface ReplyDao {

	List findReplyByCondition(Map map);

	Reply findReplyByPrimaryKey(String id);

	void deleteByPrimaryKey(List list);

	void insertReply(Reply reply);

	void updateByReplySelective(Reply reply);

}
