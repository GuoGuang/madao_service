package com.youyd.pojo.article;

import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章的评论表
 */
@Getter
@Setter
public class Comment extends BasePojo implements Serializable {

    private String id; // 评论表id

	@NotNull(message="文章ID不能为空")
    private String articleId; // 外键文章表ID

	@NotNull(message="评论内容不能为空")
    private String content; // 评论内容

    private String userId; // 评论人ID

    private String parentId; // 父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)

    private Date publishDate; // 评论日期
}