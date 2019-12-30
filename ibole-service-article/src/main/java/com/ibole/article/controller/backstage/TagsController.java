package com.ibole.article.controller.backstage;

import com.ibole.annotation.OptLog;
import com.ibole.article.service.backstage.TagsService;
import com.ibole.constant.CommonConst;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Tags;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 标签管理
 * 区分前后台 uri
 * @author  LGG
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
    @ApiOperation(value = "查询标签集合", notes = "tags")
    @GetMapping
    public JsonData findArticleByCondition(Tags tags, QueryVO queryVO) {
        List<Tags> result = tagsService.findTagsByCondition(tags, queryVO);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), result);
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
     * 增加文章标签
     * @param article:标签实例
     */
    @ApiOperation(value = "增加文章标签", notes = "id")
    @PostMapping
    @OptLog(operationType= CommonConst.ADD,operationName="增加文章标签")
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
    @OptLog(operationType= CommonConst.MODIFY,operationName="修改文章标签")
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
    @OptLog(operationType= CommonConst.DELETE,operationName="删除文章标签")
    public JsonData delete(@RequestBody List<String> articleIds) {
        tagsService.deleteByIds(articleIds);
        return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),null);
    }

}
