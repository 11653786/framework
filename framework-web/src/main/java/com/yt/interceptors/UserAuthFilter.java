package com.yt.interceptors;

import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.Employee;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.sessionutil.EmployeeSessionUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.interceptors
 * @date 2016/4/22 0022 10:06
 * @descption: 疯狂的王麻子团队!
 */
public class UserAuthFilter {

    /**
     * @param session
     * @param url        当前url
     * @param parentUrls 判断当前类里面有没有提交url
     * @param auths      权限
     * @return
     */
    public static boolean isPass(HttpSession session, String url, List<Auth> auths, String... parentUrls) {
        boolean result = false;
        result = checkAuth(session, url, auths, parentUrls);
        return result;
    }

    @SuppressWarnings("unchecked")
    private static boolean checkAuth(HttpSession session, String url, List<Auth> auths, String... parentUrls) {
        Employee employee = EmployeeSessionUtil.getSessionEmployee(session);
        if (employee != null) {
            if ("admin".equals(employee.getUserName())) {
                return true;
            }

            //如果没有就初始化
            if (EmptyUtil.isEmpty(auths)) {
                auths = new ArrayList<Auth>();
            }

            List<String> employeeUrls = new ArrayList<String>();
            for (Auth auth : auths
                    ) {
                if (EmptyUtil.isNotEmpty(auth.getAuthUrl())) {
                    employeeUrls.add(auth.getAuthUrl());
                }
            }
            if (employeeUrls.contains(url)) {
                return true;
            }

            if ((parentUrls != null && parentUrls.length != 0)) {
                for (int i = 0; i < parentUrls.length; i++) {
                    if (employeeUrls.contains(parentUrls[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
