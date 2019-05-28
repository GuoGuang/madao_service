package com.youyd.article.controller.backstage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.service.blog.TagsService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.article.Tags;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 标签管理
 * /e 区分前后台 uri
 * @author: LGG
 * @create: 2019-01-11
 **/

@Api(tags = "标签")
@RestController
@RequestMapping(value = "/sa/tags",produces = "application/json")
public class SaTagsController {

    private final TagsService tagsService;

	@Autowired
	public SaTagsController(TagsService tagsService) {
		this.tagsService = tagsService;
	}

	/**
     * 查询全部标签
     *
     * @return Result
     */
    @ApiOperation(value = "查询标签集合", notes = "tags")
    @GetMapping
    public JsonData findArticleByCondition(Tags tags, QueryVO queryVO ) {
	    IPage<Tags> result = tagsService.findTagsByCondition(tags,queryVO);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @ApiOperation(value = "按照id查询标签", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData findArticleByPrimaryKey(@PathVariable String id) {
        Tags result = tagsService.findTagsById(id);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),result);
    }


    /**
     * 增加
     * @param article:标签实例
     */
    @ApiOperation(value = "添加一条新的文章", notes = "id")
    @PostMapping
    public JsonData insertArticle(@RequestBody @Valid Tags tags) {
        tagsService.insertTags(tags);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 修改
     * @param article:标签实例
     */
    @ApiOperation(value = "按照id修改", notes = "id")
    @PutMapping
    public JsonData updateTagsById(@RequestBody @Valid Tags tags) {
        tagsService.updateTagsById(tags);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

    /**
     * 删除
     * @param articleIds 文章id
     */
    @ApiOperation(value = "删除", notes = "id")
    @DeleteMapping
    public JsonData delete(@RequestBody List<String> articleIds) {
        tagsService.deleteByIds(articleIds);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

}
