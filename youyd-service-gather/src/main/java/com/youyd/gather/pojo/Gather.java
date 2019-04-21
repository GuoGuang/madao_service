package com.youyd.gather.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动板块:活动表
 * @author LGG
 * @create 2019-03-07
 **/
@Getter
@Setter
@ApiModel(value="gather", description="活动类")
public class Gather extends BasePojo implements Serializable {

	@TableId(type = IdType.ID_WORKER_STR)
    private String id;//编号
    private String name;//活动名称
    private String summary;//大会简介
    private String detail;//详细说明
    private String sponsor;//主办方
    private String image;//活动图片
    private Date startTime;//开始时间
    private Date endTime;//截止时间
    private String address;//举办地点
    private Date enrollTime;//报名截止
    private String state;//是否可见
    private String city;//城市
}