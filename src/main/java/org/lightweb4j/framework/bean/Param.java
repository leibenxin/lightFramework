package org.lightweb4j.framework.bean;

import org.lightweb4j.framework.utils.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 * @Author benxin_lei
 * @Date 2020-08-29 10:52
 * @Version 1.0.0
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取int类型参数值
     * @param name
     * @return
     */
    public int getInt(String name){
        return CastUtil.castInt(paramMap.get(name));
    }

    /**
     * 根据参数名获取long类型参数值
     * @param name
     * @return
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 根据参数名获取double类型参数值
     * @param name
     * @return
     */
    public double getDouble(String name){
        return CastUtil.castDouble(paramMap.get(name));
    }

    /**
     * 根据参数名获取boolean类型参数值
     * @param name
     * @return
     */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(paramMap.get(name));
    }

    /**
     * 根据参数名获取String类型参数值
     * @param name
     * @return
     */
    public String getString(String name){
        return CastUtil.castString(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getMap(){
        return paramMap;
    }
}
