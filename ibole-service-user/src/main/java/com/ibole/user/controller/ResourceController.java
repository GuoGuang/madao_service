package com.ibole.user.controller;

import com.ibole.annotation.OptLog;
import com.ibole.config.CustomPageRequest;
import com.ibole.constant.CommonConst;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.Resource;
import com.ibole.user.service.ResourceService;
import com.ibole.utils.DateUtil;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 *  资源管理
 **/

@Api(tags = "资源")
@RestController
@RequestMapping(value = "/su/resource")
public class ResourceController {


	private final ResourceService resourceService;

	@Autowired
	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}


	/**
	 * 条件查询资源
	 *
	 * @param resource 资源
	 * @param queryVO  查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData<List<Resource>> findResByCondition(Resource resource, QueryVO queryVO,
													   @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNumber,
													   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		queryVO.setPageable(new CustomPageRequest(pageNumber, pageSize));
		List<Resource> result = resourceService.findResourceByCondition(resource, queryVO);
		return JsonData.success(result);
	}

	/**
	 * 根据id查询单条
	 * @param resId:资源数据
	 * @return  JsonData
	 */
	@GetMapping(value = "/{resId}")
	public JsonData findById(@PathVariable String resId) {
		Resource result = resourceService.findResourceById(resId);
		return JsonData.success(result);
	}

	/**
	 * 关联查询角色拥有的资源集合
	 * @param roleId:角色id
	 * @return  JsonData
	 */
	@GetMapping(value = "/roles")
	public JsonData findResourceByRoleIds(String[] roleId) {
		Set<Resource> result = resourceService.findResourceByRoleIds(Arrays.asList(roleId));
		return JsonData.success(result);
	}

	/**
	 * 更新资源
	 * @param  resource 资源
	 * @return JsonData
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="更新Resource")
	public JsonData updateByPrimaryKey(@RequestBody @Valid Resource resource) {
		resourceService.saveOrUpdate(resource);
		return JsonData.success();
	}

	/**
	 * 添加一条数据
	 * @param resource 资源
	 * @return JsonData
	 */
	@PostMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="插入Resource")
	public JsonData insertSelective(@RequestBody Resource resource) {
		resource.setCreateAt(DateUtil.getTimestamp());
		resource.setUpdateAt(DateUtil.getTimestamp());
		resourceService.saveOrUpdate(resource);
		return JsonData.success();
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	@OptLog(operationType= CommonConst.DELETE,operationName="删除Resource")
	public JsonData deleteByIds(@RequestBody List<String> resId) {
		resourceService.deleteByIds(resId);
		return JsonData.success();
	}
}