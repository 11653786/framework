<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2015/8/11
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台首页</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasBootStrapModal="false"></simple:Script>
    <simple:angular hasAngularTree="false"></simple:angular>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/resource/yt-util-1.0.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/resource/resource.css">
</head>
<body class="easyui-layout">
<div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north.jsp',border:false"
     style="height: 80px; overflow: hidden;" class=""></div>
<div data-options="region:'west',title:'菜单',split:true" style="width:200px;"></div>
<div data-options="region:'center',title:'欢迎东融影业综合业务管理系统'" style="padding:5px;"></div>
</body>
</html>

