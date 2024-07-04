package com.madao.user.service;

import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.exception.custom.UserException;
import com.madao.model.dto.user.ResourceDto;
import com.madao.model.dto.user.RoleDto;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.user.User;
import com.madao.model.entity.user.UserRole;
import com.madao.user.mapper.ResourceMapper;
import com.madao.user.mapper.RoleMapper;
import com.madao.user.mapper.UserMapper;
import com.madao.user.repository.ResourceRepository;
import com.madao.user.repository.RoleRepository;
import com.madao.user.repository.UserRepository;
import com.madao.user.repository.UserRoleRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final RoleRepository roleRepository;
	private final ResourceRepository resourceRepository;
	private final ResourceMapper resourceMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 注册用户
	 *
	 * @param userDto
	 */
	public void registerUser(UserDto userDto) {
		//加密后的密码
		String bCryptPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
		userDto.setPassword(bCryptPassword);
		userRepository.save(userMapper.toEntity(userDto));
	}


	public Page<UserDto> findByCondition(UserDto userDto, Pageable pageable) {

		Specification<User> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(userDto.getUserName())) {
				predicates.add(builder.like(root.get("userName"), "%" + userDto.getUserName() + "%"));
			}
			if (userDto.getStatus() != null) {
				predicates.add(builder.equal(root.get("status"), userDto.getStatus()));
			}
			if (StringUtils.isNotEmpty(userDto.getId())) {
				predicates.add(builder.equal(root.get("id"), userDto.getId()));
			}
			if (StringUtils.isNotEmpty(userDto.getAccount())) {
				predicates.add(builder.like(root.get("account"), "%" + userDto.getAccount() + "%"));
			}
			if (StringUtils.isNotEmpty(userDto.getPhone())) {
				predicates.add(builder.like(root.get("phone"), "%" + userDto.getPhone() + "%"));
			}
			Predicate[] ps = new Predicate[predicates.size()];
			return query.where(builder.and(predicates.toArray(ps))).getRestriction();
		};
		Page<UserDto> result = userRepository.findAll(condition, pageable).map(userMapper::toDto);
		return result;

	}

	public void deleteByIds(List<String> userIds) {
		userRepository.deleteBatch(userIds);
		userRoleRepository.deleteByUserIdIn(userIds);
	}

	/**
	 * 更新用户基础信息，关联的角色
	 *
	 * @param userDto 用户实体
	 */
	public void updateByPrimaryKey(UserDto userDto) {
		List<UserRole> userRoles = userDto.getRoles().stream()
				.map(user -> new UserRole(userDto.getId(), user.getId()))
				.toList();

		userRepository.save(userMapper.toEntity(userDto));

		userRoleRepository.deleteByUserIdIn(Collections.singletonList(userDto.getId()));

		userRoleRepository.saveAll(userRoles);
	}


	/**
	 * 修改密码
	 *
	 * @param oldPassword 老密码
	 */
	public void changePassword(String userId, String oldPassword, String newOnePass) {
		User userInfo = userRepository.findByIdAndRequireNonNull(userId);
		if (!bCryptPasswordEncoder.matches(oldPassword, userInfo.getPassword())) {
			throw new UserException("密码不匹配！");
		}
		userInfo.setPassword(bCryptPasswordEncoder.encode(newOnePass));
		userRepository.save(userInfo);
	}

	/**
	 * 获取用户角色权限
	 *
	 * @param userId 用户id
	 */
	public UserDto getUserPermission(String userId) {
		User user = userRepository.findByIdAndRequireNonNull(userId);
		UserDto userDto = userMapper.toDto(user);
		List<RoleDto> roles = roleRepository.findRolesOfUser(userId).map(roleMapper::toDto).orElse(Collections.emptyList());
		roles.forEach(item -> {
					Set<ResourceDto> resources = Optional.ofNullable(
									resourceRepository.findResourceByRoleIds(Collections.singletonList(item.getId())))
							.map(resourceMapper::toDto)
							.orElse(Collections.emptySet());
					item.setResources(resources);
				}
		);
		userDto.setRoles(new HashSet<>(roles));

		return userDto;
	}

	/**
	 * 查询当前角色的用户列表
	 *
	 * @param roleDto 角色
	 * @return List<User>
	 */
	public List<UserDto> findUsersOfRole(RoleDto roleDto) {
		return userRepository.findUsersOfRole(roleDto.getId())
				.map(userMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public UserDto findById(String userId) {
		User user = userRepository.findByIdAndRequireNonNull(userId);
		return assembleUserRoleData(userMapper.toDto(user));
	}

	public List<UserDto> findByIds(List<String> userId) {
		List<User> allById = userRepository.findAllById(userId);
		return userMapper.toDto(allById);
	}

	public UserDto findByCondition(String account) {

		Specification<User> condition = (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			Predicate accountParam = builder.equal(root.get("account"), account);
			Predicate phone = builder.equal(root.get("phone"), account);
			predicates.add(builder.or(accountParam, phone));
			Predicate[] ps = new Predicate[predicates.size()];
			return query.where(builder.and(predicates.toArray(ps))).getRestriction();
		};

		UserDto userInfo = userRepository.findOne(condition)
				.map(userMapper::toDto)
				.orElse(null);

		return assembleUserRoleData(userInfo);
	}

	public void updateUserProfile(UserDto userDto) {
		userRepository.save(userMapper.toEntity(userDto));
	}


	/**
	 * 组装用户角色数据
	 *
	 * @param userDto userDto
	 * @return UserDto
	 */
	private UserDto assembleUserRoleData(UserDto userDto) {
		if (userDto == null) {
			return null;
		}
		List<RoleDto> rolesOfUser = roleRepository.findRolesOfUser(userDto.getId())
				.map(roleMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
		userDto.setRoles(new HashSet<>(rolesOfUser));
		return userDto;
	}

}
