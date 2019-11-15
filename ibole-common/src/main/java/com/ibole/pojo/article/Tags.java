package com.ibole.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ibole.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ApiModel(value="article", description="标签类")
public class Tags extends BasePojo implements Serializable {

	/**
	 * 接收连接查询结果专用字段
	 */
	@TableField(exist=false)
	private Integer tagsCount;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id; // ID

    @NotNull(message="标签名称不能为空")
    private String name; // 标签名称

    @NotNull(message="英文名称不能为空")
    private String slug; // 英文名称

    @NotNull(message="描述不能为空")
    private String description;

    private String icon; // 标签图标

    private String state; // 状态


}