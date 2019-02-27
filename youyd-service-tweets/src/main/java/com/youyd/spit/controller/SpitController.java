package com.youyd.tweets.controller;

import com.youyd.pojo.Result;
import com.youyd.utils.ParamUtil;
import com.youyd.utils.StatusCode;
import com.youyd.tweets.pojo.Spit;
import com.youyd.tweets.service.SpitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 招聘控制层
 * @author: LGG
 * @create: 2018-09-26 15:59
 **/
@RestController
@RequestMapping("/tweets")
public class SpitController {

	@Autowired
	private SpitService tweetsService;

	/**
	 * 按照条件查询招聘信息
	 * @return Result
	 */
	@SuppressWarnings("Duplicates")
	@GetMapping(value="/search")
	public Result findSpitByCondition(@RequestParam(required = false) Map searchMap){
		if (!ParamUtil.isAvailable(searchMap,"page","size")){
			return new Result(false,StatusCode.PARAM_ERROR.getCode(),StatusCode.PARAM_ERROR.getMsg());
		}
		Integer page = Integer.valueOf(searchMap.get("page").toString());
		Integer size = Integer.valueOf(searchMap.get("size").toString());
		Result result = tweetsService.findSpitByCondition(searchMap, page, size);
		return result;

	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return Result
	 */
	@GetMapping(value="/{id}")
	public Result findSpitByPrimaryKey(@PathVariable String id){
		Spit label = tweetsService.findSpitByPrimaryKey(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),label);
	}

	/**
	 * 增加
	 * @param tweets
	 * @return Result
	 */
	@PostMapping()
	public Result insertSpit(@RequestBody Spit tweets){
		tweetsService.insertSpit(tweets);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 * @param tweets
	 * @param id
	 * @return Result
	 */
	@PutMapping(value="/{id}")
	public Result updateSpit(@RequestBody Spit tweets,@PathVariable String id){
		tweets.setId(id);
		tweetsService.updateByPrimaryKeyfindive(tweets);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 * @param id
	 * @return Result
	 */
	@DeleteMapping(value="/{id}")
	public Result deleteById(@PathVariable String id){
		tweetsService.deleteById(id);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
	}

	/**
	 * 根据上级ID查询吐槽分页数据
	 * @param parentId
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/comment/{parentId}/{page}/{size}")
	public Result findByParentid(@PathVariable String parentId, @PathVariable Integer page,@PathVariable Integer size){
		if (StringUtils.isBlank(page.toString()) || StringUtils.isBlank(size.toString())){
			return new Result(false,StatusCode.PARAM_ERROR.getCode(),StatusCode.PARAM_ERROR.getMsg());
		}
		Result result = tweetsService.findSpitByParentid(parentId, page, size);
		return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
	}

	/**
	 * 点赞
	 * @param id : 被点赞吐槽id
	 * @return
	 */
	@RequestMapping(value="/thumbUp/{id}",method=RequestMethod.PUT)
	public Result updateThumbUp(@PathVariable String id){
		tweetsService.updateThumbUp(id);
		return new Result(true,StatusCode.OK.getCode(),"点赞成功");
	}

}






