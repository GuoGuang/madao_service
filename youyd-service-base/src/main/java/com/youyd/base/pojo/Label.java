package com.youyd.base.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 标签实体类
 * @author: LGG
 * @create: 2018-09-26 15:55
 **/
public class Label extends BasePojo  implements Serializable {

	private String id;
	private String labelName;//标签名称
	private String state;//状态
	private Long count;//使用数量
	private Long fans;//关注数
	private String recommend;//是否推荐

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getFans() {
		return fans;
	}

	public void setFans(Long fans) {
		this.fans = fans;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
}