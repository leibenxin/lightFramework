package org.lightweb4j.framework.utils;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author benxin_lei
 * @Date 2020-08-26 23:44
 * @Version 1.0.0
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream inputStream = null;
        try {
            // 读取properties文件
            inputStream = Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(fileName);

            if (inputStream == null){
                throw new FileNotFoundException(fileName + "file is not found");
            }
            props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                }catch (IOException ex){
                    LOGGER.error("close inputstream failure", ex);
                }
            }
        }


        return props;
    }

    public static String getString(Properties props, String key){
        return getString(props, key, "");
    }

    /**
     * 从Properties对象中获取字符串值
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties props, String key, String defaultValue){
        String value = defaultValue;
        if (props.containsKey(key)){
            value = props.getProperty(key);
        }

        return value;
    }

    public static int getInt(Properties props, String key){
        return getInt(props, key, 0);
    }

    public static int getInt(Properties props, String key, int defaultValue){
        int value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty(key));
        }

        return value;
    }

    public static boolean getBoolean(Properties props, String key){
        return getBoolean(props, key, false);
    }

    public static boolean getBoolean(Properties props, String key, Boolean defaultValue){
        boolean value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castBoolean(props.getProperty(key));
        }

        return value;
    }
}
