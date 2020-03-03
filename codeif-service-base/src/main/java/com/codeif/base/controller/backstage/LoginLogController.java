package com.codeif.base.controller.backstage;

import com.codeif.base.service.backstage.LoginLogService;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.base.LoginLog;
import com.codeif.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "登录日志")
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

	private final LoginLogService loginLogService;

	@Autowired
	public LoginLogController(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

    @GetMapping
    @ApiOperation(value = "按照条件查询全部列表", notes = "LoginLog")
    public JsonData<QueryResults<LoginLog>> findLoginLogByCondition(LoginLog loginLog, QueryVO queryVO) {
        QueryResults<LoginLog> result = loginLogService.findLoginLogByCondition(loginLog, queryVO);
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
