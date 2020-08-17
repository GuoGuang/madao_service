package com.codeway.user.controller;

import com.codeway.annotation.OptLog;
import com.codeway.enums.OptLogType;
import com.codeway.model.QueryVO;
import com.codeway.model.dto.user.ResourceDto;
import com.codeway.user.service.ResourceService;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Api(tags = "资源管理")
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

	private final ResourceService resourceService;

	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@GetMapping
	@ApiOperation(value = "条件查询资源", notes = "Resource")
	public JsonData<List<ResourceDto>> findResByCondition(ResourceDto resourceDto, QueryVO queryVO) {
		List<ResourceDto> result = resourceService.findResourceByCondition(resourceDto, queryVO);
		return JsonData.success(result);
	}

	@GetMapping(value = "/{resId}")
	@ApiOperation(value = "根据id查询单条", notes = "Resource")
	public JsonData<ResourceDto> findById(@PathVariable String resId) {
		ResourceDto result = resourceService.findResourceById(resId);
		return JsonData.success(result);
	}

	@GetMapping(value = "/roles")
	@ApiOperation(value = "关联查询角色拥有的资源集合", notes = "Resource")
	public JsonData<Set<ResourceDto>> findResourceByRoleIds(String[] roleId) {
		Set<ResourceDto> result = resourceService.findResourceByRoleIds(Arrays.asList(roleId));
		return JsonData.success(result);
	}

	@PutMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "更新Resource")
	@ApiOperation(value = "更新资源", notes = "Resource")
	public JsonData<Void> updateByPrimaryKey(@RequestBody @Validated ResourceDto resourceDto) {
		resourceService.saveOrUpdate(resourceDto);
		return JsonData.success();
	}

	@PostMapping
	@OptLog(operationType = OptLogType.MODIFY, operationName = "插入Resource")
	@ApiOperation(value = "添加一条数据", notes = "Resource")
	public JsonData<Void> insertSelective(@RequestBody @Validated ResourceDto resourceDto) {
		resourceService.saveOrUpdate(resourceDto);
		return JsonData.success();
	}

	@DeleteMapping()
	@OptLog(operationType = OptLogType.DELETE, operationName = "删除Resource")
	@ApiOperation(value = "删除资源", notes = "Resource")
    public JsonData<Void> deleteByIds(@RequestBody List<String> resId) {
        resourceService.deleteByIds(resId);
        return JsonData.success();
    }
}