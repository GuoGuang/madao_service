package com.ibole.pojo.article;

import com.ibole.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章的评论表
 */
@Getter
@Setter
@Entity
@Table(name = "ar_comment")
public class Comment extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.ibole.config.IdGeneratorConfig")
    @Column(name = "id", columnDefinition = "char")
    private String id; // 评论表id

    @NotNull(message = "文章ID不能为空")
    private String articleId; // 外键文章表ID

    @NotNull(message = "评论内容不能为空")
    private String content; // 评论内容

    private String userId; // 评论人ID

    private String parentId; // 父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)

    private String publishDate; // 评论日期
}