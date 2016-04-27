package com.yt.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring上下文工具类,在applicationContext.xml文件里面注册下这个工具类就可以用了
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.util.sessionutil
 * @date 2016/4/26 0026 17:47
 * @descption: 疯狂的王麻子团队!
 */
public class SpringUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static Object getBean(Class clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据注入名称
     *
     * @param ServiceName
     * @return
     */
    public static Object getBean(String ServiceName) {
        return applicationContext.getBean(ServiceName);
    }

    /**
     * 根据注入名称和类名
     *
     * @param clazz
     * @param ServiceName
     * @return
     */
    public static Object getBean(Class clazz, String ServiceName) {
        return applicationContext.getBean(ServiceName, clazz);
    }
}
