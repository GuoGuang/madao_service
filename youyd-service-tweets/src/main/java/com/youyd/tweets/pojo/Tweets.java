package com.youyd.tweets.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 吐槽表
 * @author: LGG
 * @create: 2019-03-01
 **/
@Getter
@Setter
public class Tweets extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
    private String id; // 吐槽表ID

    private String content; // 吐槽内容

    private Integer userId; // 发布人ID

    private String nickName; // 发布人昵称

    private Long visitsCount; // 浏览量

    private Long thumbUpCount; // 点赞数

    private Long shareCount; // 分享数

    private Long replyCount; // 回复数

    private Integer isVisible; // 是否可见
}