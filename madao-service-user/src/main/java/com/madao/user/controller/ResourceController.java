package com.madao.user.controller;

import com.madao.annotation.OptLog;
import com.madao.enums.OptLogType;
import com.madao.model.QueryVO;
import com.madao.model.dto.user.ResourceDto;
import com.madao.user.service.ResourceService;
import com.madao.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "资源管理")
@RestController
@RequestMapping(value = "/resource")
@AllArgsConstructor
public class ResourceController {

	private final ResourceService resourceService;

	@GetMapping
	@Operation(summary = "条件查询资源", description = "Resource")
	public JsonData<List<ResourceDto>> findResByCondition(ResourceDto resourceDto, QueryVO queryVO) {
		List<ResourceDto> result = resourceService.findResourceByCondition(resourceDto, queryVO);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{resId}")
	@Operation(summary = "根据id查询单条", description = "Resource")
	public JsonData<ResourceDto> findById(@PathVariable String resId) {
		ResourceDto result = resourceService.findResourceById(resId);
		return JsonData.success(result);
	}

	@GetMapping(value = "/roles")
	@Operation(summary = "关联查询角色拥有的资源集合", description = "Resource")
	public JsonData<Set<ResourceDto>> findResourceByRoleIds(String[] roleId) {
		Set<ResourceDto> result = resourceService.findResourceByRoleIds(Arrays.asList(roleId));
		return JsonData.success(result);
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "更新Resource")
	@Operation(summary = "更新资源", description = "Resource")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Validated ResourceDto resourceDto) {
		resourceService.saveOrUpdate(resourceDto);
		return JsonData.success();
	}

	@PostMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "插入Resource")
	@Operation(summary = "添加一条数据", description = "Resource")
	public JsonData<Void> insertSelective(@RequestBody @Validated ResourceDto resourceDto) {
		resourceService.saveOrUpdate(resourceDto);
		return JsonData.success();
	}

	@DeleteMapping()
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除Resource")
	@Operation(summary = "删除资源", description = "Resource")
	public JsonData<Void> deleteByIds(@RequestBody List<String> resId) {
		resourceService.deleteByIds(resId);
		return JsonData.success();
	}
}