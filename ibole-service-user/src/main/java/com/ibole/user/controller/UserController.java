package com.ibole.user.controller;

import com.ibole.annotation.OptLog;
import com.ibole.constant.CommonConst;
import com.ibole.exception.custom.ParamException;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.Role;
import com.ibole.pojo.user.User;
import com.ibole.user.service.UserService;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户+JWT鉴权
 **/

@Api(tags = "用户")
@RestController
@RequestMapping(value = "/su/user", produces = "application/json")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 注册用户
	 *
	 * @param user 用户实体
	 * @return boolean
	 */
	@PostMapping()
	@OptLog(operationType = CommonConst.ADD, operationName = "注册用户")
	public JsonData<Void> insertUser(@RequestBody @Valid User user) {
		userService.registerUser(user);
		return JsonData.success();
	}


	/**
	 * 查询用户角色组
	 *
	 * @param id 用户id
	 * @return boolean
	 */
	@GetMapping("/roles/{id}")
	public JsonData<List<Role>> getUseRoles(@PathVariable String id) {
		List<Role> role = userService.getUseRoles(id);
		return JsonData.success(role);
	}

	/**
	 * 获取用户角色、权限
	 *
	 * @param token 令牌
	 * @return boolean
	 */
	@PostMapping("/permission")
	public JsonData<User> getUserPermission(@RequestHeader("x-client-token-user") String userStr) throws ParamException {
		Map user = JsonUtil.jsonToPojo(userStr, Map.class);
		User result = userService.getUserPermission((String) user.get("id"));
		return JsonData.success(result);
	}


	/**
	 * dashboard信息初始化
	 * @param token
	 * @return boolean
	 */
	@GetMapping("/dashboard")
	public JsonData dashboardInfo(String token) {
		Map<String, Object> result = new HashMap<>();
		result.put("order_no", "3cFA9Cce-dFDC-3Db7-17B9-eB4CAfE3ba3f");
		result.put("timestamp", "1248155062802");
		result.put("username", "唐三");
		result.put("price", "12104.5");
		result.put("status", "success");
		Map<String, Object> info1 = new HashMap<>();
		info1.put("order_no", "fFD4BA31-fd51-FbF9-Bf24-98FF201fEA8f");
		info1.put("timestamp", "923748815578");
		info1.put("username", "唐三");
		info1.put("price", "13641");
		info1.put("status", "success");
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(result);
		list.add(info1);
		return JsonData.success(list);
	}

	/**
	 * 条件查询用户列表
	 *
	 * @param user：用户条件
	 * @return boolean
	 * url: ?search={query}{&page,per_page,sort,order}
	 */
	@ApiOperation(value = "查找用户列表", notes = "按照条件查找用户列表")
	@ApiImplicitParam(name = "User", value = "查询条件：用户对象", dataType = "Map", paramType = "query")
	@GetMapping
	public JsonData<QueryResults<User>> findByCondition(User user, QueryVO queryVO) {
		QueryResults<User> result = userService.findByCondition(user, queryVO);
		return JsonData.success(result);
	}


	/**
	 * 按照id查询用户
	 *
	 * @param userId：用户id
	 * @return boolean
	 */
	@GetMapping(value = "/{userId}")
	public JsonData<User> findUserByUserId(@PathVariable String userId) {
		User result = userService.findById(userId);
		return JsonData.success(result);
	}

	/**
	 * 更新用户资料
	 *
	 * @param user 实体
	 * @return JsonData
	 */
	@PutMapping()
	@OptLog(operationType = CommonConst.MODIFY, operationName = "更新用户资料")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid User user) {
		userService.updateByPrimaryKey(user);
		return JsonData.success();
	}

	/**
	 * 删除用户;
	 * 必须拥有管理员权限，否则不能删除
	 * 请求时需要添加头信息Authorization ,内容为Bearer-token
	 *
	 * @param userId:要删除的用户id
	 * @param claims:jwt鉴权的数据
	 * @return JsonData
	 */
	@DeleteMapping
	@OptLog(operationType = CommonConst.DELETE, operationName = "删除用户")
	public JsonData<Void> deleteByIds(@RequestBody List<String> userId) {
		userService.deleteByIds(userId);
		return JsonData.success();
	}


}
