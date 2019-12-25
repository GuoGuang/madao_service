package com.ibole.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 封装的查询参数类
 **/

@Getter
@Setter
public class QueryVO implements Serializable {
    private long pageNum = 0;

    private long pageSize = 10000;
    private String searchValue;
    private String searchSort;

    /*排序*/
    private Boolean orderBy;
    private String fieldSort;

    private String sortType;


	public long getPageNum() {
		return (pageNum - 1) * 10;
	}
}
