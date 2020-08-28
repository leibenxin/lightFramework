package org.lightweb4j.framework.helper;

import org.lightweb4j.framework.utils.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author benxin_lei
 * @Date 2020-08-28 18:21
 * @Version 1.0.0
 */
public final class BeanHelper {

    /**
     * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet){
            Object object = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, object);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:" + cls);
        }

        return (T) BEAN_MAP.get(cls);
    }
}
