package com.madao.user.config;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
public class WsSessionManager {

    /**
     * 保存连接 session
     */
    private static ConcurrentHashMap<String, SocketIOClient> SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 添加 session
     *
     * @param key
     */
    public static void add(String key, SocketIOClient session) {
        // 添加 session
        SESSION_POOL.put(key, session);
    }

    public static List<SocketIOClient> getAllSession() {
		return new ArrayList<>(SESSION_POOL.values());
    }

    /**
     * 删除 session,会返回删除的 session
     *
     * @param key
     * @return
     */
    public static SocketIOClient remove(String key) {
        return SESSION_POOL.remove(key);
    }

    /**
     * 删除并同步关闭连接
     *
     * @param key
     */
    public static void removeAndClose(String key) {
	    SocketIOClient session = remove(key);
        if (session != null) {
            session.disconnect();
        }
    }

    /**
     * 获得 session
     *
     * @param key
     * @return
     */
    public static SocketIOClient get(String key) {
        // 获得 session
        return SESSION_POOL.get(key);
    }
}
