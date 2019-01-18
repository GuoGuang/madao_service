package com.youyd.recruit.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 企业实体类
 * @author: LGG
 * @create: 2018-09-26 15:55
 **/

public class Enterprise extends BasePojo implements Serializable{

	private String id;//ID
	private String name;//企业名称
	private String summary;//企业简介
	private String address;//企业地址
	private String labels;//标签列表
	private String coordinate;//坐标
	private String isHot;//是否热门
	private String logo;//LOGO
	private Integer jobCount;//职位数
	private String url;//URL

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getJobCount() {
		return jobCount;
	}

	public void setJobCount(Integer jobCount) {
		this.jobCount = jobCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
