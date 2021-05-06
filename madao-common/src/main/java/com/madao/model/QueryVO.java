package com.madao.model;

import java.io.Serializable;

/**
 * 封装的查询参数类
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class QueryVO implements Serializable {
    private long pageNum = 0;

    private long pageSize = 10000;
    private String keyword;
    private String searchSort;

    /*排序*/
    private Boolean orderBy;
    private String fieldSort;

    private String sortType;


    public long getPageNum() {
        return pageNum == 0 ? 0 : (pageNum - 1) * 10;
    }

	public QueryVO setPageNum(long pageNum) {
		this.pageNum = pageNum;
		return this;
	}

	public long getPageSize() {
		return pageSize;
	}

	public QueryVO setPageSize(long pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public String getKeyword() {
		return keyword;
	}

	public QueryVO setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}

	public String getSearchSort() {
		return searchSort;
	}

	public QueryVO setSearchSort(String searchSort) {
		this.searchSort = searchSort;
		return this;
	}

	public Boolean getOrderBy() {
		return orderBy;
	}

	public QueryVO setOrderBy(Boolean orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public String getFieldSort() {
		return fieldSort;
	}

	public QueryVO setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
		return this;
	}

	public String getSortType() {
		return sortType;
	}

	public QueryVO setSortType(String sortType) {
		this.sortType = sortType;
		return this;
	}
}
