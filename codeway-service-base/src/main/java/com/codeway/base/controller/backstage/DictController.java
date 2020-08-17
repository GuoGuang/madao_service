package com.codeway.base.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.base.service.backstage.DictService;
import com.codeway.enums.OptLogType;
import com.codeway.model.dto.base.DictDto;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "字典管理")
@RestController
@RequestMapping(value = "/dict")
public class DictController {

	private final DictService dictService;

	public DictController(DictService dictService) {
		this.dictService = dictService;
	}

	@GetMapping
	@ApiOperation(value = "条件查询资源", notes = "Dict")
	public JsonData<Page<DictDto>> findResByCondition(DictDto dictDto,
	                                                  @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
		Page<DictDto> resData = dictService.findDictByCondition(dictDto, pageable);
		return JsonData.success(resData);
	}

	@GetMapping("/type")
	@ApiOperation(value = "获取组字典类型，所有根节点", notes = "Dict")
	public JsonData<List<DictDto>> fetchDictType(DictDto dictDto) {
		List<DictDto> dictTypes = dictService.findIdNameTypeByParentId(dictDto);
		return JsonData.success(dictTypes);
	}

	@GetMapping("/tree")
	@ApiOperation(value = "按照字典类型获取树形字典，返回满足转换为tree的列表", notes = "DictDto")
	public JsonData<List<DictDto>> fetchDictTreeList(DictDto dictDto) {
		List<DictDto> dictTypes = dictService.fetchDictTreeList(dictDto);
		return JsonData.success(dictTypes);
	}

	@GetMapping(value = "/{dictId}")
	@ApiOperation(value = "根据id查询单条", notes = "DictDto")
	public JsonData<DictDto> findById(@PathVariable String dictId) {
		DictDto resData = dictService.findDictById(dictId);
		return JsonData.success(resData);
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "更新字典项")
	@ApiOperation(value = "更新字典项", notes = "DictDto")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid DictDto dictDto) {
		dictService.saveOrUpdate(dictDto);
		return JsonData.success();
	}

	@PostMapping
	@OptLog(operationType = OptLogType.ADD, operationName = "插入字典项")
	@ApiOperation(value = "添加一条数据", notes = "DictDto")
	public JsonData<Void> insertSelective(@RequestBody @Valid DictDto dictDto) {
		dictService.saveOrUpdate(dictDto);
		return JsonData.success();
	}

	@DeleteMapping()
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除字典项")
	@ApiOperation(value = "删除字典项", notes = "DictDto")
	public JsonData<Void> deleteByIds(@RequestBody List<String> resId) {
        dictService.deleteBatch(resId);
        return JsonData.success();
    }
}