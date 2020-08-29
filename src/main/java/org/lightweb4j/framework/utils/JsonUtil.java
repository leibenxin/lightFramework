package org.lightweb4j.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类
 * @Author benxin_lei
 * @Date 2020-08-29 11:17
 * @Version 1.0.0
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO类转换成JSON字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        }catch (Exception ex){
            LOGGER.error("convert POJO to JSON failure", ex);
            throw new RuntimeException(ex);
        }

        return json;
    }

    /**
     * 将JSON字符串转换成POJO对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T formJson(String json, Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        }catch (Exception ex){
            LOGGER.error("convert JSON to POJO failure", ex);
            throw new RuntimeException(ex);
        }

        return pojo;
    }
}
