package com.madao.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

/**
 * Querydsl工具类
 * 封装常用方法
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class QuerydslUtil {

	/**
	 * 根据字符串字段获取排序类
	 *
	 * @param order：Order.DESC
	 * @param fieldName：排序字段
	 * @return OrderSpecifier
	 * @see https://stackoverflow.com/questions/39576236/dynamic-sorting-in-querydsl
	 */
	public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
		Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
		return new OrderSpecifier(order, fieldPath);
	}

	public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent) {
		return getSortedColumn(order, parent, "createAt");
	}
}
