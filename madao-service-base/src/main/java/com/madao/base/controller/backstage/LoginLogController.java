package com.madao.base.controller.backstage;

import com.madao.base.service.backstage.LoginLogService;
import com.madao.model.dto.base.LoginLogDto;
import com.madao.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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
    public JsonData<Page<LoginLogDto>> findLoginLogByCondition(LoginLogDto loginLogDto,
                                                               @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        Page<LoginLogDto> result = loginLogService.findLoginLogByCondition(loginLogDto, pageable);
        return JsonData.success(result);

    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据ID查询登录日志", notes = "LoginLog")
    public JsonData<LoginLogDto> findById(@PathVariable String id) {
        LoginLogDto result = loginLogService.findById(id);
        return JsonData.success(result);
    }

    @PostMapping()
    @ApiOperation(value = "增加登录日志", notes = "LoginLog")
    public JsonData<Void> insertLoginLog(@RequestBody LoginLogDto loginLogDto) {
        loginLogService.save(loginLogDto);
        return JsonData.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除登录日志", notes = "LoginLog")
    public JsonData<Void> deleteById(@RequestBody List<String> loginLogIds) {
        loginLogService.deleteBatch(loginLogIds);
        return JsonData.success();
    }
}
