package com.codeway.user.service;

import com.codeway.db.redis.service.RedisService;
import com.codeway.model.dto.user.UserDto;
import com.codeway.model.pojo.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 测试mybatis-plus
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
//	@Autowired(required = false)
//	private OSSClient ossClient; // 阿里云OSS对象存储


	@Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSpringDataRedis() {
        redisService.set("stringK", "stringV", 10000); // string
    }

    @Test
    public void uploadFile() throws IOException {
        final String bucketName = "vue-admin-guoguang";
        final String fileKey = "文件名";
        final String localFile = "D:/6.png";
        final String downloadFile = "demo.jpg";
        File file = new File(localFile);
//        PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileKey, file);
//        final InputStream inputStream = ossClient.getObject(bucketName, fileKey).getObjectContent();
//        StreamUtils.copy(inputStream, new FileOutputStream(downloadFile));
//        System.out.println(ossClient.generatePresignedUrl(bucketName, fileKey, new Date(System.currentTimeMillis() + (1000 * 30)), HttpMethod.GET).toString());
    }

    @Test
    public void registerUser() {
	    UserDto user = new UserDto();
        user.setAccount("6666666666");
        user.setPassword("mima");
        user.setUserName("zhangsan");
	    user.setNickName("zhangsan");
	    user.setSex(true);
	    user.setEmail("ddddd@qq.com");
        user.setPhone("1");
        userService.registerUser(user);
    }

    @Test
    public void findByCondition() {
	    Page<UserDto> dictByCondition = userService.findByCondition(new UserDto(), null);
	    Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void deleteByIds() {
        userService.deleteByIds(Arrays.asList("1200696171232890880"));
    }

    @Test
    public void updateByPrimaryKey() {
	    UserDto user = new UserDto();
	    user.setId("1135559244600856578");
	    user.setNickName("测试");
	    userService.updateByPrimaryKey(user);
    }

    @Test
    public void changePassword() {
        User user = new User();
        user.setId("1200706394064556032");
//        userService.changePassword(user, "6666666666666666");
    }

    @Test
    public void getUserPermission() {
	    UserDto userPermission = userService.getUserPermission("1353259235236756534");
	    Assert.assertNotNull(userPermission);
    }

    @Test
    public void findById() {
	    UserDto userByUserId = userService.findById("1200706394064556032");
	    Assert.assertNotNull(userByUserId);
    }

    @Test
    public void updateUserProfile() {
	    UserDto user = new UserDto();
	    userService.updateUserProfile(user);
    }
}