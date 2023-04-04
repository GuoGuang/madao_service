package com.madao.exception.custom;

/**
 * 达到限流峰值异常
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-05-22 15:45
 */
public class RateLimiterException extends RuntimeException {

	public RateLimiterException(String message) {
		super(message);
	}

	public RateLimiterException() {
		super("访问过于频繁，请稍候再试！");
	}
}