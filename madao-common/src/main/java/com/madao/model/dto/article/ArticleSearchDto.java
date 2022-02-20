package com.madao.model.dto.article;

import com.madao.model.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 文章板块: 文章类,集成ES
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
@Document(indexName = "article")
public class ArticleSearchDto extends BasePojo implements Serializable {

    private String id;
    private String columnId;
    private String userId;
//    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	@Field(type = FieldType.Text)
	private String title;
	@Field(type = FieldType.Text)
	private String content;
    private String image;
    private String isPublic;
    private String isTop;
    private Integer visits;
    private Integer thumbUp;
    private Integer comment;
    private String state;
    private String channelId;
    private String url;
    private String type;
}