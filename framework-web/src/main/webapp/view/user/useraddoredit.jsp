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
        <ul >
            <li class="two"><label for="name">Name:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
            </li>
            <li class="two">
                <label for="email">Email:</label>
                <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'"/>
            </li>
            <li class="two"><label for="name">Name:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
            </li>
            <li class="two">
                <label for="email">Email:</label>
                <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'"/>
            </li>
        </ul>
    </div>
</form>
</body>
</html>
