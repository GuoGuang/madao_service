package com.youyd.pojo.tweets;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 吐槽，推特
 * @author: LGG
 * @create: 2019-05-23
 **/
@ApiModel(value="user", description="吐槽，推特")
@Getter
@Setter
public class Tweets extends BasePojo implements Serializable {

    /**
     * 吐槽表ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 吐槽内容
     */
    private String content;

    /**
     * 发布人ID
     */
    private String userId;

    /**
     * 发布人昵称
     */
    private String nickName;

    /**
     * 浏览量
     */
    private Long visitsCount;

    /**
     * 点赞数
     */
    private Long thumbUpCount;

    /**
     * 分享数
     */
    private Long shareCount;

    /**
     * 是否可见
     */
    private Integer isVisible;
}