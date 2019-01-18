package com.youyd.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 公共代码工具类
 * @author: LGG
 * @create: 2018-09-27 14:53
 **/
public class CodeCommonUtil {

	public static List<String> deletePart(String ids){
		List<String> delIds = new LinkedList<>();
		if (ids.contains(",")) {
			String[] split = ids.split(",");
			for (String id : split) {
				delIds.add(id);
			}
		}else {
			delIds.add(ids);
		}
		return delIds;
	}
}
