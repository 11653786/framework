package com.yt.util.tag;


import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * 自定义标签引入头文件
 * Created by Administrator on 2016/2/29 0029.
 */
public class ScriptTagSupport extends TagSupport {

    /**
     * jquery
     */
    private boolean hasJquery;

    /**
     * angularjs
     */
    private boolean hasAngularjs;


    /**
     * easyui
     */
    private boolean hasEasyUi;

    /**
     * bootstrap
     */
    private boolean hasBootStrap;

    /**
     * 我自己的工具类js
     */
    private boolean hasYtUtil;

    /**
     * 自己的资源文件
     */
    private boolean hasYtResourcesJs;

    /**
     * 自定义的表单样式
     */
    private boolean hasYtResourcesCss;

    /**
     * easyui自定义 form验证的js
     */
    private boolean hasValid;

    /**
     * 根据easyui的扩展树
     */
    private boolean hasYtTreeExtends;


    String jsPath = "/static/js/";

    String cssPath = "/static/css/";

    String resourcePath = "/resource/";

    @Override
    public int doStartTag() throws JspException {
        try {
            super.pageContext.getOut().write(getIncludeFile().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    /**
     * 拼装标签的方法
     *
     * @return
     */
    private StringWriter getIncludeFile() {
        StringWriter sw = new StringWriter();


        if (!StringUtils.isEmpty(hasYtResourcesCss) && hasYtResourcesCss) {
            sw.append("<link rel='stylesheet' href=" + getWebPath() + "/static"+ resourcePath + "resource.css></link>");
        }

        if (!StringUtils.isEmpty(hasBootStrap) && hasBootStrap) {
            sw.append("<link rel='stylesheet' href=" + getWebPath() + cssPath + "bootstrap/css/bootstrap.min.css></link>");
        }


        if (validJquery()) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + jsPath + "jquery/jquery.js></script>");
        }

        if (!StringUtils.isEmpty(hasAngularjs) && hasAngularjs) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + jsPath + "angularjs/angular.1.2.29.js></script>");
        }


        if (!StringUtils.isEmpty(hasEasyUi) && hasEasyUi) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + jsPath + "easyui/jquery.easyui.min.js></script>");
            sw.append("<script type='text/javascript' src=" + getWebPath() + jsPath + "easyui/easyui-lang-zh_CN.js></script>");
            sw.append("<link rel='stylesheet' href=" + getWebPath() + cssPath + "easyui/themes/icon.css></link>");
            sw.append("<link rel='stylesheet' href=" + getWebPath() + cssPath + "easyui/themes/default/easyui.css></link>");
        }

        if (!StringUtils.isEmpty(hasYtResourcesJs) && hasYtResourcesJs) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + "/static" + resourcePath + "yt-resource-1.0.js></script>");
        }

        if (!StringUtils.isEmpty(hasYtUtil) && hasYtUtil) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + "/static" + resourcePath + "yt-util-1.0.js></script>");
        }


        if (!StringUtils.isEmpty(hasValid) && hasValid) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + "/static" + resourcePath + "yt-easyui_valid-1.0.js></script>");
        }

        if (!StringUtils.isEmpty(hasYtTreeExtends) && hasYtTreeExtends) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + "/static" + resourcePath + "yt_tree_extends-1.0.js></script>");
        }


        return sw;
    }


    private String getWebPath() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        return request.getContextPath();
    }

    /**
     * 判断是否引入jquery
     *
     * @return
     */
    private boolean validJquery() {
        return hasJquery = !StringUtils.isEmpty(hasJquery) ? true : false;
    }

    public boolean isHasJquery() {
        return hasJquery;
    }

    public void setHasJquery(boolean hasJquery) {
        this.hasJquery = hasJquery;
    }

    public boolean isHasAngularjs() {
        return hasAngularjs;
    }

    public void setHasAngularjs(boolean hasAngularjs) {
        this.hasAngularjs = hasAngularjs;
    }


    public boolean isHasEasyUi() {
        return hasEasyUi;
    }

    public void setHasEasyUi(boolean hasEasyUi) {
        this.hasEasyUi = hasEasyUi;
    }

    public boolean isHasBootStrap() {
        return hasBootStrap;
    }

    public void setHasBootStrap(boolean hasBootStrap) {
        this.hasBootStrap = hasBootStrap;
    }


    public boolean isHasYtUtil() {
        return hasYtUtil;
    }

    public void setHasYtUtil(boolean hasYtUtil) {
        this.hasYtUtil = hasYtUtil;
    }


    public boolean isHasValid() {
        return hasValid;
    }

    public void setHasValid(boolean hasValid) {
        this.hasValid = hasValid;
    }

    public boolean isHasYtResourcesJs() {
        return hasYtResourcesJs;
    }

    public void setHasYtResourcesJs(boolean hasYtResourcesJs) {
        this.hasYtResourcesJs = hasYtResourcesJs;
    }

    public boolean isHasYtResourcesCss() {
        return hasYtResourcesCss;
    }

    public void setHasYtResourcesCss(boolean hasYtResourcesCss) {
        this.hasYtResourcesCss = hasYtResourcesCss;
    }

    public boolean isHasYtTreeExtends() {
        return hasYtTreeExtends;
    }

    public void setHasYtTreeExtends(boolean hasYtTreeExtends) {
        this.hasYtTreeExtends = hasYtTreeExtends;
    }
}
