package com.madao.base.controller.backstage;

import com.madao.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;

/**
 * 排队
 */
@Service
public class RedisQueryService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 获取医生zse名称
	 */
	public String getQueueName(String doctorId) {
		return "doctor-order-" + doctorId;
	}

	/**
	 * 判断ZSET集合中是否有某个元素
	 */
	public boolean isExistZSET(String key, String value) {
		HashMap<String,String> item = new HashMap();
		item.put("value", value);
		return stringRedisTemplate.opsForZSet().score(getQueueName(key), JsonUtil.toJsonString(item)) != null;
	}

	/**
	 * 获取队列当前排队总人数
	 */
	public Long curQueryNumber(String key) {
		return stringRedisTemplate.opsForZSet().size(getQueueName(key));
	}

	/**
	 * 排队
	 */
	public void push(String key, String value) {
		HashMap<String,String> item = new HashMap();
		item.put("value", value);
		stringRedisTemplate.opsForZSet().incrementScore(getQueueName(key), JsonUtil.toJsonString(item), Instant.now().toEpochMilli());
	}

	/**
	 * 获取自身位置
	 */
	public Long getSelfLocation(String key, String value) {
		HashMap<String,String> item = new HashMap();
		item.put("value", value);
		return Optional.ofNullable(stringRedisTemplate.opsForZSet().rank(getQueueName(key), JsonUtil.toJsonString(item))).orElse(0L) + 1;
	}

	/**
	 * 出队
	 */
	public void remove(String key, String value) {
		HashMap<String,String> item = new HashMap();
		item.put("value", value);
		stringRedisTemplate.opsForZSet().remove(getQueueName(key), JsonUtil.toJsonString(item));
	}

	/**
	 * 清空排队数据
	 */
	public void remove(String key) {
		stringRedisTemplate.delete(getQueueName(key));
	}

}


@Getter
@Setter
@AllArgsConstructor
class QueryUser {

	private Long currentLocation;  // 当前位置
	private Long totalNum;  // 总排队人数
	private Long beforeNum;  // 排在前面的人数
	private String estimatedTime;  // 预估时间
}

