package com.madao.exception.custom;

import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;

/**
 * 自定义异常：RemoteRpcException异常
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class RemoteRpcException extends RuntimeException {

    public RemoteRpcException(JsonData jsonData) {
        super(JsonUtil.toJsonString(jsonData));
    }

    public RemoteRpcException() {
        super();
    }

}
