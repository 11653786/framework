package com.yt.util.sessionutil;

import com.yt.entity.mybatis.Employee;
import com.yt.util.dhqjr.EmptyUtil;

import javax.servlet.http.HttpSession;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.util.sessionutil
 * @date 2016/4/22 0022 9:55
 * @descption: 疯狂的王麻子团队!
 */
public class EmployeeSessionUtil {

    protected static String loginEmployee = "loginEmployee";
    protected static String loginUser = "loginUser";


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
