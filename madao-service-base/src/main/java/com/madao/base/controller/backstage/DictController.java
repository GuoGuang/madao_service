package com.madao.base.controller.backstage;

import com.madao.annotation.OptLog;
import com.madao.base.service.backstage.DictService;
import com.madao.enums.OptLogType;
import com.madao.model.dto.base.DictDto;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
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
@Tag(name = "字典管理")
@RestController
@RequestMapping(value = "/dict")
public class DictController {

	private final DictService dictService;

	public DictController(DictService dictService) {
		this.dictService = dictService;
	}

	@GetMapping
	@Operation(summary = "条件查询资源", description = "Dict")
	public JsonData<Page<DictDto>> findResByCondition(DictDto dictDto,
	                                                  @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<DictDto> resData = dictService.findDictByCondition(dictDto, pageable);
		return JsonData.success(resData);
	}

	@GetMapping("/type")
	@Operation(summary = "获取组字典类型，所有根节点", description = "Dict")
	public JsonData<List<DictDto>> fetchDictType(DictDto dictDto) {
		List<DictDto> dictTypes = dictService.findIdNameTypeByParentId(dictDto);
		return JsonData.success(dictTypes);
	}

	@GetMapping("/tree")
	@Operation(summary = "按照字典类型获取树形字典，返回满足转换为tree的列表", description = "DictDto")
	public JsonData<List<DictDto>> fetchDictTreeList(DictDto dictDto) {
		List<DictDto> dictTypes = dictService.fetchDictTreeList(dictDto);
		return JsonData.success(dictTypes);
	}

	@GetMapping(value = "/{dictId}")
	@Operation(summary = "根据id查询单条", description = "DictDto")
	public JsonData<DictDto> findById(@PathVariable String dictId) {
		DictDto resData = dictService.findDictById(dictId);
		return JsonData.success(resData);
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "更新字典项")
	@Operation(summary = "更新字典项", description = "DictDto")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Validated DictDto dictDto) {
		dictService.saveOrUpdate(dictDto);
		return JsonData.success();
	}

	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "插入字典项")
	@Operation(summary = "添加一条数据", description = "DictDto")
	public JsonData<Void> insertSelective(@RequestBody @Validated DictDto dictDto) {
		dictService.saveOrUpdate(dictDto);
		return JsonData.success();
	}

	@DeleteMapping()
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除字典项")
	@Operation(summary = "删除字典项", description = "DictDto")
	public JsonData<Void> deleteByIds(@RequestBody List<String> resId) {
		dictService.deleteBatch(resId);
		return JsonData.success();
	}
}