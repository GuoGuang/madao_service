package com.youyd.user;

import com.youyd.cache.redis.RedisService;
import com.youyd.cache.redis.RedisServiceImpl;
import com.youyd.user.pojo.User;
import com.youyd.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * 测试mybatis-plus
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void testPlus(){
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setId(1067076025403432962L);
		user.setUserName("ZH001");
		user.setPassword("123456");
		user.setAvatar("头像是qq");
		user.setBirthday(new Date());
		user.setEmail("121@qq.com");
		user.setContactAddress("山东省潍坊市昌邑市");
		//user.setIsLock(0);
		user.setMobile("15866969696");
		user.setOnlineTime(150L);
		//user.setRegisteredDate(new Date());
		user.setRegisteredType("");
		user.setSex("男");
		list.add(user);

		// boolean result = userService.insertSelective(user); // 新增值不为null的字段
		// boolean b = userService.insertOrUpdate(user);// 新增或更新值不为null的字段
		// boolean b = userService.insertBatch(list);  // 批量插入
		// boolean b = userService.insertOrUpdateBatch(list);// 批量插入或者批量更新

		//boolean b = userService.updateBatchByPrimaryKey(list);// 根据id批量修改更新值不为null的字段
		// boolean b = userService.updateByPrimaryKey(user); // 根据id修改更新值不为null的字段
		/*
		传入UpdateWrapper更新条件,填充where后面的条件
		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.eq("user_id","1");
		wrapper.eq("user_name","1");
		userService.updateByCondition(user,wrapper);
		*/

		// boolean b = userService.deleteByPrimaryKey(user); // 根据id删除
		/*  根据id批量删除
		List<Serializable> delIds = new ArrayList();
		delIds.add("1067644187756732417");
		delIds.add("1067644188549455874");
		boolean b = userService.deleteByIds(delIds);*/

		// User byId = userService.findById("1067644187756732417"); 按照id查询

		/* 按照条件查询
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.like("user_name","1");
		List<User> byCondition = userService.findByCondition(queryWrapper);
		*/

		// 按照id数组查询多个
		// Long[] findIds =new Long[]{1067644187756732417L,1067644188549455874L};
		// Collection<User> byIds = userService.findByIds(Arrays.asList(findIds));

		Map<String, Object> map = new HashMap();
		map.put("user_name","1");
		//Collection<User> byMap = userService.findByMap(map); // 按照条件查询
		//Collection<User> byLoginnameAndPassword = userService.findByMap(map);// 按照条件查询
		//System.out.println(byLoginnameAndPassword);
	}

	@Autowired
	private RedisService redisService;

	@Test
	public void testSpringDataRedis(){
		//redisService.set("stringK","stringV"); // string
		redisService.lSet("listK",Arrays.asList("王","赵"));
	}
}