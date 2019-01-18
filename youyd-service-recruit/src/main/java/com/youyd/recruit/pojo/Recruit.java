package com.youyd.recruit.pojo;

import com.youyd.pojo.BasePojo;

import java.io.Serializable;

/**
 * @description: 招聘实体类
 * @author: LGG
 * @create: 2018-09-26 15:55
 **/
public class Recruit extends BasePojo implements Serializable{

	private String id;//ID
	private String jobName;//职位名称
	private String salary;//薪资范围
	private String condition;//经验要求
	private String education;//学历要求
	private String type;//任职方式
	private String address;//办公地址
	private String enterpriseId;//企业ID
	private String state;//状态
	private String url;//网址
	private String label;//标签
	private String jobDescription;//职位描述
	private String jobRequire;//职位要求

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobRequire() {
		return jobRequire;
	}

	public void setJobRequire(String jobRequire) {
		this.jobRequire = jobRequire;
	}
}
