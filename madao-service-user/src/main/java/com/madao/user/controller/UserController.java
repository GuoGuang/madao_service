package com.madao.user.controller;

import com.madao.annotation.OptLog;
import com.madao.config.chain.AbstractCommonHandler;
import com.madao.enums.OptLogType;
import com.madao.model.dto.user.RoleDto;
import com.madao.model.dto.user.UserDto;
import com.madao.user.service.UserService;
import com.madao.utils.JsonData;
import com.madao.utils.security.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping(value = "/user", produces = "application/json")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	private final AbstractCommonHandler<UserDto> userSaveHandler;

	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "注册用户")
	@Operation(summary = "注册用户", description = "User")
	public JsonData<Void> insertUser(@RequestBody @Validated UserDto userDto) {
		userSaveHandler.doHandler(userDto);
		userService.registerUser(userDto);
		return JsonData.success();
	}

	@PostMapping("/permission")
	@Operation(summary = "获取用户角色、权限", description = "User")
	public JsonData<UserDto> getUserPermission() {
		UserDto result = userService.getUserPermission(SecurityUtils.getCurrentUserId());
		return JsonData.success(result);
	}

	@GetMapping("/dashboard")
	@Operation(summary = "dashboard信息初始化", description = "User")
	public JsonData<List<Map<String, Object>>> dashboardInfo(String token) {
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

	@Operation(summary = "条件查询用户列表，url: ?search={query}{&page,per_page,sort,order}", description = "User")
	@Parameter(name = "User", description = "查询条件：用户对象")
	@GetMapping
	public JsonData<Page<UserDto>> findByCondition(UserDto userDto,
	                                               @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<UserDto> result = userService.findByCondition(userDto, pageable);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{userId}")
	@Operation(summary = "按照id查询用户", description = "User")
	public JsonData<UserDto> findUserByUserId(@PathVariable String userId) {
		UserDto result = userService.findById(userId);
		return JsonData.success(result);
	}

	@GetMapping(value = "/all")
	@Operation(summary = "查询用户列表", description = "User")
	public JsonData<List<UserDto>> findUserByUserIds(@RequestParam("userIds") String[] userIds) {
		return JsonData.success(userService.findByIds(Arrays.asList(userIds)));
	}

	@GetMapping(value = "/info")
	@Operation(summary = "微服务间调用，获取用户信息", description = "User")
	public JsonData<UserDto> getUserAccount(@RequestParam("account") String account) {
		UserDto result = userService.findByCondition(account);
		return JsonData.success(result);
	}

	@GetMapping("/role")
	@Operation(summary = "查询当前用户关联的角色", description = "Role")
	public JsonData<List<UserDto>> fetchUsersList(RoleDto roleDto) {
		List<UserDto> result = userService.findUsersOfRole(roleDto);
		return JsonData.success(result);
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "更新用户资料")
	@Operation(summary = "更新用户资料", description = "User")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Validated UserDto userDto) {
		userService.updateByPrimaryKey(userDto);
		return JsonData.success();
	}

	@DeleteMapping
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除用户")
	@Operation(summary = "删除用户;必须拥有管理员权限，否则不能删除，请求时需要添加头信息Authorization ,内容为Bearer-token", description = "User")
	public JsonData<Void> deleteByIds(@RequestBody List<String> userId) {
		userService.deleteByIds(userId);
		return JsonData.success();
	}


}
