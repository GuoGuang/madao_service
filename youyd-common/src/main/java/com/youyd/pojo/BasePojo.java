package com.youyd.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 基础实体类,其他实体应继承此实体
 * @author: LGG
 * @create: 2018-09-27 14:19
 **/
public class BasePojo implements Serializable {

	private Date updateTime;
	private Date createTime;

	@TableField(exist = false)
	private long page;
	@TableField(exist = false)
	private long size;

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
