package org.lightweb4j.framework.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author benxin_lei
 * @Date 2020-08-27 22:15
 * @Version 1.0.0
 */
public final class CollectionUtil {

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合是否非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断map是否非空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map){
        return isEmpty(map);
    }
}
