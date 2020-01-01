package com.ibole.base.controller.backstage;

import com.ibole.annotation.OptLog;
import com.ibole.base.service.backstage.TaskService;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QuartzJob;
import com.ibole.pojo.QueryVO;
import com.ibole.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "任务管理")
@RestController
@RequestMapping(value = "/task")
public class TaskController {


	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

    @GetMapping
    @ApiOperation(value = "条件查询任务", notes = "Task")
    public JsonData<QueryResults<QuartzJob>> findResByCondition(QuartzJob quartzJob, QueryVO queryVO) {
        QueryResults<QuartzJob> resData = taskService.findTaskByCondition(quartzJob, queryVO);
        return JsonData.success(resData);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id查询单条", notes = "Task")
    public JsonData<QuartzJob> findById(@PathVariable String id) {
        QuartzJob resData = taskService.findJobById(id);
        return JsonData.success(resData);
    }

    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "更新Quartz任务")
    @ApiOperation(value = "更新Quartz任务", notes = "Task")
    public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid QuartzJob quartzJob) {
        taskService.updateByPrimaryKey(quartzJob);
        return JsonData.success();
    }

    @PostMapping
    @OptLog(operationType = CommonConst.ADD, operationName = "插入Quartz任务")
    @ApiOperation(value = "添加一条数据", notes = "Task")
    public JsonData<Void> insertSelective(@RequestBody @Valid QuartzJob quartzJob) {
        taskService.insertSelective(quartzJob);
        return JsonData.success();
    }

    @DeleteMapping()
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除Quartz任务")
    @ApiOperation(value = "删除Quartz任务", notes = "Task")
    public JsonData<Void> deleteByIds(@RequestBody List<String> quartzJobIds) {
        taskService.deleteByIds(quartzJobIds);
        return JsonData.success();
    }

    @GetMapping("/pause/{id}")
    @ApiOperation(value = "暂停任务", notes = "Task")
    public JsonData<Void> pauseJob(@PathVariable("id") String jobId) {
        taskService.pause(jobId);
        return JsonData.success();
    }

    @GetMapping("/run/{id}")
    @OptLog(operationType = CommonConst.MODIFY, operationName = "开始执行Quartz任务")
    @ApiOperation(value = "开始任务", notes = "Task")
    public JsonData<Void> runJob(@PathVariable("id") String jobId) {
        taskService.run(jobId);
        return JsonData.success();
    }

    @GetMapping("/resume/{id}")
    @ApiOperation(value = "重新开始任务", notes = "Task")
    public JsonData<Void> resumeJob(@PathVariable("id") String jobId) {
        taskService.resume(jobId);
        return JsonData.success();
    }

}