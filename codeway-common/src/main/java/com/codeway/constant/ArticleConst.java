package com.codeway.constant;


/**
 * <p>文章模块 业务相关常量</p>
 * @version 1.0.0
 */
public final class ArticleConst {

	public static final String SORT_TYPE_HOT  = "2"; // 热序
	public static final String SORT_TYPE_DESC  = "-1"; // 降序
	public static final String SORT_TYPE_ASC  = "1"; // 升序

	// 文章审核状态
	public static final Integer PASS  = 1; // 热序
	public static final Integer UNDER_REVIEW  = 2; // 降序
	public static final Integer REFUSE  = 3; // 升序

	private ArticleConst(){}
}
