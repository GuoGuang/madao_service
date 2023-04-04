package com.madao.user.service;

import com.madao.model.dto.BarrageMessage;
import com.madao.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 弹幕服务
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-25 22:34
 */
@Service
public class BarrageService {

	private static final String BARRAGE = "BARRAGE";

	@Autowired
	RedisUtil redisUtil;

	public void insert(BarrageMessage message) {
		redisUtil.lSet(BARRAGE, message);
	}

	public List<BarrageMessage> findAll() {
		return redisUtil.lGet(BARRAGE, 0, -1);
	}

}
