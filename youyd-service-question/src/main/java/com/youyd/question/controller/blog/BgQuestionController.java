package com.youyd.question.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.question.pojo.Question;
import com.youyd.question.service.blog.BgQuestionService;
import com.youyd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:  问题
 * @author: LGG
 * @create: 2019-02-26
 **/

@RestController
@RequestMapping("/question")
public class BgQuestionController {

    private final BgQuestionService questionService;

	@Autowired
	public BgQuestionController(BgQuestionService questionService) {
		this.questionService = questionService;
	}


	/**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public JsonData findQuestionByCondition(Question question, QueryVO queryVO) {
	    IPage<Question> jsonData = questionService.findQuestionByCondition(queryVO);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),jsonData);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result findQuestionByPrimaryKey(@PathVariable String id) {
        Question result = questionService.findQuestionByPrimaryKey(id);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param Question
     */
    @PostMapping()
    public Result insertQuestion(@RequestBody Question question) {
	    boolean insertResult = questionService.insertQuestion(question);
	    return new Result(insertResult, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }

    /**
     * 修改
     *
     * @param Question
     */
    @PutMapping()
    public Result updateByPrimaryKey(@RequestBody Question question) {
	    boolean updateResult = questionService.updateByPrimaryKeySelective(question);
	    return new Result(updateResult, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }

    /**
     * 删除
     *
     * @param questionIds
     */
    @DeleteMapping
    public Result deleteByIds(@RequestBody List questionIds) {
	    boolean delResult = questionService.deleteByIds(questionIds);
	    return new Result(delResult, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }


    /**
     * 获取最新回复的问题列表
     *
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findNewRecommendList(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        //1.调用业务层方法查询
        //Page<Question> QuestionPage = questionService.findNewRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
        //PageJsonData<Question> QuestionPageJsonData = new PageJsonData<>(QuestionPage.getTotalElements(), QuestionPage.getContent());
        //3. 返回
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }


    /**
     * 获取热门回复的问题列表
     *
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findHotRecommendList(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        //1.调用业务层方法查询
       // Page<Question> QuestionPage = questionService.findHotRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
        //PageJsonData<Question> QuestionPageJsonData = new PageJsonData<>(QuestionPage.getTotalElements(), QuestionPage.getContent());
        //3.返回
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }


    /**
     * 获取等待回复的问题列表
     *
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findWaitRecommendList(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        //1.调用业务层方法查询
       // Page<Question> QuestionPage = questionService.findWaitRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
       // PageJsonData<Question> QuestionPageJsonData = new PageJsonData<>(QuestionPage.getTotalElements(), QuestionPage.getContent());
        //3.返回
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }
}
