package org.lightweb4j.framework.helper;

import org.lightweb4j.framework.annotation.Inject;
import org.lightweb4j.framework.utils.ArrayUtil;
import org.lightweb4j.framework.utils.CollectionUtil;
import org.lightweb4j.framework.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入帮助类
 * @Author benxin_lei
 * @Date 2020-08-28 18:54
 * @Version 1.0.0
 */
public final class IocHelper {

    static {
        // 获取所有的Bean与Bean实例之间的映射关系（简称为Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            // 便利Bean map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 从BeanMap中获取Bean类和bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类定义的所有成员变量（简称Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)){
                    // 遍历Bean Field
                    for (Field beanField : beanFields){
                        // 判断当前Bean Field是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
