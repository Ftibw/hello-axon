package com.example.helloaxon.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * json字符与对像转换
 *
 * @author ftibw
 */
@Slf4j(topic = "jackson_utils")
public final class JacksonUtils {

    /**
     * 线程安全类,可全局使用
     */
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 想包含null,就使用spring容器中的ObjectMapper
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略不存在的属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JacksonUtils() {
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * UserInfo[] ua = readValue(json,UserInfo[].class)
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueType);
        } catch (Exception e) {
            log.info("json反序列化错误: " + e.getMessage());
        }
        return null;
    }

    /**
     * json字符串转带泛型的对象
     */
    public static <T> T readGenericTypeValue(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            log.info("json反序列化错误: " + e.getMessage());
        }
        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     */
    public static String toJson(Object object) {
        if (null == object) {
            return "";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.info("json反序列化错误: " + e.getMessage());
        }
        return "";
    }

    /**
     * 把JavaBean写入文件
     */
    public static boolean writeToFile(File file, Object object) {
        if (null == file) {
            return false;
        }
        if (null == object) {
            return false;
        }
        try {
            OBJECT_MAPPER.writeValue(file, object);
            return true;
        } catch (Exception e) {
            log.info("json反序列化错误: " + e.getMessage());
        }
        return false;
    }

    /**
     * 从文件中读取json转换为JavaBean
     */
    public static <T> T readFromFile(File file, Class<T> clazz) {
        if (null == file || !file.exists()) {
            return null;
        }
        if (null == clazz) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(file, clazz);
        } catch (Exception e) {
            log.info("json反序列化错误: " + e.getMessage());
        }
        return null;
    }

    /**
     * 从文件中读取json转换为带泛型的JavaBean
     */
    public static <T> T readFromFile(File file, TypeReference<T> valueTypeRef) {
        if (null == file || !file.exists()) {
            return null;
        }
        if (null == valueTypeRef) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(file, valueTypeRef);
        } catch (Exception e) {
            log.info("json反序列化错误: " + e.getMessage());
        }
        return null;
    }

}
