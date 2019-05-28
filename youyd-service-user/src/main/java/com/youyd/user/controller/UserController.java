package com.youyd.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.annotation.OptLog;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.enums.UserEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.User;
import com.youyd.user.service.UserService;
import com.youyd.utils.JsonData;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户+JWT鉴权
 * @author: LGG
 * @create: 2018-09-26 15:59
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
	 * 用户登陆
	 *
	 * @param account  ：账号
	 * @param password ：密码
	 * @return JsonData
	 */
	@PostMapping(value = "/login")
	@ApiOperation(value = "用户登录", notes = "User")
	public JsonData login(HttpServletRequest request, String account, String password) {
		Map uMap = userService.login(account, password,request);
		if (uMap != null) {
			return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), uMap);
		} else {
			return new JsonData(false, StatusEnum.LOGIN_ERROR.getCode(), StatusEnum.LOGIN_ERROR.getMsg());
		}
	}

	/**
	 * 注册用户
	 *
	 * @param user 用户实体
	 * @return boolean
	 */
	@PostMapping()
	public JsonData insertUser(@RequestBody @Valid User user) {
		userService.insertUser(user);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}


	/**
	 * 获取用户权限，信息
	 *
	 * @param token
	 * @return boolean
	 */
	@GetMapping("/dashboard")
	public JsonData dashboardInfo(String token) {
		Map<String, Object> info = new HashMap<>();
		info.put("order_no", "3cFA9Cce-dFDC-3Db7-17B9-eB4CAfE3ba3f");
		info.put("timestamp", "1248155062802");
		info.put("username", "唐三");
		info.put("price", "12104.5");
		info.put("status", "success");
		Map<String, Object> info1 = new HashMap<>();
		info1.put("order_no", "fFD4BA31-fd51-FbF9-Bf24-98FF201fEA8f");
		info1.put("timestamp", "923748815578");
		info1.put("username", "唐三");
		info1.put("price", "13641");
		info1.put("status", "success");
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(info);
		list.add(info1);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), list);
	}

	/**
	 * 条件查询用户列表
	 *
	 * @param user：用户条件
	 * @return boolean
	 * url: ?search={query}{&page,per_page,sort,order}
	 */
	@OptLog(operationType= CommonConst.ADD,operationName="按照条件查找用户列表")
	// TODO 只给增删改添加拦截 quartz表达式启动的时候能执行成功
	@ApiOperation(value = "查找用户列表", notes = "按照条件查找用户列表")
	@ApiImplicitParam(name = "User", value = "查询条件：用户对象", dataType = "Map", paramType = "query")
	@GetMapping
	public JsonData findByCondition(User user, QueryVO queryVO ) {
		IPage<User> byCondition = userService.findByCondition(user,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),byCondition);
	}

	/**
	 * 用户上传头像
	 * @param user：用户条件
	 * @return boolean
	 * url: ?search={query}{&page,per_page,sort,order}
	 */
	@ApiOperation(value = "查找用户列表", notes = "按照条件查找用户列表")
	@ApiImplicitParam(name = "User", value = "查询条件：用户对象", dataType = "Map", paramType = "query")
	@PutMapping("avatar")
	public JsonData updateUserAvatar(User user, MultipartFile file) throws IOException {
		userService.updateUserAvatar(user,file);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 按照id查询用户
	 *
	 * @param id：用户id
	 * @return boolean
	 * url: ?search={query}{&page,per_page,sort,order}
	 */
	@GetMapping(value = "/{id}")
	public JsonData findByCondition(@PathVariable String id) {
		User byId = userService.findUserById(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),byId);
	}
	/**
	 * 退出
	 *
	 * @param token
	 * @return boolean
	 */
	@PostMapping(value = "/logout")
	public JsonData logout(@RequestHeader("X-Token")String token) {
		userService.logout(token);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}


	/**
	 * 更新用户资料
	 * @param user 实体
	 * @return JsonData
	 */
	@PutMapping()
	public JsonData updateByPrimaryKey(@RequestBody @Valid User user) {
		boolean result = userService.updateByPrimaryKey(user);
		return new JsonData(result, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改密码
	 * @param user 实体
	 * @return JsonData
	 */
	@PutMapping("password")
	public JsonData changePassword(@RequestBody User user,String oldPassword) {
		boolean result = userService.changePassword(user,oldPassword);
		if (!result){
			return new JsonData(false, UserEnum.WRONG_PASSWORD.getCode(), UserEnum.WRONG_PASSWORD.getInfo());
		}
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
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
	public JsonData deleteByIds(@RequestBody List<String> userId, @ModelAttribute("admin_claims") Claims claims) {
		if (claims == null) {
			return new JsonData(true, StatusEnum.PARAM_MISSING.getCode(), StatusEnum.PARAM_MISSING.getMsg());
		}
		boolean result = userService.deleteByIds(userId);
		return new JsonData(result, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}


}
