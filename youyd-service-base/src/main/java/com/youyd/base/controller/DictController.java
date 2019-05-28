package com.youyd.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.base.service.DictService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Dict;
import com.youyd.utils.DateUtil;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 *  字典管理
 * @author : LGG
 * @create : 2018-12-23
 **/

@Api(tags = "字典")
@RestController
@RequestMapping(value = "/dict")
public class DictController {


	private final DictService dictService;

	@Autowired
	public DictController(DictService dictService) {
		this.dictService = dictService;
	}


	/**
	 * 条件查询菜单
	 * @param dict 查询参数
	 * @param queryVO 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findResByCondition(Dict dict, QueryVO queryVO) {
		IPage<Dict> resData = dictService.findDictByCondition(dict,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 获取组字典类型，所有根节点
	 * @param dict 菜单实体
	 * @return JsonData
	 */
	@GetMapping("/type")
	public JsonData fetchDictType(Dict dict) {
		List<Dict> dictTypes = dictService.fetchDictType(dict);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), dictTypes);
	}

	/**
	 * 按照字典类型获取树形字典
	 * 返回满足转换为tree的列表
	 * @param dict 菜单实体
	 * @return JsonData
	 */
	@GetMapping("/tree")
	public JsonData fetchDictTreeList(Dict dict) {
		List<Dict> dictTypes = dictService.fetchDictTreeList(dict);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), dictTypes);
	}

	/**
	 * 根据id查询单条
	 * @param dictId:字典id
	 * @return  JsonData
	 */
	@GetMapping(value = "/{dictId}")
	public JsonData findById(@PathVariable String dictId) {
		Dict resData = dictService.findDictById(dictId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 更新资源
	 * @param  dict 菜单
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateByPrimaryKey(@RequestBody @Valid Dict dict) {
		boolean state = dictService.updateByPrimaryKey(dict);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 添加一条数据
	 * @param dict 菜单
	 * @return JsonData
	 */
	@PostMapping
	public JsonData insertSelective(@RequestBody @Valid Dict dict) {
		dict.setCreateAt(DateUtil.getTimestamp());
		dict.setUpdateAt(DateUtil.getTimestamp());
		boolean state = dictService.insertDictSelective(dict);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	public JsonData deleteByIds(@RequestBody List<String> resId) {
		boolean state = dictService.deleteDictByIds(resId);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}