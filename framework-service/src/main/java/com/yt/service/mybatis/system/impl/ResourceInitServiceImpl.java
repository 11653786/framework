package com.yt.service.mybatis.system.impl;

import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.service.mybatis.system.AuthGroupService;
import com.yt.service.mybatis.system.AuthService;
import com.yt.service.mybatis.system.ResourceInitService;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.yt.annotation.system.ResourceAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.mybatis.system.impl
 * @date 2016/4/22 0022 13:54
 * @descption: 疯狂的王麻子团队!
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ResourceInitServiceImpl implements ResourceInitService {


    @Autowired
    private AuthGroupService authGroupService;
    @Autowired
    private AuthService authService;

    public void registResourceByClass(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        ResourceAnnotation classRes = clazz.getAnnotation(ResourceAnnotation.class);

        AuthGroup group = this.authGroupService.selectByName(classRes.resourceGroup());
        if (EmptyUtil.isEmpty(group)) {
            group = new AuthGroup();
            group.setAuthGroupName(classRes.resourceGroup());
        }
        List<Method> needRetryMethods = registResource(Arrays.asList(methods), group);
        if (EmptyUtil.isNotEmpty(needRetryMethods) && needRetryMethods.size() > 0) {
            for (int i = 0; i < 5; i++) {
                needRetryMethods = registResource(Arrays.asList(methods), group);
            }
        }
    }


    private List<Method> registResource(List<Method> methods, AuthGroup group) {
        List<Method> needRetryMethods = new ArrayList<Method>();
        for (Method method : methods) {
            ResourceAnnotation resourceAnnotation = method.getAnnotation(ResourceAnnotation.class);
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (EmptyUtil.isNotEmpty(requestMapping) && EmptyUtil.isNotEmpty(resourceAnnotation)) {
                //先判断是否存在了再做操作,根据名称查询权限
                Auth existsAuth = this.authService.selectByName(resourceAnnotation.name());
                Integer pid = -1;
                if (EmptyUtil.isEmpty(existsAuth)) {
                    existsAuth = new Auth(resourceAnnotation.url(), resourceAnnotation.name(), resourceAnnotation.resourceType(), resourceAnnotation.remark());
                    existsAuth.setIsEnable("1");
                    //父级
                    Auth parentAuth = this.authService.selectByName(resourceAnnotation.pName());
                    if (EmptyUtil.isEmpty(parentAuth)) {
                        if (resourceAnnotation.parentIsRoot()) {
                            parentAuth = new Auth();
                            parentAuth.setAuthName(resourceAnnotation.pName());
                            parentAuth.setAuthDesc(resourceAnnotation.pName());
                            //菜单类型
                            parentAuth.setAuthType("3");
                            parentAuth.setIsEnable("1");
                            pid = (Integer) this.authService.saveAuth(parentAuth).getObj();
                        } else {
                            needRetryMethods.add(method);
                            continue;
                        }
                    } else {
                        existsAuth.set_parentId(parentAuth.getId());
                    }

                    try {
                        this.authService.saveAuth(existsAuth);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }



            }
        }
        return needRetryMethods;
    }
}
