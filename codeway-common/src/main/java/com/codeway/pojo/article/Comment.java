package com.codeway.pojo.article;

import com.codeway.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ar_comment",
		indexes = {
				@Index(name = "comments_article_id", columnList = "articleId"),
				@Index(name = "comments_parent_id", columnList = "parentId")
		})
public class Comment extends BasePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
    @Column(name = "id", columnDefinition = "char")
    @ApiModelProperty("文章的评论表主键")
    private String id;

    @ApiModelProperty("评论人ID，未接入用户系统，暂时使用用户qq号作为唯一id")
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
	private Integer upvote;

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

	@ApiModelProperty("回复")
	@Transient
	@JsonIgnoreProperties("reply")
	private List<Comment> reply;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;
		if (!super.equals(o)) return false;
		Comment comment = (Comment) o;
		return id.equals(comment.id) &&
				Objects.equals(userId, comment.userId) &&
				Objects.equals(parentId, comment.parentId) &&
				Objects.equals(articleId, comment.articleId) &&
				Objects.equals(content, comment.content) &&
				Objects.equals(upvote, comment.upvote) &&
				Objects.equals(avatar, comment.avatar) &&
				Objects.equals(userName, comment.userName) &&
				Objects.equals(toId, comment.toId) &&
				Objects.equals(toName, comment.toName) &&
				Objects.equals(toAvatar, comment.toAvatar);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, userId, parentId, articleId, content, upvote, avatar, userName, toId, toName, toAvatar);
	}
}