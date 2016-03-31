package com.yt.util.tag;


import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 *
 * angularjs的标签
 * Created by Administrator on 2016/3/1 0001.
 */
public class AngularjsTreeSupport extends TagSupport {

    private boolean hasAngularTree;

    private String jsPath = "/resource/js/";

    private String cssPath = "/resource/css/";

    @Override
    public int doStartTag() throws JspException {
        try {
            super.pageContext.getOut().write(getIncludeFile().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    private String getWebPath() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        return request.getContextPath();
    }

    /**
     * 拼装标签的方法
     *
     * @return
     */
    private StringWriter getIncludeFile() {
        StringWriter sw = new StringWriter();
        if (!StringUtils.isEmpty(hasAngularTree) && hasAngularTree) {
            sw.append("<script type='text/javascript' src=" + getWebPath() + jsPath + "angular_tree/angular-tree-control.js></script>");
            sw.append("<link rel='stylesheet' href=" + getWebPath() + cssPath + "angular_tree/css/tree-control.css></link>");
            sw.append("<link rel='stylesheet' href=" + getWebPath() + cssPath + "angular_tree/css/tree-control-attribute.css></link>");
        }
        return sw;
    }

    public boolean getHasAngularTree() {
        return hasAngularTree;
    }

    public void setHasAngularTree(boolean hasAngularTree) {
        this.hasAngularTree = hasAngularTree;
    }
}
