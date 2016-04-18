<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/auth/saveAddOrEdit.do">
    <input type="hidden" name="isUpdate" value="${isUpdate}"/>
    <input type="hidden" name="id" value="${auth.id}"/>
    <input type="hidden" name="createDate" value="${auth.createDate}"/>
    <input type="hidden" name="createUser" value="${auth.createUser}"/>
    <input type="hidden" name="updateTime" value="${auth.updateTime}"/>
    <input type="hidden" name="updateUser" value="${auth.updateUser}"/>
    <input type="hidden" name="_parentId" value="${auth._parentId}"/>

    <div class="mymenu">
        <div class="two">
            <label for="authName">权限名称:</label>
            <input class="easyui-validatebox" value="${auth.authName}" id="authName" type="text" name="authName"
                   data-options="required:true"/>
        </div>
        <div class="two"><label for="authUrl">权限url:</label>
            <input class="easyui-validatebox" value="${auth.authUrl}" id="authUrl" type="text" name="authUrl"/>
        </div>
        <div class="two">
            <label for="isEnable">启用状态:</label>
            <select class="easyui-combobox" id="isEnable" name="isEnable" style="width:160px;">
                <option value="">请选择</option>
                <c:choose>
                    <c:when test="${auth.isEnable==1}">
                        <option value="1" selected="selected">可用</option>
                        <option value="0">不可用</option>
                    </c:when>
                    <c:when test="${auth.isEnable==0}">
                        <option value="1">可用</option>
                        <option value="0" selected="selected">不可用</option>
                    </c:when>
                    <c:when test="${auth.isEnable=='' ||auth.isEnable==null}">
                        <option value="1" selected="selected">可用</option>
                        <option value="0">不可用</option>
                    </c:when>
                </c:choose>
            </select>


        </div>
        <div class="two">
            <label for="authType">权限类型:</label>
            <select class="easyui-combobox" id="authType" name="authType" style="width:160px;">
                <option value="">请选择</option>
                <c:choose>
                    <c:when test="${auth.authType==1}">
                        <option value="1" selected="selected">按钮</option>
                        <option value="2">页面</option>
                        <option value="3">菜单</option>
                    </c:when>
                    <c:when test="${auth.authType==2}">
                        <option value="1">按钮</option>
                        <option value="2" selected="selected">页面</option>
                        <option value="3">菜单</option>
                    </c:when>
                    <c:when test="${auth.authType==3}">
                        <option value="1">按钮</option>
                        <option value="2">页面</option>
                        <option value="3" selected="selected">菜单</option>
                    </c:when>
                    <c:when test="${auth.authType=='' ||auth.authType==null}">
                        <option value="1">按钮</option>
                        <option value="2">页面</option>
                        <option value="3">菜单</option>
                    </c:when>
                </c:choose>
            </select>
        </div>

    </div>
</form>
</body>
</html>
