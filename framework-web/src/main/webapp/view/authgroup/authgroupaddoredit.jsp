<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/authGroup/saveAddOrEdit.do">
    <input type="hidden" name="isUpdate" value="${isUpdate}"/>
    <input type="hidden" name="id" value="${authgroup.id}"/>
    <input type="hidden" name="isEnable" value="${authgroup.isEnable}"/>
    <input type="hidden" name="createUser" value="${authgroup.createUser}"/>
    <input type="hidden" name="updateUser" value="${authgroup.updateUser}"/>

    <div class="mymenu">
        <div class="two">
            <label for="authGroupName">名称:</label>
            <input class="easyui-validatebox" value="${authgroup.authGroupName}" id="authGroupName" type="text"
                   name="authGroupName"
                   data-options="required:true"/>
        </div>
    </div>
    </div>
</form>