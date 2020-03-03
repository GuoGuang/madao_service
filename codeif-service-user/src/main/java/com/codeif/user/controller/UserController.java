package com.codeif.user.controller;

import com.codeif.annotation.OptLog;
import com.codeif.constant.CommonConst;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.user.Role;
import com.codeif.pojo.user.User;
import com.codeif.user.service.UserService;
import com.codeif.utils.JsonData;
import com.codeif.utils.JsonUtil;
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

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

    @PostMapping()
    @OptLog(operationType = CommonConst.ADD, operationName = "注册用户")
    @ApiOperation(value = "注册用户", notes = "User")
    public JsonData<Void> insertUser(@RequestBody @Valid User user) {
		userService.registerUser(user);
		return JsonData.success();
	}

    @GetMapping("/roles/{id}")
    @ApiOperation(value = "查询用户角色组", notes = "Dict")
    public JsonData<List<Role>> getUseRoles(@PathVariable String id) {
		List<Role> role = userService.getUseRoles(id);
		return JsonData.success(role);
	}

    @PostMapping("/permission")
    @ApiOperation(value = "获取用户角色、权限", notes = "User")
    public JsonData<User> getUserPermission(@RequestHeader("x-client-token-user") String userStr) {
        Map user = JsonUtil.jsonToPojo(userStr, Map.class);
        User result = userService.getUserPermission((String) user.get("id"));
        return JsonData.success(result);
    }

    @GetMapping("/dashboard")
    @ApiOperation(value = "dashboard信息初始化", notes = "User")
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

    @ApiOperation(value = "条件查询用户列表，url: ?search={query}{&page,per_page,sort,order}", notes = "User")
    @ApiImplicitParam(name = "User", value = "查询条件：用户对象", dataType = "Map", paramType = "query")
    @GetMapping
    public JsonData<QueryResults<User>> findByCondition(User user, QueryVO queryVO) {
        QueryResults<User> result = userService.findByCondition(user, queryVO);
        return JsonData.success(result);
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "按照id查询用户", notes = "User")
    public JsonData<User> findUserByUserId(@PathVariable String userId) {
		User result = userService.findById(userId);
		return JsonData.success(result);
	}

    @PutMapping()
    @OptLog(operationType = CommonConst.MODIFY, operationName = "更新用户资料")
    @ApiOperation(value = "更新用户资料", notes = "User")
    public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid User user) {
		userService.updateByPrimaryKey(user);
		return JsonData.success();
	}

    @DeleteMapping
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除用户")
    @ApiOperation(value = "删除用户;必须拥有管理员权限，否则不能删除，请求时需要添加头信息Authorization ,内容为Bearer-token", notes = "User")
    public JsonData<Void> deleteByIds(@RequestBody List<String> userId) {
		userService.deleteByIds(userId);
		return JsonData.success();
	}


}
