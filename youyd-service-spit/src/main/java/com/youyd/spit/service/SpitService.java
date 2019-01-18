package com.youyd.spit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youyd.spit.dao.SpitDao;
import com.youyd.spit.pojo.Spit;
import com.youyd.pojo.Result;
import com.youyd.utils.CodeCommonUtil;
import com.youyd.utils.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 吐槽服务
 * @author: LGG
 * @create: 2018-09-27
 **/
@Service
public class SpitService {

	@Autowired
	private SpitDao spitDao;

	/**
	 * 按照条件查询全部标签
	 * @param map
	 * @param page
	 * @param size
	 * @return
	 */
	public Result findSpitByCondition(Map map, Integer page, Integer size) {
		QueryWrapper queryWrapper = new QueryWrapper();
		List result = spitDao.selectList(queryWrapper);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Spit findSpitByPrimaryKey(String id) {
		return spitDao.selectById(id);
	}

	/**
	 * 发布吐槽（或吐槽评论）
	 * @param spit
	 */
	public void insertSpit(Spit spit){
		spit.setPublishTime(new Date());//发布日期
		spit.setVisitsCount(0L);//浏览量
		spit.setShareCount(0L);//分享数
		spit.setThumbUpCount(0L);//点赞数
		spit.setReplyCount(0L);//回复数
		spit.setIsVisible(1);//是否可见
		// 如果存在上级ID,则是上级评论的子评论,给父评论回复数加一
		if(StringUtils.isNotBlank(spit.getParentId())){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("_count","reply_count"); // 回复数 字段
			map.put("id",spit.getParentId());
			spitDao.updateCountByPrimaryKey(map);
		}
		spitDao.insert(spit);
	}

	/**
	 * 修改
	 * @param
	 */
	public void updateByPrimaryKeyfindive(Spit spit) {
		spitDao.updateById(spit);
	}

	/**
	 * 删除
	 * @param ids
	 */
	public void deleteById(String ids) {
		List<String> delIds = CodeCommonUtil.deletePart(ids);
		spitDao.deleteBatchIds(delIds);
	}

	/**
	 * 根据上级ID查询吐槽列表
	 * @param parentId : 上级ID
	 * @param page
	 * @param size
	 * @return Result
	 */
	public Result findSpitByParentid(String parentId, Integer page, Integer size){
		Spit result = spitDao.findSpitByParentid(parentId);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 点赞
	 * @param id:被点赞的吐槽id
	 */
	public void updateThumbUp(String id){
		HashMap<Object, Object> map = new HashMap<>();
		map.put("_count","thumb_up_count"); // 点赞 字段
		map.put("id",id);
		spitDao.updateCountByPrimaryKey(map);
	}
}



