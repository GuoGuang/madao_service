package com.madao.user.service.blog;

import com.madao.enums.ProviderEnum;
import com.madao.exception.custom.CaptchaNotMatchException;
import com.madao.exception.custom.PhoneExistingException;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.exception.custom.UserException;
import com.madao.model.dto.user.RoleDto;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.user.User;
import com.madao.model.entity.user.UserRole;
import com.madao.user.dao.ResourceDao;
import com.madao.user.dao.RoleDao;
import com.madao.user.dao.UserDao;
import com.madao.user.dao.UserRoleDao;
import com.madao.user.mapper.ResourceMapper;
import com.madao.user.mapper.RoleMapper;
import com.madao.user.mapper.UserMapper;
import com.madao.utils.BeanUtil;
import com.madao.utils.FakerUtil;
import com.madao.utils.JsonUtil;
import com.madao.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Service
public class BlogUserService {

	private final RedisUtil redisUtil;
	private final UserDao userDao;
	private final UserRoleDao userRoleDao;
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final RoleDao roleDao;
	private final ResourceDao resourceDao;
	private final ResourceMapper resourceMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public BlogUserService(RedisUtil redisUtil, UserDao userDao,
	                       UserMapper userMapper,
	                       BCryptPasswordEncoder bCryptPasswordEncoder,
	                       RoleDao roleDao,
	                       UserRoleDao userRoleDao,
	                       RoleMapper roleMapper,
	                       ResourceDao resourceDao1,
	                       ResourceMapper resourceMapper) {
		this.redisUtil = redisUtil;
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
		String redisCaptcha = redisUtil.getKeyStr("code:sms:" + userDto.getPhone())
				.orElseThrow(CaptchaNotMatchException::new)
				.toString();
		Map<String, String> data = JsonUtil.jsonToPojo(redisCaptcha, Map.class);

		if (!StringUtils.equals(data.get("code"), userDto.getCaptcha())) {
			log.error("验证码不匹配!:redisCaptcha--->{},captcha--->:{}", redisCaptcha, userDto.getCaptcha());
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
