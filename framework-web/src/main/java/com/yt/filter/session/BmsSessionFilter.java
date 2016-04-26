package com.yt.filter.session;

import com.yt.entity.mybatis.Employee;
import com.yt.util.dhqjr.EmptyUtil;
import com.yt.util.sessionutil.EmployeeSessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 后台session拦截器
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.filter.session
 * @date 2016/4/26 0026 14:30
 * @descption: 疯狂的王麻子团队!
 */
public class BmsSessionFilter implements Filter {


    private static String[] staticUrl = {".css", ".js", ".ico", ".gif", ".html", "htm", ".jpg", ".png", ".bmp",
            ".jpeg", ".swf", ".apk", ".tif", ".flv", ".xml", ".txt", ".svg", ".json", ".map", ".mp4", ".avi"};


    private static String category;


    public void destroy() {

    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        //放行
        String uri = request.getRequestURI();
        //静态资源
        if (isStaticFile(uri)) {
            chain.doFilter(request, response);
        } else {
            //不是静态资源
            if (uri.contains("api")) {
                Employee employee = EmployeeSessionUtil.getSessionEmployee(request.getSession());
                if (EmptyUtil.isEmpty(employee)) {
                    response.sendRedirect("/");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                //其他的通过
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    /**
     * 判断是否是静态资源
     *
     * @param url
     * @return
     */
    public static boolean isStaticFile(String url) {
        for (String string : staticUrl) {
            if (url.endsWith(string)) {
                return true;
            }
        }
        return false;
    }


}

