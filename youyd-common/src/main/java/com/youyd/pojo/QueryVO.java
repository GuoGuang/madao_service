package com.youyd.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 封装的查询参数类
 * @author: LGG
 * @create: 28-February-2019
 **/

@Data
public class QueryVO implements Serializable {

	private long page;
	private long limit = 10;
	private String searchValue;
	private String searchSort;

}
