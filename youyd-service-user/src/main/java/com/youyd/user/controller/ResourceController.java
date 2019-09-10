package com.youyd.user.controller;

import com.youyd.annotation.OptLog;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.Resource;
import com.youyd.user.service.ResourceService;
import com.youyd.utils.DateUtil;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


/**
 *  资源管理
 * @author : LGG
 * @create : 2018-12-23
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
	 * @param resource 资源
	 * @param queryVO 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findResByCondition(Resource resource, QueryVO queryVO) {
		List<Resource> resData = resourceService.findResourceByCondition(resource,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 根据id查询单条
	 * @param resId:资源数据
	 * @return  JsonData
	 */
	@GetMapping(value = "/{resId}")
	public JsonData findById(@PathVariable String resId) {
		Resource resData = resourceService.findResourceById(resId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 关联查询角色拥有的资源集合
	 * @param roleId:角色id
	 * @return  JsonData
	 */
	@GetMapping(value = "/roles")
	public JsonData findResourceByRoleIds(String[] roleId) {
		Set<Resource> resData = resourceService.findResourceByRoleIds(roleId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 更新资源
	 * @param  resource 资源
	 * @return JsonData
	 */
	@PutMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="更新Resource")
	public JsonData updateByPrimaryKey(@RequestBody @Valid Resource resource) {
		boolean state = resourceService.updateByPrimaryKey(resource);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
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
		boolean state = resourceService.insertSelective(resource);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	@OptLog(operationType= CommonConst.DELETE,operationName="删除Resource")
	public JsonData deleteByIds(@RequestBody List<String> resId) {
		boolean state = resourceService.deleteByIds(resId);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}