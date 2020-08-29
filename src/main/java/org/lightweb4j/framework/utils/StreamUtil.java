package org.lightweb4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 * @Author benxin_lei
 * @Date 2020-08-29 11:09
 * @Version 1.0.0
 */
public final class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception ex){
            LOGGER.error("get string failure", ex);
            throw new RuntimeException(ex);
        }

        return sb.toString();
    }
}
