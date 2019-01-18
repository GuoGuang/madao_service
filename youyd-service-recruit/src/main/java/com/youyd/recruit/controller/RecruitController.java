package com.youyd.recruit.controller;

import com.youyd.pojo.Result;
import com.youyd.utils.ParamUtil;
import com.youyd.utils.StatusCode;
import com.youyd.recruit.pojo.Recruit;
import com.youyd.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 招聘控制层
 * @author: LGG
 * @create: 2018-09-26 15:59
 **/
@RestController
@RequestMapping("/recruit")
public class RecruitController {

	@Autowired
	private RecruitService recruitService;

	/**
	 * 按照条件查询招聘信息
	 * @return Result
	 */
	@SuppressWarnings("Duplicates")
	@GetMapping(value="/search")
	public Result findRecruitByCondition(@RequestParam(required = false) Map searchMap){
		if (!ParamUtil.isAvailable(searchMap,"page","size")){
			return new Result(false,StatusCode.PARAM_ERROR.getCode(),StatusCode.PARAM_ERROR.getMsg());
		}
		Integer page = Integer.valueOf(searchMap.get("page").toString());
		Integer size = Integer.valueOf(searchMap.get("size").toString());
		Result result = recruitService.findRecruitByCondition(searchMap, page, size);
		return result;

	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public Result findRecruitByPrimaryKey(@PathVariable String id){
		Recruit label = recruitService.findRecruitByPrimaryKey(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),label);
	}

	/**
	 * 增加
	 * @param recruit
	 * @return Result
	 */
	@PostMapping()
	public Result insertRecruit(@RequestBody Recruit recruit){
		recruitService.insertRecruit(recruit);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 * @param recruit
	 * @param id
	 * @return Result
	 */
	@PutMapping(value="/{id}")
	public Result updateRecruit(@RequestBody Recruit recruit,@PathVariable String id){
		recruit.setId(id);
		recruitService.updateByPrimaryKeyfindive(recruit);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 * @param id
	 * @return Result
	 */
	@DeleteMapping(value="/{id}")
	public Result deleteById(@PathVariable String id){
		recruitService.deleteById(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 最新职位列表
	 * @return
	 */
	@RequestMapping(value="/search/new_job",method = RequestMethod.GET)
	public Result newJob(){
		List<Recruit> recruits = recruitService.newJob();
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),recruits);
	}

	/**
	 * 推荐职位列表
	 * @return
	 */
	@RequestMapping(value="/search/recommend",method = RequestMethod.GET)
	public Result reCommendList(){
		List<Recruit> recruits = recruitService.reCommendList();
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),recruits);
	}
}






