package com.madao.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * 分布式全局唯一ID
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @created 2022/03/05/ 16:00:00
 */
public class IdGenerator {
	private static final Snowflake snowflake;

	static {
		snowflake = IdUtil.getSnowflake(getWorkId(), getDataCenterId());
	}

	public static long generateId() {
		return snowflake.nextId();
	}

	private static Long getWorkId() {
		try {
			String hostAddress = Inet4Address.getLocalHost().getHostAddress();
			int[] ints = StringUtils.toCodePoints(hostAddress);
			int sums = 0;
			for (int b : ints) {
				sums += b;
			}
			return (long) (sums % 32);
		} catch (UnknownHostException e) {
			return RandomUtils.nextLong(0, 31);
		}
	}

	private static Long getDataCenterId() {
		String hostName = SystemUtils.getHostName();
		if (StringUtils.isEmpty(hostName)) {
			try {
				hostName = Inet4Address.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		int[] ints = StringUtils.toCodePoints(hostName);
		int sums = 0;
		for (int i : ints) {
			sums += i;
		}
		return (long) (sums % 32);
	}
}
