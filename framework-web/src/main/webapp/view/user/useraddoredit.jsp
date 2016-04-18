<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/user/saveAddOrEdit.do">
    <input type="hidden" name="isUpdate" value="${isUpdate}"/>
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="createDate" value="${user.createDate}"/>
    <input type="hidden" name="createUser" value="${user.createUser}"/>
    <input type="hidden" name="lastLoginTime" value="${user.lastLoginTime}"/>
    <input type="hidden" name="loginTotal" value="${user.loginTotal}"/>
    <input type="hidden" name="password" value="${user.password}"/>
    <div class="mymenu">
        <div class="two">
            <label for="userName">用户名:</label>
            <input class="easyui-validatebox" id="userName" type="text" name="userName"
                   data-options="required:true"/>
        </div>
        <div class="two"><label for="nikeName">昵称:</label>
            <input class="easyui-validatebox" id="nikeName" type="text" name="nikeName"/>
        </div>
        <div class="two">
            <label for="email">email:</label>
            <input class="easyui-validatebox" id="email" type="text" name="email"
                   data-options="required:true,validType:'email'"/>
        </div>
        <div class="two">
            <label for="phone">手机号:</label>
            <input class="easyui-validatebox" id="phone" type="text" name="phone" data-options="required:true"/>
        </div>
        <div class="two">
            <label for="isLogin">登录状态:</label>
            <select id="cc" class="easyui-combobox" id="isLogin" name="isLogin" style="width:160px;">
                <option value="">请选择</option>
                <option value="1">正常</option>
                <option value="0">限制登录</option>
            </select>
        </div>
        <div class="two">
            <label for="isEnable">启动状态:</label>
            <select id="cc" class="easyui-combobox" id="isEnable" name="isEnable" style="width:160px;">
                <option value="">请选择</option>
                <option value="1">可用</option>
                <option value="0">不可用</option>
            </select>
        </div>

    </div>
</form>
</body>
</html>
