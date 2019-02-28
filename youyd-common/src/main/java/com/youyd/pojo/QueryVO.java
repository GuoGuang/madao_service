package com.youyd.pojo;

import java.io.Serializable;

/**
 * @description: 封装的查询参数类
 * @author: LGG
 * @create: 28-February-2019
 **/

public class QueryVO implements Serializable {

	private long page;
	private long limit;
	private String searchValue;
	private String searchSort;

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchSort() {
		return searchSort;
	}

	public void setSearchSort(String searchSort) {
		this.searchSort = searchSort;
	}
}
