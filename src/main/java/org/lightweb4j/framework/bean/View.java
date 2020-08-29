package org.lightweb4j.framework.bean;

import java.util.Map;

/**
 * 返回视图对象
 * @Author benxin_lei
 * @Date 2020-08-29 11:01
 * @Version 1.0.0
 */
public class View {

    /**
     * 视图对象路径
     */
    private String path;

    private Map<String, Object> model;

    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }

    public String getPath(){
        return path;
    }

    public Map<String, Object> getModel(){
        return model;
    }
}
