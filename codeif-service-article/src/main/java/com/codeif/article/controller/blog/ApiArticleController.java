package com.codeif.article.controller.blog;

import com.codeif.article.service.blog.ApiArticleService;
import com.codeif.constant.ArticleConst;
import com.codeif.constant.CommonConst;
import com.codeif.constant.RedisConstant;
import com.codeif.db.redis.service.RedisService;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Article;
import com.codeif.utils.JsonData;
import com.codeif.utils.JsonUtil;
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
@RequestMapping(value = "/api/ar/article", produces = "application/json")
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
	    redisService.del(RedisConstant.REDIS_KEY_ARTICLE + articleId);
        Object mapJson = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + articleId);
        if (mapJson == null) {
	        Article articleResult = articleService.findArticleById(articleId);
	        redisService.set(RedisConstant.REDIS_KEY_ARTICLE + articleId, articleResult, CommonConst.TIME_OUT_DAY);
            return JsonData.success(articleResult);
        }
	    Article article = JsonUtil.jsonToPojo(mapJson, Article.class);
        return JsonData.success(article);
    }


}
