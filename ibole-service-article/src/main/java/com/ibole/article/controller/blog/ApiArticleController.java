package com.ibole.article.controller.blog;

import com.ibole.article.service.blog.ApiArticleService;
import com.ibole.constant.ArticleConst;
import com.ibole.constant.CommonConst;
import com.ibole.constant.RedisConstant;
import com.ibole.db.redis.service.RedisService;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.article.Article;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "前台网站文章")
@RestController
@RequestMapping(value = "/api/ar", produces = "application/json")
public class ApiArticleController {

    @Autowired
    private ApiArticleService articleService;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "查询集合", notes = "Article")
    @GetMapping
    public JsonData<Object> findArticleByCondition(Article article, QueryVO queryVO) {
        if (ArticleConst.SORT_TYPE_HOT.equals(queryVO.getSortType())) {
            List<Object> hotList = redisService.lGet("1", 1, 1);
            return JsonData.success(hotList);
        }
        QueryResults<Article> result = articleService.findArticleByCondition(article, queryVO);
//        String objMap = "{\"status\":true,\"code\":20000,\"message\":\"操作成功\",\"data\":{\"records\":[{\"updateAt\":\"2019-01-29T11:47:46.000+0000\",\"createAt\":\"2019-01-29T11:47:44.000+0000\",\"id\":5,\"categoryId\":1,\"userId\":\"12151044\",\"title\":\"受脑认知和神经科学启发的人工智能\",\"image\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg\",\"isPublic\":\"1\",\"isTop\":\"1\",\"visits\":25,\"upvote\":3562,\"comment\":11,\"reviewState\":\"0\",\"url\":\"1\",\"type\":\"1\",\"importance\":\"9\",\"description\":\"人工智能渗透到了人类社会各个领域，但目前来看，无论是深度学习还是其它方法，解决的都是单一问题。人类大脑是一个多问题求解的结构，怎么从脑认知和神经科学中得到构造健壮的人工智能的启示，国内外都做了非常多有成效的研究。\\r\\n\\r\\n一、实现健壮的人工智能的方法\\r\\n\\r\\n人类面临的许多问题具有不确定性、脆弱性和开放性。今天人工智能的理论框架，建立在演绎逻辑和语义描述的基础方法之上，但我们不可能对人类社会的所有问题建模，因为这中间存在着条件问题，我们不可能把一个行为的所有条件都模拟出，这是传统人工智能的局限性。\\r\\n\\r\\n这个局限性主要表现在几个方面：\\r\\n\\r\\n1、需要对问题本身抽象出一个精确数学意义上的解析式的数学模型（抽象不出，即归纳为不可解问题）\",\"keywords\":\"1\",\"origin\":\"2\",\"category\":{\"updateAt\":null,\"createAt\":null,\"id\":1,\"parentId\":null,\"name\":\"java\",\"summary\":null,\"userId\":null,\"state\":null}},{\"updateAt\":\"2019-01-29T11:47:46.000+0000\",\"createAt\":\"2019-01-29T11:47:44.000+0000\",\"id\":4,\"categoryId\":1,\"userId\":\"12151044\",\"title\":\"受脑认知和神经科学启发的人工智能\",\"image\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg\",\"isPublic\":\"1\",\"isTop\":\"1\",\"visits\":25,\"upvote\":3562,\"comment\":11,\"reviewState\":\"0\",\"url\":\"1\",\"type\":\"1\",\"importance\":\"9\",\"description\":\"人工智能渗透到了人类社会各个领域，但目前来看，无论是深度学习还是其它方法，解决的都是单一问题。人类大脑是一个多问题求解的结构，怎么从脑认知和神经科学中得到构造健壮的人工智能的启示，国内外都做了非常多有成效的研究。\\r\\n\\r\\n一、实现健壮的人工智能的方法\\r\\n\\r\\n人类面临的许多问题具有不确定性、脆弱性和开放性。今天人工智能的理论框架，建立在演绎逻辑和语义描述的基础方法之上，但我们不可能对人类社会的所有问题建模，因为这中间存在着条件问题，我们不可能把一个行为的所有条件都模拟出，这是传统人工智能的局限性。\\r\\n\\r\\n这个局限性主要表现在几个方面：\\r\\n\\r\\n1、需要对问题本身抽象出一个精确数学意义上的解析式的数学模型（抽象不出，即归纳为不可解问题）\",\"keywords\":\"1\",\"origin\":\"2\",\"category\":{\"updateAt\":null,\"createAt\":null,\"id\":1,\"parentId\":null,\"name\":\"java\",\"summary\":null,\"userId\":null,\"state\":null}},{\"updateAt\":\"2019-01-29T11:47:46.000+0000\",\"createAt\":\"2019-01-29T11:47:44.000+0000\",\"id\":3,\"categoryId\":1,\"userId\":\"12151044\",\"title\":\"受脑认知和神经科学启发的人工智能\",\"image\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg\",\"isPublic\":\"1\",\"isTop\":\"1\",\"visits\":25,\"upvote\":3562,\"comment\":11,\"reviewState\":\"0\",\"url\":\"1\",\"type\":\"1\",\"importance\":\"9\",\"description\":\"人工智能渗透到了人类社会各个领域，但目前来看，无论是深度学习还是其它方法，解决的都是单一问题。人类大脑是一个多问题求解的结构，怎么从脑认知和神经科学中得到构造健壮的人工智能的启示，国内外都做了非常多有成效的研究。\\r\\n\\r\\n一、实现健壮的人工智能的方法\\r\\n\\r\\n人类面临的许多问题具有不确定性、脆弱性和开放性。今天人工智能的理论框架，建立在演绎逻辑和语义描述的基础方法之上，但我们不可能对人类社会的所有问题建模，因为这中间存在着条件问题，我们不可能把一个行为的所有条件都模拟出，这是传统人工智能的局限性。\\r\\n\\r\\n这个局限性主要表现在几个方面：\\r\\n\\r\\n1、需要对问题本身抽象出一个精确数学意义上的解析式的数学模型（抽象不出，即归纳为不可解问题）\",\"keywords\":\"1\",\"origin\":\"2\",\"category\":{\"updateAt\":null,\"createAt\":null,\"id\":1,\"parentId\":null,\"name\":\"java\",\"summary\":null,\"userId\":null,\"state\":null}},{\"updateAt\":\"2019-01-29T11:47:46.000+0000\",\"createAt\":\"2019-01-29T11:47:44.000+0000\",\"id\":1,\"categoryId\":1,\"userId\":\"12151044\",\"title\":\"受脑认知和神经科学启发的人工智能\",\"image\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg\",\"isPublic\":\"1\",\"isTop\":\"1\",\"visits\":25,\"upvote\":3562,\"comment\":11,\"reviewState\":\"0\",\"url\":\"1\",\"type\":\"1\",\"importance\":\"9\",\"description\":\"人工智能渗透到了人类社会各个领域，但目前来看，无论是深度学习还是其它方法，解决的都是单一问题。人类大脑是一个多问题求解的结构，怎么从脑认知和神经科学中得到构造健壮的人工智能的启示，国内外都做了非常多有成效的研究。\\r\\n\\r\\n一、实现健壮的人工智能的方法\\r\\n\\r\\n人类面临的许多问题具有不确定性、脆弱性和开放性。今天人工智能的理论框架，建立在演绎逻辑和语义描述的基础方法之上，但我们不可能对人类社会的所有问题建模，因为这中间存在着条件问题，我们不可能把一个行为的所有条件都模拟出，这是传统人工智能的局限性。\\r\\n\\r\\n这个局限性主要表现在几个方面：\\r\\n\\r\\n1、需要对问题本身抽象出一个精确数学意义上的解析式的数学模型（抽象不出，即归纳为不可解问题）\",\"keywords\":\"1\",\"origin\":\"2\",\"category\":{\"updateAt\":null,\"createAt\":null,\"id\":1,\"parentId\":null,\"name\":\"java\",\"summary\":null,\"userId\":null,\"state\":null}},{\"updateAt\":\"2019-01-30T02:16:49.000+0000\",\"createAt\":\"2019-01-30T02:16:49.000+0000\",\"id\":2,\"categoryId\":2,\"userId\":\"2\",\"title\":\"测试\",\"image\":\"https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=c8a9d4e2841363270aedc433a18fa056/11385343fbf2b2114a65cd70c48065380cd78e41.jpg\",\"isPublic\":\"2\",\"isTop\":\"2\",\"visits\":0,\"upvote\":3,\"comment\":11,\"reviewState\":\"2\",\"url\":\"2\",\"type\":\"2\",\"importance\":\"2\",\"description\":\"2\",\"keywords\":\"2\",\"origin\":\"1\",\"category\":{\"updateAt\":null,\"createAt\":null,\"id\":2,\"parentId\":null,\"name\":\"python\",\"summary\":null,\"userId\":null,\"state\":null}}],\"total\":2,\"size\":10,\"current\":1,\"searchCount\":true,\"pages\":1}}";
//        Map<String, Object> result = JsonUtil.jsonToMap(objMap);
        return JsonData.success(result);
    }

    @GetMapping(value = "/{articleId}")
    public JsonData<Article> findArticleByPrimaryKey(@PathVariable String articleId) {
        Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId);
        Article article = JsonUtil.jsonToPojo(mapJson.toString(), Article.class);
        if (article == null) {
            article = articleService.findArticleById(articleId);
            redisService.set(RedisConstant.REDIS_KEY_ARTICLE + articleId, article, CommonConst.TIME_OUT_DAY);
        }
