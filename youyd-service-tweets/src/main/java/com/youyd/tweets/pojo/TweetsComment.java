package com.youyd.tweets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 吐槽评论表
 * @author: LGG
 * @create: 2019-03-01
 **/
@Getter
@Setter
public class TweetsComment extends BasePojo implements Serializable {

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
     * 评论人ID
     */
    private Integer userId;

    /**
     * 评论人昵称
     */
    private String nickName;

    /**
     * 点赞数
     */
    private Long thumbUpCount;

    /**
     * 回复数
     */
    private Long replyCount;

    /**
     * 是否可见
     */
    private Integer isVisible;


    /**
     * 吐槽表id
     */
    private Integer tweetsId;
}