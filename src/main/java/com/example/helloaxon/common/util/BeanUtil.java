package com.example.helloaxon.common.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存后拷贝性能等同setter
 */
public class BeanUtil {
    /**
     * BeanCopier缓存
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new HashMap<>();

    /**
     * 使用属性类型转换器会降低效率,所以source与target要提前保证属性类型一致
     * BeanCopier的copy
     */
    public static <T> T copy(Object source, T target) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        BEAN_COPIER_CACHE.computeIfAbsent(
                sourceClass.getName() + targetClass.getName(),
                mixName -> BeanCopier.create(sourceClass, targetClass, false)
        ).copy(source, target, null);
        return target;
    }

}
