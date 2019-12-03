package com.ibole.base.controller.blog;

import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "基础接口")
@RestController
@RequestMapping(value = "/api/base")
public class ApiBaseController {

    @GetMapping("/announcement")
    public JsonData findAnnouncement() {
        String hashMap = "{\"status\":\"success\",\"message\":\"获取公告成功\",\"result\":{\"data\":[],\"params\":{\"querys\":{\"state\":1},\"options\":{\"sort\":{\"_id\":-1},\"page\":1},\"params\":{\"url\":\"/announcement\"},\"isAuthenticated\":false},\"pagination\":{\"total\":0,\"current_page\":1,\"total_page\":1,\"per_page\":16}}}";
        Map<String, Object> objMap = JsonUtil.jsonToMap(hashMap);
        return JsonData.success(objMap);
    }

    @GetMapping("/github")
    public JsonData findGithubProject() {
        String hashMap = "{\"status\":\"success\",\"message\":\"获取项目列表成功\",\"result\":[{\"html_url\":\"https://github.com/GuoGuang/pinyinUtils\",\"name\":\"pinyinUtils\",\"fork\":false,\"forks\":2,\"forks_count\":109,\"description\":\"\uD83D\uDD0F汉字转换为拼音，支持多音字，首字母模式，支持标点模式，无标点模式\",\"open_issues_count\":2,\"stargazers_count\":49,\"created_at\":\"2016-09-23T01:59:08Z\",\"language\":\"TypeScript\"}]}";
        Map<String, Object> objMap = JsonUtil.jsonToMap(hashMap);
        return JsonData.success(objMap);
    }

}
