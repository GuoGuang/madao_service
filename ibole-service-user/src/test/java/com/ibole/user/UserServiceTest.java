package com.ibole.user;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.ibole.db.redis.service.RedisService;
import com.ibole.pojo.user.User;
import com.ibole.db.redis.service.RedisService;
import com.ibole.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 测试mybatis-plus
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired(required = false)
	private OSSClient ossClient; // 阿里云OSS对象存储

	@Test
	public void testPlus(){
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setId("1067076025403432962");
		user.setUserName("ZH001");
		user.setPassword("123456");
		user.setAvatar("头像是qq");
		user.setBirthday(1L);
		user.setEmail("121@qq.com");
		user.setContactAddress("山东省潍坊市昌邑市");
		//user.setIsLock(0);
		user.setPhone("15866969696");
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

	@Autowired
	private RedisTemplate redisTemplate;
	@Test
	public void testSpringDataRedis(){
		redisService.set("stringK", "stringV", 10000); // string
		// redisService.lSet("listK",Arrays.asList("王","赵"));
//		Object user_token1353259235236756534 = redisService.get("USER_TOKEN1353259235236756534");
//		Object userToken = redisTemplate.opsForValue().get("USER_TOKEN1353259235236756534");
//		System.out.println(userToken);
	}

	@Test
	public void uploadFile() throws IOException {
		final String bucketName = "vue-admin-guoguang";
		final String fileKey = "文件名";
		final String localFile = "D:/6.png";
		final String downloadFile = "demo.jpg";
		File file = new File(localFile);
		PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileKey, file);
		final InputStream inputStream = ossClient.getObject(bucketName, fileKey).getObjectContent();
		StreamUtils.copy(inputStream, new FileOutputStream(downloadFile));
		System.out.println(ossClient.generatePresignedUrl(bucketName, fileKey, new Date(System.currentTimeMillis() + (1000 * 30)), HttpMethod.GET).toString());


	}
}