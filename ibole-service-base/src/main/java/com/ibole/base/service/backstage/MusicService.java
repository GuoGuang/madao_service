package com.ibole.base.service.backstage;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.ibole.base.dao.MusicDao;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.Music;
import com.ibole.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音乐
 **/
@Service
public class MusicService {

	private final MusicDao musicDao;

	@Autowired
	public MusicService(MusicDao musicDao) {
		this.musicDao = musicDao;
	}

	/**
	 * 条件查询字典
	 * @param dict 字典实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public IPage<Music> findMusicByCondition(Music dict, QueryVO queryVO) {
		Page<Music> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
		LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();

		IPage<Music> dictIPage = musicDao.selectPage(pr, queryWrapper);
		return dictIPage;
	}

	/**
	 * 按照字典类型获取树形字典
	 * @param dict 字典实体
	 * @return List
	 */
	public List<Music> fetchMusicTreeList(Music dict) {
		LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Music::getType,dict.getType());
		return musicDao.selectList(queryWrapper);
	}

	public Music findMusicById(String resId) {
		return musicDao.selectById(resId);
	}

	public boolean updateByPrimaryKey(Music resources) {
		int i = musicDao.updateById(resources);
		return SqlHelper.retBool(i);
	}

	public boolean insertMusicSelective(Music dict) {
		dict.setCreateAt(DateUtil.getTimestamp());
		dict.setUpdateAt(DateUtil.getTimestamp());
		int insert = musicDao.insert(dict);
		return SqlHelper.retBool(insert);
	}

	public boolean deleteMusicByIds(List<String> resId) {
		int i = musicDao.deleteBatchIds(resId);
		return SqlHelper.retBool(i);
	}
}
