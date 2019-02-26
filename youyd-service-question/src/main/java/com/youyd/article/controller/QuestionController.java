package com.youyd.article.controller;

import com.youyd.article.pojo.Question;
import com.youyd.article.service.QuestionService;
import com.youyd.utils.JsonData;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:  问题
 * @author: LGG
 * @create: 2019-02-26
 **/

@RestController
@RequestMapping("/Question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonData findQuestionByCondition() {
        List<Question> JsonData = questionService.findQuestionByCondition();
        return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),JsonData);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public JsonData findQuestionByPrimaryKey(@PathVariable String id) {
        Question result = questionService.findQuestionByPrimaryKey(id);
        return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param Question
     */
    @PostMapping()
    public JsonData insertQuestion(@RequestBody Question question) {
	    questionService.insertQuestion(question);
	    return new JsonData(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 修改
     *
     * @param Question
     */
    @PutMapping()
    public JsonData updateByPrimaryKey(@RequestBody Question question) {
	    boolean result = questionService.updateByPrimaryKeySelective(question);
	    return new JsonData(result, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
    }

    /**
     * 删除
     *
     * @param questionId
     */
    @DeleteMapping
    public JsonData delete(@RequestBody List questionId) {
	    boolean result = questionService.deleteByIds(questionId);
	    return new JsonData(result,StatusCode.OK.getCode(), StatusCode.OK.getMsg());
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
    public JsonData findNewRecommendList(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        //1.调用业务层方法查询
        //Page<Question> QuestionPage = questionService.findNewRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
        //PageJsonData<Question> QuestionPageJsonData = new PageJsonData<>(QuestionPage.getTotalElements(), QuestionPage.getContent());
        //3.返回
        return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
    public JsonData findHotRecommendList(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        //1.调用业务层方法查询
       // Page<Question> QuestionPage = questionService.findHotRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
        //PageJsonData<Question> QuestionPageJsonData = new PageJsonData<>(QuestionPage.getTotalElements(), QuestionPage.getContent());
        //3.返回
        return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
    public JsonData findWaitRecommendList(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        //1.调用业务层方法查询
       // Page<Question> QuestionPage = questionService.findWaitRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
       // PageJsonData<Question> QuestionPageJsonData = new PageJsonData<>(QuestionPage.getTotalElements(), QuestionPage.getContent());
        //3.返回
        return new JsonData(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }
}
