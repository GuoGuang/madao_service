package com.youyd.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 封装的查询参数类
 * @author: LGG
 * @create: 28-February-2019
 **/

@Getter
@Setter
public class QueryVO implements Serializable {

	@TableField(exist = false)
	private long pageNum;

	@TableField(exist = false)
	private long pageSize = 10;

	@TableField(exist = false)
	private String searchValue;

	@TableField(exist = false)
	private String searchSort;

}
