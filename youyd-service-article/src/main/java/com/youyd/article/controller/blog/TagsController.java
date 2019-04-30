package com.youyd.article.controller.blog;

import com.youyd.article.service.blog.TagsService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.pojo.article.Tags;
import com.youyd.utils.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 前台标签管理
 * /e 区分前后台 uri
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "标签")
@RestController
@RequestMapping(value = "/tags",produces = "application/json")
public class TagsController {

    private final TagsService tagsService;

	@Autowired
	public TagsController(TagsService tagsService) {
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
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }
}
