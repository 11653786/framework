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
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/user/js/user.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/resource/resource.css">
</head>
<body>
<div class="searchForm">
    <form id="searchForm" method="post">
        标题：
        <input id="title" class="width-145"/>
        上线时间：
        <input
                class="easyui-datetimebox" type="text" id="startTime" name="startTime">
        至
        <input
                class="easyui-datetimebox" type="text" name="endTime" id="endTime">
        状态：
        <select id="status" class="easyui-combobox">
            <option value="">请选择</option>
            <option value="1">已上线</option>
            <option value="0">已下线</option>
        </select>
        <a id="searchButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>
<!-- datagrid -->
<div id="datagrid"></div>
<!-- 工具栏 -->
<div id="toolbar">
    <a onclick="addBanner();" class="easyui-linkbutton"
       iconCls="icon-add">发布</a>
    <a onclick="offline();" class="easyui-linkbutton">上线/下线</a>
</div>
</body>
</html>
