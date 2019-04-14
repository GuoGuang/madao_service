package com.youyd.article.dao.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.article.pojo.Tags;
import com.youyd.pojo.QueryVO;

import java.util.ArrayList;


/**
 * @description: 标签
 * @author: LGG
 * @create: 2018-09-26 16:21
 **/


public interface BgTagsDao extends BaseMapper<Tags> {


	ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
