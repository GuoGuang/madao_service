package com.codeway.base.controller.backstage;

import com.codeway.base.service.backstage.OptLogService;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.base.OptLog;
import com.codeway.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "操作日志")
@RestController
@RequestMapping("/optLog")
public class OptLogController {

	private final OptLogService optLogService;

	@Autowired
	public OptLogController(OptLogService optLogService) {
		this.optLogService = optLogService;
	}

    @GetMapping
    @ApiOperation(value = "按照条件查询全部列表", notes = "OptLog")
    public JsonData<QueryResults<OptLog>> findOptLogByCondition(OptLog optLog, QueryVO queryVO) {
        QueryResults<OptLog> result = optLogService.findOptLogByCondition(optLog, queryVO);
        return JsonData.success(result);

    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据ID查询操作日志", notes = "OptLog")
    public JsonData<OptLog> findOptLogByPrimaryKey(@PathVariable String id) {
        OptLog result = optLogService.findById(id);
        return JsonData.success(result);
    }

    @PostMapping()
    @ApiOperation(value = "增加操作日志", notes = "OptLog")
    public JsonData<Void> insertOptLog(@RequestBody OptLog optLog) {
        optLogService.insertOptLog(optLog);
        return JsonData.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除操作日志", notes = "OptLog")
    public JsonData<Void> deleteById(@RequestBody List<String> optLogIds) {
        optLogService.deleteBatch(optLogIds);
        return JsonData.success();
    }
}
