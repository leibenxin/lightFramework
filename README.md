# lightFramework

## 前言
lightFramework项目不是为了重复造轮子，仅用于学习java web框架的实现原理，以及底层所使用的技术。

## 项目地址
Github地址：https://github.com/leibenxin/lightFramework

## 项目介绍
lightFramework项目是一个轻量级的mvc框架，具有IOC、DI以及AOP等常用功能。
基于此，实现数据库的事务管理功能，并集成Shiro安全控制框架，实现认证、授权以及会话管理等功能。


## 使用介绍
1、首先在idea中新建一个maven项目，添加如下的依赖：

```xml
<dependencies>
    <dependency>
        <groupId>org.lightweb4j</groupId>
        <artifactId>lightFramework</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

2、在resources目录下添加**light.properties**文件，文件内容如下：

```properties
#数据库管理相关
#数据库驱动
light.framework.jdbc.driver=com.mysql.jdbc.Driver
#数据库url
light.framework.jdbc.url=jdbc:mysql://localhost:3306/demo
#数据库用户名
light.framework.jdbc.username=root
#数据库密码
light.framework.jdbc.password=root

#框架相关
#项目包名，用户扫描包，实现IOC、DI、以及AOP等功能，需指定
light.framework.app.base_package=org.light4j.webFramwork
#视图地址，可根据实际需求进行修改
light.framework.app.jsp_path=/WEB-INF/view/
#静态资源地址
light.framework.app.asset_path=/asset/
```

3、web功能测试：

```java
package org.light4j.webFramwork.controller;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;
import org.lightweb4j.framework.annotation.Action;
import org.lightweb4j.framework.annotation.Controller;
import org.lightweb4j.framework.bean.Param;
import org.lightweb4j.framework.bean.View;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @Author benxin_lei
 * @Date 2020-08-29 16:58
 * @Version 1.0.0
 */
@Controller
public class CustomerController {

    @Action("get:/customer")
    public View index(Param param){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("123", 123);
        return new View("customer_create.jsp", model);
    }
}
```

1)在类上使用Controller注解，在方法上使用Action注解，用于标注请求地址。仅需这两个注解即可实现简单的web功能。

## 项目进度

2020/08/30：实现了Java Web功能以及IOC和DI等部分功能。





**注：**本项目主要借鉴了《**架构探险 从零开始写Java Web框架**》。