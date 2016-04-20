<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/employee/saveAddOrEdit.do">
    <input type="hidden" name="isUpdate" value="${isUpdate}"/>
    <input type="hidden" name="employeeId" value="${employee.id}"/>
    <div class="mymenu">
        <div class="two">
            <label for="isLogin">权限组:</label>
            <select class="easyui-combobox" id="isLogin" name="isLogin" style="width:160px;">
                <option value="">请选择</option>
                <c:choose>
                    <c:when test="${employee.isLogin==1}">
                        <option value="1" selected="selected">正常</option>
                        <option value="0">限制登录</option>
                    </c:when>
                    <c:when test="${employee.isLogin==0}">
                        <option value="1">正常</option>
                        <option value="0" selected="selected">限制登录</option>
                    </c:when>
                    <c:when test="${employee.isLogin=='' ||employee.isLogin==null}">
                        <option value="1" selected="selected">正常</option>
                        <option value="0">限制登录</option>
                    </c:when>
                </c:choose>
            </select>
        </div>
    </div>
</form>