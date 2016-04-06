package com.yt.base;

import com.yt.entity.mybatis.Employee;
import com.yt.util.dhqjr.EmptyUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2015/9/8.
 */
public class BaseAction {

    protected static String loginEmployee = "loginEmployee";
    protected static String loginUser = "loginUser";
    protected static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat(dateFormat);
        //true:允许输入空值，false:不能为空值
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);


    }


    protected void setSessionEmployee(HttpSession session, Employee employee) {
        session.setAttribute(loginEmployee, employee);
    }

    protected void setSessionUser(HttpSession session, Employee employee) {
        session.setAttribute(loginUser, employee);
    }

    protected static Employee getSessionUser(HttpSession session) {
        if (EmptyUtil.isEmpty(session.getAttribute(loginUser))) {
            return null;
        }
        return (Employee) session.getAttribute(loginUser);
    }


    protected static Employee getSessionEmployee(HttpSession session) {
        if (EmptyUtil.isEmpty(session.getAttribute(loginEmployee))) {
            return null;
        }
        return (Employee) session.getAttribute(loginEmployee);
    }


    /**
     * 传递过来的reuqest参数转成map
     *
     * @param request
     * @return
     */
    public Map<String, Object> getParameters(HttpServletRequest request) {
        if (request == null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        Map req = request.getParameterMap();
        Map<String, Object> p = new HashMap<String, Object>();
        if ((req != null) && (!req.isEmpty())) {

            Collection keys = req.keySet();
            for (Iterator i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                Object value = req.get(key);
                Object v = null;
                if ((value.getClass().isArray())
                        && (((Object[]) value).length > 0)) {
                    v = ((Object[]) value)[0];
                } else {
                    v = value;
                }
                if ((v != null) && ((v instanceof String))) {
                    String s = (String) v;
                    if (s.length() > 0) {
                        if (key.equalsIgnoreCase("page") || key.equalsIgnoreCase("pageSize")) {
                            p.put(key, Integer.valueOf(s));
                        } else {
                            p.put(key, s);
                        }

                    }
                }
            }
            //读取cookie
            p.putAll(ReadCookieMap(request));
            return p;
        }
        return p;
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    public static Map<String, String> ReadCookieMap(HttpServletRequest request) {
        if (request == null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }


}

