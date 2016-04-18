<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/user/updatepass.do">
    <input type="hidden" name="id" value="${user.id}"/>

    <div class="mymenu">
        <div class="one">
            <label for="password">原密码:</label>
            <input class="easyui-validatebox" id="password" type="password" name="password"
                   data-options="required:true"/>
        </div>
        <div class="one"><label for="newPassword">新密码:</label>
            <input class="easyui-validatebox" id="newPassword" type="password" name="newPassword"/>
        </div>
        <div class="one">
            <label for="rePassword">确认密码:</label>
            <input class="easyui-validatebox" id="rePassword" type="password" name="rePassword"
                   data-options="required:true,validType:'email'"/>
        </div>
    </div>
</form>