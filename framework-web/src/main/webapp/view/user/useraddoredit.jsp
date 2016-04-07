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
<form id="addOrEditForm" method="post">
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
                <label for="password">密码:</label>
                <input class="easyui-validatebox" id="password" type="text" name="password" data-options="required:true"/>
            </div>
            <div class="two">
                <label  for="email">email:</label>
                <input class="easyui-validatebox" id="email" type="text" name="email"
                       data-options="required:true,validType:'email'"/>
            </div>
            <div class="two">
                <label for="phone">手机啊啊啊号:</label>
                <input class="easyui-validatebox" id="phone" type="text" name="phone" data-options="required:true"/>
            <div>
    </div>
</form>
</body>
</html>
