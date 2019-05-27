package com.youyd.pojo.tweets;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import com.youyd.pojo.user.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 吐槽，评论表
 * @author: LGG
 * @create: 2019-05-23
 **/
@ApiModel(value="TweetsComment", description="吐槽，评论表")
@Getter
@Setter
public class TweetsComment extends BasePojo implements Serializable {

	/**
	 * 前台搜索参数
	 */
	@TableField(exist = false)
	private String nickName;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id; // 吐槽评论ID

    @NotNull(message="评论内容不能为空")
    private String content; // 评论内容

    private User user;

    private String userId; // 评论人ID

    private Long likeNum; // 点赞数

    private Integer isVisible; // 是否可见

    private String tweetsId; // 吐槽表id

    private String parentId; // 父级评论

}