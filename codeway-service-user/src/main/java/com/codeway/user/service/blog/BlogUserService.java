package com.codeway.user.service.blog;

import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.CaptchaNotMatchException;
import com.codeway.exception.custom.PhoneExistingException;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.model.dto.user.UserDto;
import com.codeway.model.pojo.user.User;
import com.codeway.model.pojo.user.UserRole;
import com.codeway.user.dao.ResourceDao;
import com.codeway.user.dao.RoleDao;
import com.codeway.user.dao.UserDao;
import com.codeway.user.dao.UserRoleDao;
import com.codeway.user.mapper.ResourceMapper;
import com.codeway.user.mapper.RoleMapper;
import com.codeway.user.mapper.UserMapper;
import com.codeway.utils.FakerUtil;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.LogBack;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/api/su/")
public class BlogUserService {

	private final RedisService redisService;
	private final UserDao userDao;
	private final UserRoleDao userRoleDao;
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final RoleDao roleDao;
	private final ResourceDao resourceDao;
	private final ResourceMapper resourceMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public BlogUserService(RedisService redisService, UserDao userDao,
	                       UserMapper userMapper,
	                       BCryptPasswordEncoder bCryptPasswordEncoder,
	                       RoleDao roleDao,
	                       UserRoleDao userRoleDao,
	                       RoleMapper roleMapper,
	                       ResourceDao resourceDao1,
	                       ResourceMapper resourceMapper) {
		this.redisService = redisService;
		this.userDao = userDao;
		this.userMapper = userMapper;
		this.roleDao = roleDao;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRoleDao = userRoleDao;
		this.roleMapper = roleMapper;
		this.resourceDao = resourceDao1;
		this.resourceMapper = resourceMapper;
	}

	public void updateByPrimaryKey(UserDto userDto) {


	}

	public UserDto findByPhone(String phone) {
		return userDao.findByPhone(phone)
				.map(userMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public UserDto registerUser(UserDto userDto) {
		User user = userDao.findByPhone(userDto.getPhone()).orElse(null);
		if (user != null) {
			throw new PhoneExistingException();
		}
		String redisCaptcha = redisService.getKeyStr("code:sms:" + userDto.getPhone())
				.orElseThrow(CaptchaNotMatchException::new)
				.toString();
		Map<String, String> data = JsonUtil.jsonToPojo(redisCaptcha, Map.class);

		if (!StringUtils.equals(data.get("code"), userDto.getCaptcha())) {
			LogBack.error("验证码不匹配!:redisCaptcha--->{},captcha--->:{}", redisCaptcha, userDto.getCaptcha());
			throw new CaptchaNotMatchException();
		}
		String nickName = FakerUtil.getNickName();
		userDto.setAccount(userDto.getPhone())
				.setUserName(nickName)
				.setNickName(nickName)
				.setEmail("")
				.setPassword(bCryptPasswordEncoder.encode(nickName))
				.setSex(true)
				.setBirthday(0L)
				.setAvatar(FakerUtil.getAvatar())
				.setOnlineTime(0L)
				.setFansCount(0)
				.setFollowCount(0)
				.setOrigin(true)
				.setStatus(false);
		UserDto userDtoResult = userMapper.toDto(userDao.save(userMapper.toEntity(userDto)));
		userRoleDao.save(new UserRole(userDtoResult.getId(), "1257559802842845184"));
		return userDtoResult;
	}

	public UserDto findById(String userId) {
		return userDao.findById(userId).map(userMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public UserDto findByCondition(UserDto userDto) {
		return null;
	}
}
