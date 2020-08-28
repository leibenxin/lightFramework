package org.lightweb4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author benxin_lei
 * @Date 2020-08-28 18:10
 * @Version 1.0.0
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建新实例
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        }catch (Exception ex){
            LOGGER.error("new instance failure", ex);
            throw new RuntimeException(ex);
        }

        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch(Exception ex){
            LOGGER.error("Invoke Method failure", ex);
            throw new RuntimeException(ex);
        }

        return result;
    }

    /**
     * 设置字段值
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
