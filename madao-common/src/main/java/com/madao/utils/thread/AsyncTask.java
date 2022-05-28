package com.madao.utils.thread;

import java.util.UUID;

/**
 * 异步线任务
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-05-23 07:37
 */
public interface AsyncTask<T> {

	default String taskName() {
		return UUID.randomUUID().toString();
	}

	T doExecute();
}