package com.youyd.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.base.service.LabelService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Label;
import com.youyd.utils.JsonData;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签控制层
 * @author : LGG
 * @create : 2018-09-26 15:59
 **/

@Api(tags = "标签")
@RestController
@RequestMapping("/label")
public class LabelController {

	private final LabelService labelService;

	@Autowired
	public LabelController(LabelService labelService) {
		this.labelService = labelService;
	}

	/**
	 * 按照条件查询全部列表
	 * @return Result
	 */
	@GetMapping
	public JsonData findLabelByCondition(Label label, QueryVO queryVO){
		IPage<Label> result = labelService.findLabelByCondition(label,queryVO);
		return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), result);

	}

	/**
	 * 根据ID查询标签
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public JsonData findLabelByPrimaryKey(@PathVariable String id){
		Label label = labelService.findLabelByPrimaryKey(id);
		return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),label);
	}

	/**
	 * 增加标签
	 * @param label 标签实体
	 * @return JsonData
	 */
	@PostMapping()
	public JsonData insertLabel(@RequestBody Label label){
		labelService.insertLabel(label);
		return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改标签
	 * @param label 标签实体
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateLabel(@RequestBody Label label){
		labelService.updateLabel(label);
		return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 删除标签
	 * @param labelIds 要删除的id数组
	 * @return JsonData
	 */
	@DeleteMapping
	public JsonData deleteById(@RequestBody List<String> labelIds){
		labelService.deleteById(labelIds);
		return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}
}
