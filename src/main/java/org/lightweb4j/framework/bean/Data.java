package org.lightweb4j.framework.bean;

/**
 * 返回数据对象
 * @Author benxin_lei
 * @Date 2020-08-29 11:04
 * @Version 1.0.0
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel(){
        return model;
    }
}
