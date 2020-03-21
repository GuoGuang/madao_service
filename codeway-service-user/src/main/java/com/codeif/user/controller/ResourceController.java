package com.codeif.user.controller;

import com.codeif.annotation.OptLog;
import com.codeif.constant.CommonConst;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.user.Resource;
import com.codeif.user.service.ResourceService;
import com.codeif.utils.DateUtil;
import com.codeif.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Api(tags = "资源管理")
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {


	private final ResourceService resourceService;

	@Autowired
	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

    @GetMapping
    @ApiOperation(value = "条件查询资源", notes = "Resource")
    public JsonData<List<Resource>> findResByCondition(Resource resource, QueryVO queryVO) {
        List<Resource> result = resourceService.findResourceByCondition(resource, queryVO);
        return JsonData.success(result);
    }

    @GetMapping(value = "/{resId}")
    @ApiOperation(value = "根据id查询单条", notes = "Resource")
    public JsonData<Resource> findById(@PathVariable String resId) {
        Resource result = resourceService.findResourceById(resId);
        return JsonData.success(result);
    }

    @GetMapping(value = "/roles")
    @ApiOperation(value = "关联查询角色拥有的资源集合", notes = "Resource")
    public JsonData<Set<Resource>> findResourceByRoleIds(String[] roleId) {
        Set<Resource> result = resourceService.findResourceByRoleIds(Arrays.asList(roleId));
        return JsonData.success(result);
    }

    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "更新Resource")
    @ApiOperation(value = "更新资源", notes = "Resource")
    public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid Resource resource) {
        resourceService.saveOrUpdate(resource);
        return JsonData.success();
    }

    @PostMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "插入Resource")
    @ApiOperation(value = "添加一条数据", notes = "Resource")
    public JsonData<Void> insertSelective(@RequestBody @Valid Resource resource) {
        resourceService.saveOrUpdate(resource);
        return JsonData.success();
    }

    @DeleteMapping()
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除Resource")
    @ApiOperation(value = "删除资源", notes = "Resource")
    public JsonData<Void> deleteByIds(@RequestBody List<String> resId) {
        resourceService.deleteByIds(resId);
        return JsonData.success();
    }
}