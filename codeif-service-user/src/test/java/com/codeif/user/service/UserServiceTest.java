package com.codeif.user.service;

import com.codeif.db.redis.service.RedisService;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.user.Role;
import com.codeif.pojo.user.User;
import com.querydsl.core.QueryResults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        User user = new User();
        user.setAccount("6666666666");
        user.setPassword("mima");
        user.setUserName("zhangsan");
        user.setNickName("zhangsan");
        user.setSex("1");
        user.setEmail("ddddd@qq.com");
        user.setPhone("1");
        userService.registerUser(user);
    }

    @Test
    public void findByCondition() {
        QueryVO queryVO = new QueryVO();
        QueryResults<User> dictByCondition = userService.findByCondition(new User(), queryVO);
        Assert.assertTrue(dictByCondition.getTotal() > 0);
    }

    @Test
    public void deleteByIds() {
        userService.deleteByIds(Arrays.asList("1200696171232890880"));
    }

    @Test
    public void updateByPrimaryKey() {
        User user = new User();
        user.setId("1135559244600856578");
        user.setNickName("测试");
        userService.updateByPrimaryKey(user);
    }

    @Test
    public void changePassword() {
        User user = new User();
        user.setId("1200706394064556032");
        userService.changePassword(user, "6666666666666666");
    }

    @Test
    public void getUserPermission() {
        User userPermission = userService.getUserPermission("1353259235236756534");
        Assert.assertNotNull(userPermission);
    }

    @Test
    public void getUseRoles() {
        List<Role> useRoles = userService.getUseRoles("1119477949450088449");
        Assert.assertTrue(useRoles.size() > 0);
    }

    @Test
    public void findById() {
        User userByUserId = userService.findById("1200706394064556032");
        Assert.assertNotNull(userByUserId);
    }

    @Test
    public void updateUserProfile() {
        User user = new User();
        userService.updateUserProfile(user);
    }
}