package org.lightweb4j.framework.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @Author benxin_lei
 * @Date 2020-08-28 19:00
 * @Version 1.0.0
 */
public final class ArrayUtil {

    /**
     * 判断数组是否非空
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
}
