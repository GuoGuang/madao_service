package com.ibole.pojo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@ApiModel(value = "ar_article_tags", description = "文章_标签")
@Entity
@Table(name = "ar_article_tags")
public class ArticleTags implements Serializable {

    @ApiModelProperty("ID")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    private String id;

    @ApiModelProperty("文章id")
    @Column(columnDefinition = "article_id")
    private String articleId;

    @ApiModelProperty("标签id")
    @Column(columnDefinition = "tags_id")
    private String tagsId;

}