package com.yt.base;

import com.yt.service.mybatis.system.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 继承了InitializingBean,这个类会在初始化的时候启动
 * Created by Administrator on 2016/3/24 0024.
 */
public class ResourceBaseController extends BaseAction implements InitializingBean {


    @Value("${system.initResource}")
    private String initResource;
    @Autowired
    private AuthService authService;

    /**
     * 处理resource资源信息
     */
    public void afterPropertiesSet() throws Exception {
        if ("yes".equals(initResource)) {
            System.out.println("资源扫描: " + this.getClass().getSimpleName());
        }

    }


}
