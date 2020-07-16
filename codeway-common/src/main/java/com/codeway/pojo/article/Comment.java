package com.codeway.pojo.article;

import com.codeway.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "ar_comment")
public class Comment extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @Column(name = "id", columnDefinition = "char")
    @ApiModelProperty("文章的评论表主键")
    private String id;

    @ApiModelProperty("评论人ID")
    @Column(length = 20)
    private String userId;

    @ApiModelProperty("父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)")
    @Column(length = 20)
    private String parentId;

    @NotNull(message = "文章ID不能为空")
    @ApiModelProperty("外键文章表ID")
    @Column(length = 20)
    private String articleId;

    @NotNull(message = "评论内容不能为空")
    @ApiModelProperty("评论内容")
    @Column(length = 200)
    private String content;

    @ApiModelProperty("点赞量")
    @Column(length = 200)
    private String upvote;

    @NotNull(message = "头像不能为空")
    @ApiModelProperty("头像")
    @Column(length = 200)
    private String avatar;

    @ApiModelProperty("评论人名称")
    @Column(length = 300)
    private String userName;

    @ApiModelProperty("被评论人id")
    @Column(length = 200)
    private String toId;

    @NotNull(message = "被评论人")
    @ApiModelProperty("头像")
    @Column(length = 200)
    private String toName;

    @ApiModelProperty("被评论人头像")
    @Column(length = 300)
    private String toAvatar;

    @ApiModelProperty("")
    @Transient
    @JsonIgnoreProperties("reply")
    private List<Comment> reply;

}