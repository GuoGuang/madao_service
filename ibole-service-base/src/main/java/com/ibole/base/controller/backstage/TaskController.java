package com.ibole.base.controller.backstage;

import com.ibole.annotation.OptLog;
import com.ibole.base.service.backstage.TaskService;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QuartzJob;
import com.ibole.pojo.QueryVO;
import com.ibole.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 *  任务管理
 **/

@Api(tags = "任务")
@RestController
@RequestMapping(value = "/task")
public class TaskController {


	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}


    /**
     * 条件查询任务
     *
     * @param quartzJob 查询参数
     * @param queryVO   查询参数
     * @return JsonData
     */
    @GetMapping
    public JsonData findResByCondition(QuartzJob quartzJob, QueryVO queryVO) {
        QueryResults<QuartzJob> resData = taskService.findTaskByCondition(quartzJob, queryVO);
        return JsonData.success(resData);
    }

    /**
     * 根据id查询单条
     *
     * @param id:job
     * @return JsonData
     */
    @GetMapping(value = "/{id}")
    public JsonData findById(@PathVariable String id) {
        QuartzJob resData = taskService.findJobById(id);
        return JsonData.success(resData);
    }

	/**
	 * 更新任务
	 * @param  quartzJob 任务
	 * @return JsonData
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="更新Quartz任务")
	public JsonData updateByPrimaryKey(@RequestBody @Valid QuartzJob quartzJob) {
		taskService.updateByPrimaryKey(quartzJob);
		return JsonData.success();
	}

	/**
	 * 添加一条数据
	 * @param quartzJob 任务
	 * @return JsonData
	 */
	@PostMapping
	@OptLog(operationType= CommonConst.ADD,operationName="插入Quartz任务")
	public JsonData insertSelective(@RequestBody @Valid QuartzJob quartzJob) {
		taskService.insertSelective(quartzJob);
		return JsonData.success();
	}

	/**
	 * 删除Quartz任务
	 * @param quartzJobIds 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	@OptLog(operationType= CommonConst.DELETE,operationName="删除Quartz任务")
	public JsonData deleteByIds(@RequestBody List<String> quartzJobIds) {
		taskService.deleteByIds(quartzJobIds);
		return JsonData.success();
	}

	/**
	 * 暂停任务
	 * @param jobId 任务Id
	 * @return JsonData
	 */
	@GetMapping("/pause/{id}")
	public JsonData pauseJob(@PathVariable("id") String jobId) {
		taskService.pause(jobId);
		return JsonData.success();
	}

	/**
	 * 开始任务
	 * @param jobId 任务Id
	 * @return JsonData
	 */
	@GetMapping("/run/{id}")
	@OptLog(operationType= CommonConst.MODIFY,operationName="开始执行Quartz任务")
	public JsonData runJob(@PathVariable("id") String jobId) {
		taskService.run(jobId);
		return JsonData.success();
	}

	/**
	 * 重新开始任务
	 * @param jobId 任务Id
	 * @return JsonData
	 */
	@GetMapping("/resume/{id}")
	public JsonData resumeJob(@PathVariable("id") String jobId) {
		taskService.resume(jobId);
		return JsonData.success();
	}

}