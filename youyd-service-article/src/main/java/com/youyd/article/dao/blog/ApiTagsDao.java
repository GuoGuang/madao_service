package com.youyd.article.dao.blog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Tags;

import java.util.ArrayList;


/**
 * @description 标签
 * @author LGG
 * @create 2018-09-26 16:21
 **/


public interface ApiTagsDao extends BaseMapper<Tags> {


	ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
