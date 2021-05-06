package com.madao.base.controller.blog;

import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Api(tags = "基础接口")
@RestController
@RequestMapping(value = "/api/ba")
public class ApiBaseController {

    @GetMapping("/announcement")
    public JsonData<Map<String, Object>> findAnnouncement() {
        String hashMap = "{\"status\":\"success\",\"message\":\"获取公告成功\",\"result\":{\"data\":[],\"params\":{\"querys\":{\"state\":1},\"options\":{\"sort\":{\"_id\":-1},\"page\":1},\"params\":{\"url\":\"/announcement\"},\"isAuthenticated\":false},\"pagination\":{\"total\":0,\"current_page\":1,\"total_page\":1,\"per_page\":16}}}";
        Map<String, Object> objMap = JsonUtil.jsonToMap(hashMap);
        return JsonData.success(objMap);
    }

}
