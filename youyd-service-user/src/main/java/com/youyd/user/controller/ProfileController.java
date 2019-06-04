package com.youyd.user.controller;

import com.youyd.annotation.OptLog;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.enums.UserEnum;
import com.youyd.pojo.user.User;
import com.youyd.user.service.UserService;
import com.youyd.utils.DesensitizedUtil;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 用户画像
 * @author: LGG
 * @create: 2019-05-30
 **/

@Api(tags = "用户画像")
@RestController
@RequestMapping(value = "/su/profile", produces = "application/json")
public class ProfileController {

	private final UserService userService;

	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户上传头像
	 *
	 * @param user：用户条件
	 * @return boolean
	 * url: ?search={query}{&page,per_page,sort,order}
	 */
	@ApiOperation(value = "用户上传头像", notes = "用户上传头像")
	@ApiImplicitParam(name = "User", value = "用户上传头像", dataType = "Map", paramType = "query")
	@PutMapping("avatar")
	public JsonData updateUserAvatar(User user, MultipartFile file) throws IOException {
		userService.updateUserAvatar(user, file);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 查看个人界面
	 * @param id：用户id
	 * @return boolean
	 * url: ?search={query}{&page,per_page,sort,order}
	 */
	@GetMapping(value = "/{id}")
	public JsonData findByCondition(@PathVariable String id) {
		User byId = userService.findUserById(id);
		DesensitizedUtil.mobilePhone(byId.getPhone());
		DesensitizedUtil.around(byId.getAccount(),2,2);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), byId);
	}

	/**
	 * 修改密码
	 *
	 * @param user 实体
	 * @return JsonData
	 */
	@PutMapping("password")
	@OptLog(operationType = CommonConst.MODIFY, operationName = "修改用户密码")
	public JsonData changePassword(@RequestBody User user, String oldPassword) {
		if (oldPassword ==null || !oldPassword.equals(user.getPassword())){
			return new JsonData(false, UserEnum.TWICE_PASSWORD_NOT_MATCH.getCode(), UserEnum.TWICE_PASSWORD_NOT_MATCH.getInfo());
		}
		boolean result = userService.changePassword(user, oldPassword);
		if (!result) {
			return new JsonData(false, UserEnum.WRONG_PASSWORD.getCode(), UserEnum.WRONG_PASSWORD.getInfo());
		}
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

}