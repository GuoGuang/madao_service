package com.ibole.base.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibole.base.service.backstage.OptLogService;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.OptLog;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志
 **/

@Api(tags = "操作日志")
@RestController
@RequestMapping("/optLog")
public class OptLogController {

	private final OptLogService optLogService;

	@Autowired
	public OptLogController(OptLogService optLogService) {
		this.optLogService = optLogService;
	}

	/**
	 * 按照条件查询全部列表
	 * @return Result
	 */
	@GetMapping
	public JsonData findOptLogByCondition(OptLog optLog, QueryVO queryVO){
		IPage<OptLog> result = optLogService.findOptLogByCondition(optLog,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), result);

	}

	/**
	 * 根据ID查询操作日志
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public JsonData findOptLogByPrimaryKey(@PathVariable String id){
		OptLog optLog = optLogService.findOptLogByPrimaryKey(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),optLog);
	}

	/**
	 * 增加操作日志
	 * @param optLog 操作日志实体
	 * @return JsonData
	 */
	@PostMapping()
	public JsonData insertOptLog(@RequestBody OptLog optLog){
		optLogService.insertOptLog(optLog);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}


	/**
	 * 删除操作日志
	 * @param optLogIds 要删除的id数组
	 * @return JsonData
	 */
	@DeleteMapping
	public JsonData deleteById(@RequestBody List<String> optLogIds){
		optLogService.deleteById(optLogIds);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}
