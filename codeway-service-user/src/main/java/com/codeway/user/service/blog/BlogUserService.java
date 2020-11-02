package com.codeway.user.service.blog;

import com.codeway.db.redis.service.RedisService;
import com.codeway.enums.ProviderEnum;
import com.codeway.exception.custom.CaptchaNotMatchException;
import com.codeway.exception.custom.PhoneExistingException;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.exception.custom.UserException;
import com.codeway.model.dto.user.RoleDto;
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
import com.codeway.utils.BeanUtil;
import com.codeway.utils.FakerUtil;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.LogBack;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

	public void updateByPrimaryKey(UserDto userDto, String userId) {
		UserDto srcUserDto = this.findById(userId);
		BeanUtil.copyProperties(srcUserDto, userDto);
		userDao.save(userMapper.toEntity(userDto));
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
		return createDefaultUser(nickName, userDto.getPhone(), "",
				userDto.getPhone(), FakerUtil.getAvatar(), null, "", "",
				bCryptPasswordEncoder.encode(userDto.getPassword()));
	}

	/**
	 * 存在则返回登录信息；否则注册并返回登录信息；
	 *
	 * @param params access_token换取的用户信息；
	 * @return UserDto
	 */
	public UserDto loginWithGithub(HashMap<String, Object> params) {
		UserDto userInfo = userDao.findByBindId(params.get("node_id") + "")
				.map(userMapper::toDto)
				.orElseGet(() -> createDefaultUser(
						params.get("name").toString(),
						params.get("login").toString(),
						params.get("email").toString(),
						"",
						params.get("avatar_url").toString(),
						ProviderEnum.GITHUB,
						params.get("node_id").toString(),
						params.get("location").toString(),
						bCryptPasswordEncoder.encode(params.get("name").toString() + params.get("node_id").toString()))
				);

		List<RoleDto> rolesOfUser = roleDao.findRolesOfUser(userInfo.getId())
				.map(roleMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
		userInfo.setRoles(new HashSet<>(rolesOfUser));

		return userInfo;
	}

	private UserDto createDefaultUser(String nickName, String account,
	                                  String email, String phone,
	                                  String avatar, ProviderEnum provider,
	                                  String bindId, String contactAddress,
	                                  String password) {
		UserDto userDto = new UserDto();
		userDto.setAccount(account)
				.setUserName(nickName)
				.setPhone(phone)
				.setNickName(nickName)
				.setEmail(email)
				.setPassword(password)
				.setSex(true)
				.setBirthday(0L)
				.setAvatar(avatar)
				.setOnlineTime(0L)
				.setFansCount(0)
				.setFollowCount(0)
				.setOrigin(true)
				.setBindId(bindId)
				.setProvider(provider)
				.setContactAddress(contactAddress)
				.setStatus(false);
		UserDto userDtoResult = userMapper.toDto(userDao.save(userMapper.toEntity(userDto)));
		userRoleDao.save(new UserRole(userDtoResult.getId(), "1257559802842845184"));
		return userDtoResult;
	}

	public UserDto findById(String userId) {
		return userMapper.toDto(userDao.findByIdAndRequireNonNull(userId));
	}

	public UserDto findByAccount(String account) {
		return userDao.findByAccount(account)
				.map(userMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public void changePassword(String userId, String oldPassword, String newOnePass) {
		User userInfo = userDao.findByIdAndRequireNonNull(userId);
		if (!bCryptPasswordEncoder.matches(oldPassword, userInfo.getPassword())) {
			throw new UserException("密码不匹配！");
		}
		userInfo.setPassword(bCryptPasswordEncoder.encode(newOnePass));
		userDao.save(userInfo);
	}

}
