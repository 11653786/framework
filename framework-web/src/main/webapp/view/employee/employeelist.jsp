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
    <title>员工管理</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   ></simple:Script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/resource/yt-util-1.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/employee/js/employee.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/resource/resource.css">
</head>
<body>
<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
<!-- datagrid -->
<div id="datagrid"></div>
<!-- 工具栏 -->
<div id="toolbar">
    <div class="searchForm">
        <form id="searchForm" method="post">
            <div class="searchFormRows">
                帐号：
                <input id="userName" class="searchinput"/>
                昵称：
                <input id="nikeName" class="searchinput"/>
                email：
                <input id="email" class="searchinput"/>
                手机：
                <input id="phone" class="searchinput"/>
                登录状态：
                <%--这里注意不要专门去写样式,因为easyui的样式已经定义了样式,加载顺序会导致每次加载页面长度不同,用style优先级最高固定住--%>
                <select id="isLogin" data-options="editable:false" class="easyui-combobox" style="width: 100px;">
                    <option value="">请选择</option>
                    <option value="0">限制登录</option>
                    <option value="1">正常</option>
                </select>
                <a id="searchButton" class="easyui-linkbutton" iconCls="icon-search">查询</a>
            </div>

        </form>
    </div>
    <a id="adduser" class="easyui-linkbutton" iconCls="icon-add">添加员工</a>
    <a id="updateuser" class="easyui-linkbutton" iconCls="icon-add">编辑员工</a>
    <a id="updatepass" class="easyui-linkbutton" iconCls="icon-add">修改密码</a>
    <a id="addauth" class="easyui-linkbutton" iconCls="icon-add">分配权限</a>
</div>
<%--添加和修改dialog--%>
<div id="addOrEditDialog"></div>
</body>
</html>
