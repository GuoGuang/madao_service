package com.youyd.question.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @description: 问题表
 * @author: LGG
 * @create: 2019-02-26 16:21
 **/

@ApiModel(value="question", description="文章类")
@Getter
@Setter
public class Question extends BasePojo implements Serializable {

	/**
	 * ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String title;//标题
    private String content;//内容
    private Integer tagsId;//问题标签表
    private String userId;//用户ID
    private String nickName;//昵称
    private Long visits;//浏览量
    private Long thumbUp;//点赞数
    private Long reply;//回复数
    private String solve;//是否解决
    private String replyName;//回复人昵称
    private Date replyTime;//回复日期

	@TableField(exist=false)
    private ArrayList<Answers> answers; // 答案
}