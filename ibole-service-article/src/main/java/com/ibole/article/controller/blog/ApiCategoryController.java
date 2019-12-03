package com.ibole.article.controller.blog;

import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Tags;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 分类管理
 * 区分前后台 uri
 **/

@Api(tags = "分类")
@RestController
@RequestMapping(value = "/api/article/category")
public class ApiCategoryController {

    /**
     * 查询全部分类
     */
    @ApiOperation(value = "查询分类集合", notes = "Category")
    @GetMapping
    public JsonData findCategoryByCondition(Tags tags, QueryVO queryVO) {
        // ArrayList<Tags> result = tagsService.findTagsByCondition(tags,queryVO);
        String objMap = "{\"status\":\"success\",\"message\":\"获取文章成功\",\"result\":{\"data\":[],\"params\":{\"querys\":{\"state\":1,\"public\":1},\"options\":{\"sort\":{\"_id\":-1},\"page\":1,\"populate\":[\"category\",\"tag\"],\"select\":\"-password -content\"},\"params\":{\"url\":\"/article\"},\"isAuthenticated\":false},\"pagination\":{\"total\":0,\"current_page\":1,\"total_page\":1,\"per_page\":16}}}";
        Map<String, Object> objectMap = JsonUtil.jsonToMap(objMap);
        return JsonData.success(objectMap);
    }
}
