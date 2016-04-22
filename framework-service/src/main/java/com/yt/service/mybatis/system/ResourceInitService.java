package com.yt.service.mybatis.system;

import com.yt.entity.mybatis.AuthGroup;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 初始化service,用来加载baseService
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system
 * @date 2016/4/22 0022 13:52
 * @descption: 疯狂的王麻子团队!
 */
public interface ResourceInitService {

    /**
     * 获取每个后台controller,保存权限的自动保存权限
     *
     * @param clazz
     */
    public void registResourceByClass(Class<?> clazz);


}
