package com.madao.user.controller;

import com.madao.annotation.OptLog;
import com.madao.config.ratelimit.LimitType;
import com.madao.config.ratelimit.RateLimiter;
import com.madao.enums.OptLogType;
import com.madao.enums.StatusEnum;
import com.madao.model.dto.user.UserDto;
import com.madao.user.service.UserService;
import com.madao.utils.DesensitizedUtil;
import com.madao.utils.JsonData;
import com.madao.utils.OssClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Api(tags = "用户画像")
@RestController
@RequestMapping(value = "/profile", produces = "application/json")
public class ProfileController {

    private final UserService userService;
    private final OssClientUtil ossClientUtil;

    public ProfileController(UserService userService, OssClientUtil ossClientUtil) {
        this.userService = userService;
        this.ossClientUtil = ossClientUtil;
    }

    @PutMapping("/avatar")
    @ApiOperation(value = "用户上传头像", notes = "用户上传头像")
    @ApiImplicitParam(name = "User", value = "用户上传头像", dataType = "Map", paramType = "query")
    public JsonData<String> updateUserAvatar(MultipartFile file, @RequestParam String id) throws IOException {
        UserDto userById = userService.findById(id);
        String fileUrl = ossClientUtil.uploadFile(file);
        userById.setAvatar(fileUrl);
        userService.updateUserProfile(userById);
        return JsonData.success(fileUrl);
    }

    @PutMapping
    @OptLog(operationType = OptLogType.MODIFY, operationName = "更新用户资料")
    @ApiOperation(value = "更新用户资料", notes = "User")
    public JsonData<Void> updateByPrimaryKey(@RequestParam String id, @RequestParam String avatar) {
        UserDto userDto = userService.findById(id);
        userDto.setAvatar(avatar);
        userService.updateUserProfile(userDto);
        return JsonData.success();
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "查看个人界面", notes = "User")
    public JsonData<UserDto> findByCondition(@PathVariable String userId) {
        UserDto result = userService.findById(userId);
        DesensitizedUtil.mobilePhone(result.getPhone());
        DesensitizedUtil.around(result.getAccount(), 2, 2);
        return JsonData.success(result);
    }

    @PutMapping("/password/{userId}")
    @RateLimiter(time = 30,count = 1,limitType = LimitType.IP)
    @OptLog(operationType = OptLogType.MODIFY, operationName = "修改用户密码")
    @ApiOperation(value = "修改密码", notes = "User")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newOnePassword", value = "第一次密码", required = true),
            @ApiImplicitParam(name = "newTwoPassword", value = "第二次密码", required = true)
    })
    public JsonData<Void> changePassword(@PathVariable String userId, @RequestParam String oldPassword,
                                         @RequestParam String newOnePassword, @RequestParam String newTwoPassword) {
        if (!StringUtils.equals(newOnePassword, newTwoPassword)) {
            return JsonData.failed(StatusEnum.TWICE_PASSWORD_NOT_MATCH);
        }
        userService.changePassword(userId, oldPassword, newOnePassword);
        return JsonData.success();
    }

}