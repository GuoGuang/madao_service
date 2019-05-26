package com.youyd.pojo.tweets;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import com.youyd.pojo.user.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

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

    /**
     * 吐槽评论ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论内容
     */
    private User user;

    /**
     * 评论人ID
     */
    private String userId;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * 是否可见
     */
    private Integer isVisible;

    /**
     * 吐槽表id
     */
    private String tweetsId;

    /**
     * 父级评论
     */
    private String parentId;

}