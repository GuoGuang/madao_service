package com.youyd.article.controller;

import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import com.youyd.article.pojo.Problem;
import com.youyd.article.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findProblemByCondition() {
        List<Problem> result = problemService.findProblemByCondition();
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findProblemByPrimaryKey(@PathVariable String id) {
        Problem result = problemService.findProblemByPrimaryKey(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param problem
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result insertProblem(@RequestBody Problem problem) {
        problemService.insertProblem(problem);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result updateByPrimaryKeySelective(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.updateByPrimaryKeySelective(problem);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
        //Page<Problem> problemPage = problemService.findNewRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
        //PageResult<Problem> problemPageResult = new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
        //3.返回
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
       // Page<Problem> problemPage = problemService.findHotRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
        //PageResult<Problem> problemPageResult = new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
        //3.返回
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
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
       // Page<Problem> problemPage = problemService.findWaitRecommendList(labelid, page, size);
        //2.创建自定义的分页对象
       // PageResult<Problem> problemPageResult = new PageResult<>(problemPage.getTotalElements(), problemPage.getContent());
        //3.返回
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }
}
