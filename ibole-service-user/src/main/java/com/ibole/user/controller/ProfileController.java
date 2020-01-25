package com.ibole.user.controller;

import com.ibole.annotation.OptLog;
import com.ibole.constant.CommonConst;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.user.User;
import com.ibole.user.service.UserService;
import com.ibole.utils.DesensitizedUtil;
import com.ibole.utils.JsonData;
import com.ibole.utils.OssClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "用户画像")
@RestController
@RequestMapping(value = "/profile", produces = "application/json")
public class ProfileController {

    private final UserService userService;
    // 对象存储工具OØ
    private final OssClientUtil ossClientUtil;

    @Autowired
    public ProfileController(UserService userService, OssClientUtil ossClientUtil) {
        this.userService = userService;
        this.ossClientUtil = ossClientUtil;
    }

    @PutMapping("avatar")
    @ApiOperation(value = "用户上传头像", notes = "用户上传头像")
    @ApiImplicitParam(name = "User", value = "用户上传头像", dataType = "Map", paramType = "query")
    public JsonData<Void> updateUserAvatar(MultipartFile file, User user) {
    	// 暂无可用图床，自行对接图床
		// String fileUrl = ossClientUtil.uploadFile(file);
        user.setAvatar("https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/a.png");
        userService.updateUserProfile(user);
        return JsonData.success();
    }

    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "更新用户资料")
    @ApiOperation(value = "更新用户资料", notes = "User")
    public JsonData<Void> updateByPrimaryKey(@RequestBody User user) {
        userService.updateUserProfile(user);
        return JsonData.success();
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "查看个人界面", notes = "User")
    public JsonData<User> findByCondition(@PathVariable String userId) {
        User result = userService.findById(userId);
        DesensitizedUtil.mobilePhone(result.getPhone());
        DesensitizedUtil.around(result.getAccount(), 2, 2);
        return JsonData.success(result);
    }

    @PutMapping("password")
    @OptLog(operationType = CommonConst.MODIFY, operationName = "修改用户密码")
    @ApiOperation(value = "修改密码", notes = "User")
    public JsonData<Void> changePassword(@RequestBody User user, String oldPassword) {
        if (oldPassword == null || !oldPassword.equals(user.getPassword())) {
            return JsonData.failed(StatusEnum.TWICE_PASSWORD_NOT_MATCH);
        }
        userService.changePassword(user, oldPassword);
        return JsonData.success();
    }

}