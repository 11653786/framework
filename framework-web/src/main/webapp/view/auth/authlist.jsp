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
    <title>权限管理</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasValid="true" hasYtUtil="true" hasYtResourcesCss="true" hasYtResourcesJs="false"
                   hasYtTreeExtends="true"></simple:Script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/auth/js/auth.js"></script>
</head>
<body>
<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
<!-- datagrid -->
<div id="datagrid"></div>
<!-- 工具栏 -->
<div id="toolbar">
    <a id="adduser" class="easyui-linkbutton" iconCls="icon-add">添加</a>
    <a id="updateuser" class="easyui-linkbutton" iconCls="icon-add">修改</a>
</div>
<%--添加和修改dialog--%>
<div id="addOrEditDialog"></div>
</body>
</html>
