package com.youyd.question.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.question.pojo.Answers;
import com.youyd.question.service.AnswersService;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 问题答案
 * @author: LGG
 * @create: 2019-02-26
 **/

@RestController
@RequestMapping("/sq/answers")
public class AnswersController {

	private final AnswersService answersService;

	@Autowired
	public AnswersController(AnswersService answersService) {
		this.answersService = answersService;
	}


	/**
	 * 查询全部数据
	 *
	 * @return Result
	 */
	@GetMapping
	public Result findAnswersByCondition(Answers answers) {
		IPage<Answers> byCondition = answersService.findAnswersByCondition(answers);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), byCondition);
	}

	/**
	 * 根据ID查询
	 *
	 * @param id ID
	 * @return Result
	 */
	@GetMapping(value = "/{id}")
	public Result findAnswersByPrimaryKey(@PathVariable String id) {
		Answers result = answersService.findAnswersByPrimaryKey(id);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), result);
	}


	/**
	 * 增加
	 *
	 * @param answers 要添加的对象
	 */
	@PostMapping
	public Result insertAnswers(Answers answers) {
		answersService.insertAnswers(answers);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 * @param answers 被修改的对象
	 */
	@PutMapping()
	public Result updateByAnswersSelective(Answers answers) {
		boolean result = answersService.updateByAnswersSelective(answers);
		return new Result(result, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 *
	 * @param answersIds 答案id数组
	 */
	@DeleteMapping()
	public Result deleteByPrimaryKey(@RequestBody List answersIds) {
		answersService.deleteByPrimaryKey(answersIds);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

}
