package com.codeway.base.controller.backstage;

import com.codeway.annotation.OptLog;
import com.codeway.base.service.backstage.DictService;
import com.codeway.constant.CommonConst;
import com.codeway.pojo.QueryVO;
import com.codeway.pojo.base.Dict;
import com.codeway.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public DictController(DictService dictService) {
		this.dictService = dictService;
	}

    @GetMapping
    @ApiOperation(value = "条件查询资源", notes = "Dict")
    public JsonData<Page<Dict>> findResByCondition(Dict dict,
                                                           @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        Page<Dict> resData = dictService.findDictByCondition(dict, pageable);
        return JsonData.success(resData);
    }

    @GetMapping("/type")
    @ApiOperation(value = "获取组字典类型，所有根节点", notes = "Dict")
    public JsonData<List<Dict>> fetchDictType(Dict dict) {
        List<Dict> dictTypes = dictService.findIdNameTypeByParentId(dict);
        return JsonData.success(dictTypes);
    }

    @GetMapping("/tree")
    @ApiOperation(value = "按照字典类型获取树形字典，返回满足转换为tree的列表", notes = "Dict")
    public JsonData<List<Dict>> fetchDictTreeList(Dict dict) {
        List<Dict> dictTypes = dictService.fetchDictTreeList(dict);
        return JsonData.success(dictTypes);
    }

    @GetMapping(value = "/{dictId}")
    @ApiOperation(value = "根据id查询单条", notes = "Dict")
    public JsonData<Dict> findById(@PathVariable String dictId) {
        Dict resData = dictService.findDictById(dictId);
        return JsonData.success(resData);
    }

    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "更新字典项")
    @ApiOperation(value = "更新字典项", notes = "Dict")
    public JsonData<Void> updateByPrimaryKey(@RequestBody @Valid Dict dict) {
        dictService.saveOrUpdate(dict);
        return JsonData.success();
    }

    @PostMapping
    @OptLog(operationType = CommonConst.ADD, operationName = "插入字典项")
    @ApiOperation(value = "添加一条数据", notes = "Dict")
    public JsonData<Void> insertSelective(@RequestBody @Valid Dict dict) {
        dictService.saveOrUpdate(dict);
        return JsonData.success();
    }

    @DeleteMapping()
    @OptLog(operationType = CommonConst.DELETE, operationName = "删除字典项")
    @ApiOperation(value = "删除字典项", notes = "Dict")
    public JsonData<Void> deleteByIds(@RequestBody List<String> resId) {
        dictService.deleteBatch(resId);
        return JsonData.success();
    }
}