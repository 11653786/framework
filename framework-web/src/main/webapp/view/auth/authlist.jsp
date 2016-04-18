<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入自定义标签-->
<%@ include file="/top.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasBootStrapModal="false"></simple:Script>
    <simple:angular hasAngularTree="false"></simple:angular>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/resource/yt-util-1.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/auth/js/auth.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/resource/resource.css">
</head>
<body>
<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
<!-- datagrid -->
<div id="datagrid"></div>
<!-- 工具栏 -->
<div id="toolbar">
    <a id="adduser" class="easyui-linkbutton" iconCls="icon-add">添加用户</a>
    <a id="updateuser" class="easyui-linkbutton" iconCls="icon-add">编辑用户</a>
</div>
<%--添加和修改dialog--%>
<div id="addOrEditDialog"></div>
</body>
</html>
