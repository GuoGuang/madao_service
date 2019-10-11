package com.youyd.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description 封装的查询参数类
 * @author LGG
 * @create 28-February-2019
 **/

@Getter
@Setter
public class QueryVO implements Serializable {

	private long pageNum = 1;

	private long pageSize = 10000;

	private String searchValue;
	private String searchSort;

	/*排序*/
	private Boolean orderBy;
	private String fieldSort;

	private String sortType;


}
