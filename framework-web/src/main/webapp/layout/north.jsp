<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" charset="utf-8">
</script>
<div style="float:left;">
    <img src="${pageContext.request.contextPath}/static/images/logo2.png"/>
</div>
<div style="float:left;">
    <h1>东融影业综合业务管理系统</h1>
</div>
<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 4px;" class="alert alert-info">
    <c:if test="${loginEmployee != null}">【<strong>${loginEmployee.userName}</strong>】,欢迎您</c:if>
</div>

<div style="position: absolute;right: 0px; bottom: 0px;" data-options="border:false">
    <a href="javascript:void(0);" class="easyui-menubutton"
       data-options="menu:'#layout_north_zxMenu',iconCls:'status_busy'">注销</a>
</div>

<div id="layout_north_zxMenu" style="width: 100px; display: none;">
    <div>修改密码</div>
    <div>退出系统</div>
</div>