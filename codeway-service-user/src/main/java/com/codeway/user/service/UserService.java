package com.codeway.user.service;

import com.codeway.api.base.LoginLogServiceRpc;
import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.ResourceNotFoundException;
import com.codeway.exception.custom.UserException;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.user.QUser;
import com.codeway.pojo.user.Role;
import com.codeway.pojo.user.User;
import com.codeway.user.dao.ResourceDao;
import com.codeway.user.dao.RoleDao;
import com.codeway.user.dao.UserDao;
import com.codeway.utils.QuerydslUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户服务
 **/
@Service
public class UserService {

	private final UserDao userDao;
	private final RoleDao roleDao;
    private final ResourceDao resourceDao;

    private final RedisService redisService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final LoginLogServiceRpc loginLogServiceRpc;

	private final JPAQueryFactory jpaQueryFactory;

    public UserService(UserDao userDao, RedisService redisService,
					   BCryptPasswordEncoder bCryptPasswordEncoder,
					   LoginLogServiceRpc loginLogServiceRpc,
					   RoleDao roleDao, ResourceDao resourceDao, JPAQueryFactory jpaQueryFactory) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.resourceDao = resourceDao;
        this.redisService = redisService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.loginLogServiceRpc = loginLogServiceRpc;
		this.jpaQueryFactory = jpaQueryFactory;
	}

	/**
	 * 注册用户
	 *
	 * @param user
	 */
    public void registerUser(User user) {
        //加密后的密码
        String bCryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(bCryptPassword);
        userDao.save(user);
    }


    public QueryResults<User> findByCondition(User user, QueryVO queryVO) {

        QUser qUser = QUser.user;
        com.querydsl.core.types.Predicate predicate = null;
        OrderSpecifier<?> sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qUser);
        if (StringUtils.isNotEmpty(user.getUserName())) {
            predicate = ExpressionUtils.and(predicate, qUser.userName.like(user.getUserName()));
        }
        if (user.getStatus() != null) {
            predicate = ExpressionUtils.and(predicate, qUser.status.eq(user.getStatus()));
        }
        if (StringUtils.isNotEmpty(user.getId())) {
            predicate = ExpressionUtils.and(predicate, qUser.status.eq(user.getStatus()));
        }
        if (StringUtils.isNotEmpty(user.getUserName())) {
            predicate = ExpressionUtils.and(predicate, qUser.status.eq(user.getStatus()));
        }
        if (StringUtils.isNotEmpty(user.getAccount())) {
            predicate = ExpressionUtils.and(predicate, qUser.account.eq(user.getAccount()));
        }
        if (StringUtils.isNotEmpty(user.getPhone())) {
            predicate = ExpressionUtils.and(predicate, qUser.phone.eq(user.getPhone()));
        }
        if (StringUtils.isNotEmpty(queryVO.getFieldSort())) {
            sortedColumn = QuerydslUtil.getSortedColumn(Order.DESC, qUser, queryVO.getFieldSort());
        }
        QueryResults<User> queryResults = jpaQueryFactory
                .selectFrom(qUser)
                .where(predicate)
                .offset(queryVO.getPageNum())
                .limit(queryVO.getPageSize())
                .orderBy(sortedColumn)
                .fetchResults();
        return queryResults;
    }

    public void deleteByIds(List<String> userId) {
        userDao.deleteBatch(userId);
    }

    /**
	 * 更新用户基础信息，关联的角色
	 * @param user 用户实体
	 */
	public void updateByPrimaryKey(User user) {
		List<String> ids = user.getRoles().stream()
				.map(Role::getId)
				.collect(Collectors.toList());
		List<Role> allById = roleDao.findAllById(ids);
		Set<Role> rolesSet = new HashSet<>(allById);
		user.setRoles(rolesSet);
		userDao.save(user);
	}


	/**
	 * 修改密码
	 * @param oldPassword 老密码
	 */
	public void changePassword(String userId, String oldPassword, String newOnePass) {
		User userInfo = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("用户不存在！"));
		if (!bCryptPasswordEncoder.matches(oldPassword, userInfo.getPassword())) {
			throw new UserException("密码不匹配！");
		}
		userInfo.setPassword(bCryptPasswordEncoder.encode(newOnePass));
		userDao.save(userInfo);
	}

	/**
	 * 获取用户角色权限
	 * @param id 用户id
	 */
	public User getUserPermission(String id) {
		User user = userDao.findById(id).orElseThrow(ResourceNotFoundException::new);
		return user;
	}

	public List<Role> getUseRoles(String id) {
		List<Role> rolesOfUser = roleDao.findRolesOfUser(id);
		return rolesOfUser;
	}

	public User findById(String userId) {
		User user = userDao.findById(userId).orElseThrow(ResourceNotFoundException::new);
		List<Role> rolesOfUser = roleDao.findRolesOfUser(user.getId());
		Set<Role> rolesSet = new HashSet<>(rolesOfUser);
		user.setRoles(rolesSet);
		return user;
	}

	public User findByAccount(String account) {
		User user = userDao.findByAccount(account).orElseThrow(ResourceNotFoundException::new);
		return user;
	}

	public void updateUserProfile(User user) {
		userDao.save(user);
	}
}
