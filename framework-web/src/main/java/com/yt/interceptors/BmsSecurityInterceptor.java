package com.yt.interceptors;

import com.yt.entity.mybatis.Auth;
import com.yt.entity.mybatis.AuthGroup;
import com.yt.entity.mybatis.Employee;
import com.yt.service.mybatis.system.AuthService;
import com.yt.service.mybatis.system.EmployeeAuthGroupRelationShipService;
import com.yt.util.sessionutil.EmployeeSessionUtil;
import com.yt.util.yt.annotation.system.ParentSecurity;
import com.yt.util.yt.annotation.system.UnSecurity;
import com.yt.util.yt.annotation.system.UnSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 权限拦截器
 *
 * @author liuyijun
 */
public class BmsSecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(BmsSecurityInterceptor.class);

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = this.getRequestUrl(request);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        logger.info("拦截方法名称: " + ((HandlerMethod) handler).getMethod().getName());
        UnSession unSession = handlerMethod
                .getMethodAnnotation(UnSession.class);
        HttpSession session = request.getSession();
        if (unSession != null) {
            return true;
        } else {
            Employee employee = EmployeeSessionUtil.getSessionEmployee(session);
            if (employee == null || employee.getId() == null) {
                request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录！");
                request.getRequestDispatcher("/error/noSession.jsp").forward(
                        request, response);
                return false;
            } else {
                ParentSecurity parent = handlerMethod
                        .getMethodAnnotation(ParentSecurity.class);
                List<Auth> auths = authService.getEmployeeAuths(employee.getId());
                if (parent != null) {
                    String[] parentUrls = parent.value();
                    for (String parentUrl : parentUrls) {
                        if (UserAuthFilter.isPass(session, url, auths, parentUrl)) {
                            return true;
                        }
                    }

                } else {
                    UnSecurity security = handlerMethod
                            .getMethodAnnotation(UnSecurity.class);
                    if (security != null) {
                        return true;
                    } else {
                        if (UserAuthFilter.isPass(session, url, auths)) {
                            return true;
                        }
                    }
                }
            }
        }
        request.setAttribute("msg", "您没有[" + url + "]资源的权限！");
        request.getRequestDispatcher("/error/noSecurity.jsp").forward(request,
                response);
        return false;
    }


    /**
     * 获取请求的资源路径
     *
     * @param request
     * @return 2014年7月28日 liuyijun
     */
    private String getRequestUrl(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return requestUri.substring(contextPath.length());
    }

}
