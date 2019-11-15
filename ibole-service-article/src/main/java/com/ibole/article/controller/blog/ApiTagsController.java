package com.ibole.article.controller.blog;

import com.ibole.article.service.blog.ApiTagsService;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.Result;
import com.ibole.pojo.article.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台标签管理
 * 区分前后台 uri
 * @author LGG
 * @create 2019-01-11
 **/

@Api(tags = "标签")
@RestController
@RequestMapping(value = "/api/tags",produces = "application/json")
public class ApiTagsController {

    private final ApiTagsService tagsService;

	@Autowired
	public ApiTagsController(ApiTagsService tagsService) {
		this.tagsService = tagsService;
	}

	/**
     * 查询全部标签
     *
     * @return Result
     */
    @ApiOperation(value = "查询标签集合", notes = "Article")
    @GetMapping
    public Result findArticleByCondition(Tags tags,QueryVO queryVO) {
//	    ArrayList<Tags> result = tagsService.findTagsByCondition(tags,queryVO);
        return new Result(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }
}
