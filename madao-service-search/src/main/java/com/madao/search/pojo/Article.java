package com.madao.search.pojo;

import com.madao.model.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章板块: 文章类,集成ES
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Document(indexName = "article")
@Getter
@Setter
public class Article extends BasePojo implements Serializable {

    private String id;//ID
    private String columnId;//专栏ID
    private String userId;//用户ID
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;//标题
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;//文章正文
    private String image;//文章封面
    private String isPublic;//是否公开
    private String isTop;//是否置顶
    private Integer visits;//浏览量
    private Integer thumbUp;//点赞数
    private Integer comment;//评论数
    private String state;//审核状态
    private String channelId;//所属频道
    private String url;//URL
    private String type;//类型
}