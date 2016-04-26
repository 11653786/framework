package com.yt.util.sessionutil;

import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.Employee;
import com.yt.util.dhqjr.EmptyUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.util.sessionutil
 * @date 2016/4/22 0022 9:55
 * @descption: 疯狂的王麻子团队!
 */
public class EmployeeSessionUtil {

    //后台登录帐号
    public static String loginEmployee = "loginEmployee";
    public static String loginUser = "loginUser";
    //后台登录帐号权限
    public static String loginAuth = "loginAuth";

    /**
     * 后台保存登录权限
     *
     * @param session
     * @param auths
     */
    public static void setEmployeeAuth(HttpSession session, List<Auth> auths) {
        session.setAttribute(loginAuth, auths);
    }

    public static List<Auth> getEmployeeAuth(HttpSession session) {
        List<Auth> auths = null;
        if (!EmptyUtil.isEmpty(session.getAttribute(loginAuth))) {
            auths = (List<Auth>) session.getAttribute(loginAuth);
        }
        return auths;
    }

    /**
     * 后台登录保存用户
     *
     * @param session
     * @param employee
     */
    public static void setSessionEmployee(HttpSession session, Employee employee) {
        session.setAttribute(loginEmployee, employee);
    }

    public void setSessionUser(HttpSession session, Employee employee) {
        session.setAttribute(loginUser, employee);
    }

    public static Employee getSessionUser(HttpSession session) {
        if (EmptyUtil.isEmpty(session.getAttribute(loginUser))) {
            return null;
        }
        return (Employee) session.getAttribute(loginUser);
    }


    public static Employee getSessionEmployee(HttpSession session) {
        if (EmptyUtil.isEmpty(session.getAttribute(loginEmployee))) {
            return null;
        }
        return (Employee) session.getAttribute(loginEmployee);
    }
}
