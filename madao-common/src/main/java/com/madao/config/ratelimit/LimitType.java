package com.madao.config.ratelimit;


/**
 * 接口限流类型，支持ip或者全局
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-05-22 15:21
 */
public enum LimitType {
	/**
	 * 默认策略全局限流
	 */
	DEFAULT,
	/**
	 * 根据请求者IP进行限流
	 */
	IP
}