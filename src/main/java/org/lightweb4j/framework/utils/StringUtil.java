package org.lightweb4j.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author benxin_lei
 * @Date 2020-08-27 0:25
 * @Version 1.0.0
 */
public final class StringUtil {

    public static boolean isEmpty(String str){
        if (str != null){
            str = str.trim();
        }

        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 按指定格式分割字符串
     * @param source
     * @param regex
     * @return
     */
    public static String[] splitString(String source, String regex){
        return source.split(regex);
    }
}
