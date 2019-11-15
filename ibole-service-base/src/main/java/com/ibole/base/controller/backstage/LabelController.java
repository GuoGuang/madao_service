package com.ibole.base.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibole.base.service.backstage.LabelService;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.Label;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 标签控制层
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
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), result);

	}

	/**
	 * 根据ID查询标签
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public JsonData findLabelByPrimaryKey(@PathVariable String id){
		Label label = labelService.findLabelByPrimaryKey(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),label);
	}

	/**
	 * 增加标签
	 * @param label 标签实体
	 * @return JsonData
	 */
	@PostMapping()
	public JsonData insertLabel(@RequestBody @Valid Label label){
		labelService.insertLabel(label);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 修改标签
	 * @param label 标签实体
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateLabel(@RequestBody @Valid Label label){
		labelService.updateLabel(label);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除标签
	 * @param labelIds 要删除的id数组
	 * @return JsonData
	 */
	@DeleteMapping
	public JsonData deleteById(@RequestBody List<String> labelIds){
		labelService.deleteById(labelIds);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}
