package com.youyd.constant;


/**
 * 公共模块常量
 * @author LGG
 * @create 2019年4月23日22:35:18
 * @version 1.0.0
 */
public final class CommonConst {

	/* 常量禁止实例化 */
	private CommonConst() {
		throw new IllegalStateException("Constant prohibition instantiation!");
	}

	public static final int ADD = 1;
	public static final Integer DELETE= 2;
	public static final Integer MODIFY= 3;
}
