package com.codeway.base.controller.backstage;

import com.codeway.base.service.backstage.LoginLogService;
import com.codeway.model.pojo.base.LoginLog;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "登录日志")
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

	private final LoginLogService loginLogService;

	public LoginLogController(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

    @GetMapping
    @ApiOperation(value = "按照条件查询全部列表", notes = "LoginLog")
    public JsonData<Page<LoginLog>> findLoginLogByCondition(LoginLog loginLog,
                                                                    @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        Page<LoginLog> result = loginLogService.findLoginLogByCondition(loginLog, pageable);
        return JsonData.success(result);

    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据ID查询登录日志", notes = "LoginLog")
    public JsonData<LoginLog> findById(@PathVariable String id) {
        LoginLog result = loginLogService.findById(id);
        return JsonData.success(result);
    }

    @PostMapping()
    @ApiOperation(value = "增加登录日志", notes = "LoginLog")
    public JsonData<Void> insertLoginLog(@RequestBody LoginLog loginLog) {
        loginLogService.save(loginLog);
        return JsonData.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除登录日志", notes = "LoginLog")
    public JsonData<Void> deleteById(@RequestBody List<String> loginLogIds) {
        loginLogService.deleteBatch(loginLogIds);
        return JsonData.success();
    }
}
