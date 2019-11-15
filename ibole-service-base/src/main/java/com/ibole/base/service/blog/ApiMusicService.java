package com.ibole.base.service.blog;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ibole.base.dao.MusicDao;
import com.ibole.pojo.base.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音乐
 **/
@Service
public class ApiMusicService {

	private final MusicDao musicDao;

	@Autowired
	public ApiMusicService(MusicDao musicDao) {
		this.musicDao = musicDao;
	}

	/**
	 * 查询所有音乐
	 * @return List
	 */
	public List<Music> findMusicByCondition() {
		LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
		List<Music> musics = musicDao.selectList(queryWrapper);
		return musics;
	}

	/**
	 * 音乐详情
	 * @return List
	 */
	public Music findMusicDetail(String resId) {
		return musicDao.selectById(resId);
	}

}