//        String objMap = "{\"status\":true,\"code\":20000,\"message\":\"操作成功\",\"data\":{\"updateAt\":\"2019-01-29T11:47:46.000+0000\",\"createAt\":\"2019-01-29T11:47:44.000+0000\",\"id\":1,\"categoryId\":1,\"userId\":\"12151044\",\"title\":\"受脑认知和神经科学启发的人工智能\",\"image\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1229855747,4092679124&fm=26&gp=0.jpg\",\"isPublic\":\"1\",\"isTop\":\"1\",\"visits\":25,\"upvote\":3562,\"comment\":11,\"reviewState\":\"0\",\"url\":\"1\",\"type\":\"1\",\"importance\":\"9\",\"description\":\"大概描述\",\"keywords\":\"1\",\"origin\":1,\"content\":\"内置Markdown编辑器和使用指南，非技术类笔记用户，千万不要被「标记」、「语言」吓到，Markdown的语法十分简单，常用的标记符号不超过十个，用于日常写作记录绰绰有余，不到半小时就能完全掌握。**就是这十个不到的标记符号，却能让人**优雅地沉浸式记录，专注内容而不是纠结排版**，达到「心中无尘，码字入神」的境界。\",\"category\":null,\"related\":[{\"meta\":{\"likes\":56,\"views\":1709,\"comments\":18,\"_id\":\"5cc44adfaa99112280b8e2ac\"},\"title\":\"何以为家\",\"description\":\"风说随沙\",\"thumb\":\"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/318-0330.jpg\",\"create_at\":\"2019-04-27T12:28:15.975Z\",\"update_at\":\"2019-06-18T10:58:21.093Z\",\"id\":144},{\"id\":91,\"title\":\"什么样的人最自由？\",\"description\":\"一个能够安住于不确定和不可控之中的人最自由。\",\"thumb\":\"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/family.jpg\",\"meta\":{\"likes\":17,\"views\":1054,\"comments\":0,\"_id\":\"5d2b46ef76cf7214fe5e37b5\"},\"update_at\":\"2018-08-04T21:57:55.835Z\",\"create_at\":\"2018-03-27T06:05:55.276Z\"},{\"meta\":{\"likes\":9,\"views\":772,\"comments\":3,\"_id\":\"5d2b46ef76cf7214fe5e37bb\"},\"title\":\"这个世界其实是你想象出来的\",\"description\":\"恐怖的量子力学正彻底颠覆人类的物理世界观\",\"thumb\":\"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/freedom-new%281%29.png\",\"create_at\":\"2018-08-10T20:04:11.584Z\",\"update_at\":\"2018-12-06T11:47:08.685Z\",\"id\":107},{\"id\":3,\"title\":\"多巴胺\",\"description\":\"我喜欢走极端的人。 \\n我喜欢能下生命的大海和能上生命的高山的人。 \\n我喜欢带着全部自我奔向事物的独特性而不停在两个相反事物之间犹豫不决的人。 \\n我喜欢充满力量和具有坚定目标的心，我喜欢本体不接受装配。本质不会被分裂的、简单朴素的灵魂。\",\"thumb\":\"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/lotus-new2.jpg\",\"meta\":{\"likes\":16,\"views\":1629,\"comments\":2,\"_id\":\"5d2b46ef76cf7214fe5e37aa\"},\"create_at\":\"2017-02-14T01:27:44.591Z\",\"update_at\":\"2019-01-23T19:22:13.412Z\"},{\"meta\":{\"likes\":14,\"views\":755,\"comments\":0,\"_id\":\"5d2b46ef76cf7214fe5e37b8\"},\"title\":\"尽管投入爱河吧\",\"description\":\"照见五蕴皆空，度一切苦厄\",\"thumb\":\"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/lotus4.jpg\",\"create_at\":\"2018-07-20T18:46:42.858Z\",\"update_at\":\"2018-08-04T21:57:22.970Z\",\"id\":99},{\"meta\":{\"likes\":27,\"views\":1261,\"comments\":15,\"_id\":\"5cd831ef994f7269753d72e9\"},\"title\":\"极道以御术\",\"description\":\"则天人合一\",\"thumb\":\"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/icode/image/taichi-cosmos.jpg\",\"create_at\":\"2019-05-12T14:47:11.332Z\",\"update_at\":\"2019-05-14T09:53:38.382Z\",\"id\":146}]}}";
//        Map<String, Object> result = JsonUtil.jsonToMap(objMap);
        return JsonData.success(article);
    }


}
