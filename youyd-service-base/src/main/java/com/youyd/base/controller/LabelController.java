package com.youyd.base.controller;

import com.youyd.base.pojo.Label;
import com.youyd.base.service.LabelService;
import com.youyd.utils.ParamUtil;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 标签控制层
 * @author: LGG
 * @create: 2018-09-26 15:59
 **/
@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	private LabelService labelService;

	/**
	 * 按照条件查询全部列表
	 * @return Result
	 */
	@GetMapping(value="/search")
	public Result findLabelByCondition(@RequestParam(required = false) Map searchMap){
		if (!ParamUtil.isAvailable(searchMap,"page","size")){
			return new Result(false,StatusCode.PARAM_ERROR.getCode(),StatusCode.PARAM_ERROR.getMsg());
		}
		Integer page = Integer.valueOf(searchMap.get("page").toString());
		Integer size = Integer.valueOf(searchMap.get("size").toString());
		Result result = labelService.findLabelByCondition(searchMap, page, size);
		return result;

	}

	/**
	 * 根据ID查询标签
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public Result findLabelByPrimaryKey(@PathVariable String id){
		Label label = labelService.findLabelByPrimaryKey(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),label);
	}

	/**
	 * 增加标签
	 * @param label
	 * @return Result
	 */
	@PostMapping()
	public Result insertLabel(@RequestBody Label label){
		labelService.insertLabel(label);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改标签
	 * @param label
	 * @param id
	 * @return Result
	 */
	@PutMapping(value="/{id}")
	public Result updateLabel(@RequestBody Label label,@PathVariable String id){
		label.setId(id);
		labelService.updateLabel(label);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 删除标签
	 * @param id
	 * @return Result
	 */
	@DeleteMapping(value="/{id}")
	public Result deleteById(@PathVariable String id){
		labelService.deleteById(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}
}
