package com.youyd.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.base.service.TaskService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QuartzJob;
import com.youyd.pojo.QueryVO;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 *  任务管理
 * @author : LGG
 * @create : 2018-12-23
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
	 * @param quartzJob 查询参数
	 * @param queryVO 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findResByCondition(QuartzJob quartzJob, QueryVO queryVO) {
		IPage<QuartzJob> resData = taskService.findTaskByCondition(quartzJob,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 根据id查询单条
	 * @param id:job
	 * @return  JsonData
	 */
	@GetMapping(value = "/{id}")
	public JsonData findById(@PathVariable String id) {
		QuartzJob resData = taskService.findJobById(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 更新任务
	 * @param  quartzJob 任务
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateByPrimaryKey(@RequestBody QuartzJob quartzJob) {
		boolean state = taskService.updateByPrimaryKey(quartzJob);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 添加一条数据
	 * @param quartzJob 任务
	 * @return JsonData
	 */
	@PostMapping
	public JsonData insertSelective(@RequestBody @Valid QuartzJob quartzJob) {
		boolean state = taskService.insertSelective(quartzJob);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param quartzJobIds 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	public JsonData deleteByIds(@RequestBody List<String> quartzJobIds) {
		boolean state = taskService.deleteByIds(quartzJobIds);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 暂停任务
	 * @param jobId 任务Id
	 * @return JsonData
	 */
	@GetMapping("/pause/{id}")
	public JsonData pauseJob(@PathVariable("id") String jobId){
		taskService.pause(jobId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 开始任务
	 * @param jobId 任务Id
	 * @return JsonData
	 */
	@GetMapping("/run/{id}")
	public JsonData runJob(@PathVariable("id") String jobId){
		taskService.run(jobId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 重新开始任务
	 * @param jobId 任务Id
	 * @return JsonData
	 */
	@GetMapping("/resume/{id}")
	public JsonData resumeJob(@PathVariable("id") String jobId) {
		taskService.resume(jobId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

}