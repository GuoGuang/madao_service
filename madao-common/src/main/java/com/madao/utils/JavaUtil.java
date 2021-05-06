package com.madao.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * java原生 工具类
 */
public class JavaUtil {

    /**
     * 获取该类和所有父类的全部字段
     * @param object 对象
     */
    public static List<String> getAllFields(Object object) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<Field> fieldList = new ArrayList<>();
        Class tempClass = object.getClass();
        while (tempClass != null && tempClass.getName().getClass() instanceof java.lang.Object) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        for (Field objField : fieldList) {
            objField.setAccessible(true);
            String name = objField.getName();
            arrayList.add(name);
        }
        return arrayList;
    }
}
