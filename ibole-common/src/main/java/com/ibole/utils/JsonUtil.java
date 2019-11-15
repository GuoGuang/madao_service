package com.ibole.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibole.pojo.JsonException;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * <p>对Jackson进行一层封装</p>
 */
public class JsonUtil {

	private JsonUtil(){}

	private static ObjectMapper objectMapper;
	private static final String JACKSON_ERROR = "Jackson序列化异常！";
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
				throw new JsonException(JACKSON_ERROR);
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
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			LogBack.error("json error:" + e.getMessage());
			throw new JsonException(JACKSON_ERROR);
		}
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
			throw new JsonException(JACKSON_ERROR);
		}
	}
	/**
	 * JSON字符串转java对象
	 * @param jsonStr JSON字符串
	 * @return java对象
	 */
	public static synchronized Map<String,Object> jsonToMap(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (IOException e) {
			LogBack.error("json error:" + e.getMessage());
			throw new JsonException(JACKSON_ERROR);
		}
	}

	/**
	 * 判断是否json串
	 * @param jsonStr JSON字符串
	 * @return java对象
	 */
	public static synchronized boolean isJson(String jsonStr) {
		try {
			objectMapper.readTree(jsonStr);
			return true;
		} catch (IOException e) {
			LogBack.info("非json串");
			return false;
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
			LogBack.error("json error:" + e.getMessage());
			throw new JsonException(JACKSON_ERROR);
		}

	}

	public static byte[] toJSONBytes(JsonData result) throws IOException {
		return objectMapper.writeValueAsBytes(result);
	}
}
