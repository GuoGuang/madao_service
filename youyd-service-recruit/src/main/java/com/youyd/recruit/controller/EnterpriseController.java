package com.youyd.recruit.controller;

import com.youyd.pojo.Result;
import com.youyd.utils.ParamUtil;
import com.youyd.utils.StatusCode;
import com.youyd.recruit.pojo.Enterprise;
import com.youyd.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 企业控制层
 * @author: LGG
 * @create: 2018-09-26 15:59
 **/
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;

	/**
	 * 按照条件查询全部列表
	 * @return Result
	 */
	@SuppressWarnings("Duplicates")
	@GetMapping(value="/search")
	public Result findEnterpriseByCondition(@RequestParam(required = false) Map searchMap){
		if (!ParamUtil.isAvailable(searchMap,"page","size")){
			return new Result(false,StatusCode.PARAM_ERROR.getCode(),StatusCode.PARAM_ERROR.getMsg());
		}
		Integer page = Integer.valueOf(searchMap.get("page").toString());
		Integer size = Integer.valueOf(searchMap.get("size").toString());
		Result result = enterpriseService.findEnterpriseByCondition(searchMap, page, size);
		return result;

	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public Result findEnterpriseByPrimaryKey(@PathVariable String id){
		Enterprise label = enterpriseService.findEnterpriseByPrimaryKey(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),label);
	}

	/**
	 * 增加
	 * @param enterprise
	 * @return Result
	 */
	@PostMapping()
	public Result insertEnterprise(@RequestBody Enterprise enterprise){
		enterpriseService.insertEnterprise(enterprise);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 * @param enterprise
	 * @param id
	 * @return Result
	 */
	@PutMapping(value="/{id}")
	public Result updateEnterprise(@RequestBody Enterprise enterprise,@PathVariable String id){
		enterprise.setId(id);
		enterpriseService.updateEnterprise(enterprise);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 * @param id
	 * @return Result
	 */
	@DeleteMapping(value="/{id}")
	public Result deleteById(@PathVariable String id){
		enterpriseService.deleteById(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 热门企业列表
	 * @param page
	 * @param size
	 * @return

	 @RequestMapping(value="/search/hotlist/{page}/{size}",method = RequestMethod.GET)
	 public Result hotlist(@PathVariable("page") int page,@PathVariable("size") int size){
	 Page<Enterprise> enterprisePage = enterpriseService.hotlist(page,size);
	 PageResult<Enterprise> pageResult = new PageResult<>(enterprisePage.getTotalElements(),enterprisePage.getContent());
	 return new Result(true,StatusCode.OK,"查询成功",pageResult);
	 }*/
}




