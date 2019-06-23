package com.youyd.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * <p>对Jackson进行一层封装</p>
 */
public class JsonUtil {

	private JsonUtil(){}

	private static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * JSON字符串转换为Java泛型对象
	 * 例1：String jsonStr = "[{\"id\":\"1234\",\"account\":\"admin\"}]";
	 * List<UserInfo> list = JsonUtil.json2GenericObject(jsonStr, new TypeReference<List<UserInfo>>() {});
	 * 例2：String jsonStr = "[\"1111\",\"2222\",\"3333\"]";
	 * List<String> list = JsonUtil.json2GenericObject(jsonStr, new TypeReference<List<String>>() {});
	 * @param <T> 转换泛型
	 * @param jsonString JSON字符串
	 * @param tr 需要转换的对象类型
	 * @return Java泛型对象
	 */
	public static synchronized <T> T jsonToGenericObject(String jsonString, TypeReference<T> tr) {
		if (jsonString != null && !("".equals(jsonString))) {
			try {
				return (T) (tr.getType().equals(String.class) ? jsonString : objectMapper.readValue(jsonString, tr));
			} catch (Exception e) {
				LogBack.error("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Java对象转Json字符串
	 * @param object Java对象，可以是对象，数组，List,Map等
	 * @return json 字符串
	 */
	public static synchronized String toJsonString(Object object) {
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			LogBack.error("json error:" + e.getMessage());
		}
		return jsonString;
	}

	/**
	 * JSON字符串转java对象
	 * @param <T> 转换泛型
	 * @param jsonStr JSON字符串
	 * @param clazz 类型
	 * @return java对象
	 */
	public static synchronized <T> T jsonToPojo(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			LogBack.error("json error:" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * JSON字符串转java对象
	 * @param jsonStr JSON字符串
	 * @return java对象
	 */
	public static synchronized Map<String,Object> jsonToMap(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
		} catch (IOException e) {
			LogBack.error("json error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * json转为map
	 * @param jsonData JSON字符串
	 * @return
	 */
	public static List<Map<String,Object>> jsonToListMap(String jsonData) {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Map.class);
		try {
			List<Map<String,Object>> list = objectMapper.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static byte[] toJSONBytes(JsonData result) throws IOException {
		return objectMapper.writeValueAsBytes(result);
	}
}
