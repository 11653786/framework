package com.yt.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yt.util.sessionutil.EmployeeSessionUtil;
import com.yt.util.yt.annotation.system.ParentSecurity;
import com.yt.util.yt.annotation.system.UnSecurity;
import com.yt.util.yt.annotation.system.UnSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * 权限拦截器
 *
 * @author liuyijun
 */
public class BmsSecurityInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = this.getRequestUrl(request);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        UnSession unSession = handlerMethod
                .getMethodAnnotation(UnSession.class);
        HttpSession session=request.getSession();
        if (unSession != null) {
            return true;
        } else {
            EmployeeSessionUtil eSession = EmployeeSessionUtil.getEmployeeSession(session);
            if (eSession == null || eSession.getIntId() == null) {
                request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录！");
                request.getRequestDispatcher("/error/noSession.jsp").forward(
                        request, response);
                return false;
            } else {
				ParentSecurity parent = handlerMethod
						.getMethodAnnotation(ParentSecurity.class);
				if (parent != null) {
					String[] parentUrls = parent.value();
					for (String parentUrl : parentUrls) {
						if (UserAuthFilter.isPass(url, parentUrl)) {
							return true;
						}
					}

				} else {
					UnSecurity security = handlerMethod
							.getMethodAnnotation(UnSecurity.class);
					if (security != null) {
						return true;
					} else {
						if (UserAuthFilter.isPass(url)) {
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
