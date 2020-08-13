package com.codeway.base.service.backstage;


import com.codeway.model.QueryVO;
import com.codeway.model.pojo.base.Music;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音乐
 **/
@Service
public class MusicService {

//	private final MusicDao musicDao;
//
//	public MusicService(MusicDao musicDao) {
//		this.musicDao = musicDao;
//	}

	/**
	 * 条件查询字典
	 *
	 * @param dict    字典实体
	 * @param queryVO 查询参数
	 * @return List
	 */
	public List<Music> findMusicByCondition(Music dict, QueryVO queryVO) {
//		Page<Music> pr = new Page<>(queryVO.getPageNum(),queryVO.getPageSize());
//		LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
//
//		IPage<Music> dictIPage = musicDao.selectPage(pr, queryWrapper);
//		return dictIPage;
		return null;
	}

	/**
	 * 按照字典类型获取树形字典
	 * @param dict 字典实体
	 * @return List
	 */
	public List<Music> fetchMusicTreeList(Music dict) {
//		LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
//		queryWrapper.eq(Music::getType,dict.getType());
//		return musicDao.selectList(queryWrapper);

		return null;
	}

	public Music findMusicById(String resId) {
//		return musicDao.selectById(resId);

		return null;
	}

	public boolean updateByPrimaryKey(Music resources) {
//		int i = musicDao.updateById(resources);
//		return SqlHelper.retBool(i);
		return true;
	}

	public boolean insertMusicSelective(Music dict) {
//		int insert = musicDao.insert(dict);
//		return SqlHelper.retBool(insert);

		return true;
	}

	public boolean deleteMusicByIds(List<String> resId) {
//		int i = musicDao.deleteBatchIds(resId);
//		return SqlHelper.retBool(i);
		return true;
	}
}
