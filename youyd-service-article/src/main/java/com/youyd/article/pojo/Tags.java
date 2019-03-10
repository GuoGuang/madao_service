package com.youyd.article.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.youyd.pojo.BasePojo;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value="article", description="标签类")
public class Tags extends BasePojo implements Serializable {

    /**
     * ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String slug;

    /**
     * 描述
     */
    private String description;

    /**
     * 标签图标
     */
    private String icon;

    /**
     * 状态
     */
    private String state;


	/**
	 * 接收连接查询结果专用字段
	 */
	@TableField(exist=false)
	private Integer tagsCount;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
	public Integer getTagsCount() {
		return tagsCount;
	}

	public void setTagsCount(Integer tagsCount) {
		this.tagsCount = tagsCount;
	}
}