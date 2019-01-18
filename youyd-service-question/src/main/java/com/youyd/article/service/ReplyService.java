package com.youyd.article.service;


import java.util.List;

import com.youyd.article.dao.ReplyDao;
import com.youyd.article.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ReplyService {

	@Autowired
	private ReplyDao replyDao;


	/**
	 * 查询全部列表
	 * @return
	 */
	public List findReplyByCondition() {
		return replyDao.findReplyByCondition(null);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Reply findReplyByPrimaryKey(String id) {
		return replyDao.findReplyByPrimaryKey(id);
	}

	/**
	 * 增加
	 * @param reply
	 */
	public void insertReply(Reply reply) {
		replyDao.insertReply(reply);
	}

	/**
	 * 修改
	 * @param reply
	 */
	public void updateByReplySelective(Reply reply) {
		replyDao.updateByReplySelective(reply);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteByPrimaryKey(String id) {
		replyDao.deleteByPrimaryKey(null);
	}
}
