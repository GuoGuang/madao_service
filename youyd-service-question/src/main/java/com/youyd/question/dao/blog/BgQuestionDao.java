package com.youyd.question.dao.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.question.pojo.Question;

/**
 * @description: 问题
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/

public interface BgQuestionDao extends BaseMapper<Question> {


	Question findQuestionDetail(String questionId);
}
