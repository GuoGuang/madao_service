package com.youyd.question.pojo;

import com.youyd.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 回答表
 */
@Getter
@Setter
public class Answers extends BasePojo implements Serializable {
    private String id; // 答案表id
    private Integer questionId;// 问题表ID
    private String content;// 答案内容
    private Integer userId;// 回答人ID
    private String nickName;// 回答人昵称

}