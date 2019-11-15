package com.ibole.article.dao.backstage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Tags;

import java.util.ArrayList;


/**
 * @description 标签
 * @author LGG
 * @create 2018-09-26 16:21
 **/


public interface TagsDao extends BaseMapper<Tags> {


	ArrayList<Tags> findTagsByCondition(QueryVO queryVO);
}
