package com.ibole.pojo.article;

import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ar_comment")
public class Comment extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    @Column(name = "id", columnDefinition = "char")
    @ApiModelProperty("文章的评论表主键")
    private String id;

    @NotNull(message = "文章ID不能为空")
    @ApiModelProperty("外键文章表ID")
    private String articleId;

    @NotNull(message = "评论内容不能为空")
    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论人ID")
    private String userId;

    @ApiModelProperty("父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)")
    private String parentId;

    @ApiModelProperty("评论日期")
    private String publishDate;
}