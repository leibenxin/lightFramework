package org.lightweb4j.framework;

import org.lightweb4j.framework.helper.BeanHelper;
import org.lightweb4j.framework.helper.ClassHelper;
import org.lightweb4j.framework.helper.ControllerHelper;
import org.lightweb4j.framework.helper.IocHelper;
import org.lightweb4j.framework.utils.ClassUtil;

/**
 * 加载相应的Helper类，初始化框架
 * @Author benxin_lei
 * @Date 2020-08-28 19:45
 * @Version 1.0.0
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> helper : classList){
            ClassUtil.loadClass(helper.getName());
        }
    }
}
