package com.codeway.model.dto.article;

import com.codeway.model.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentDto extends BasePojo implements Serializable {

	@ApiModelProperty("文章的评论表主键")
	private String id;

	@ApiModelProperty("评论人ID，未接入用户系统，暂时使用用户qq号作为唯一id")
	private String userId;

	@ApiModelProperty("父评论ID(如果为0表示文章的顶级评论,每一条评论都可以被评论)")
	private String parentId;

	@NotNull(message = "文章ID不能为空")
	@ApiModelProperty("外键文章表ID")
	private String articleId;

	@NotNull(message = "评论内容不能为空")
	@ApiModelProperty("评论内容")
	private String content;

	@ApiModelProperty("点赞量")
	private Integer upvote;

	@NotNull(message = "头像不能为空")
	@ApiModelProperty("头像")
	private String avatar;

	@ApiModelProperty("评论人名称")
	private String userName;

	@ApiModelProperty("被评论人id")
	private String toId;

	@NotNull(message = "被评论人")
	@ApiModelProperty("头像")
	private String toName;

	@ApiModelProperty("被评论人头像")
	private String toAvatar;

	@ApiModelProperty("回复")
	@JsonIgnoreProperties("reply")
	private List<CommentDto> reply;

}