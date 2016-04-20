<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/employee/auth.do">
    <input type="hidden" name="isUpdate" value="${isUpdate}"/>
    <input type="hidden" name="employeeId" value="${employeeId}"/>

    <div class="mymenu">
        <div class="two">
            <label for="isLogin">权限组:</label>
            <select class="easyui-combobox" id="authGroupId" name="authGroupId" style="width:160px;">
                <option value="">请选择</option>
                <c:forEach items="${authGroups}" var="authGroup">
                    <c:if test="${authGroup.id==mygroup.authGroupId}" var="isTrue">
                        <option selected="selected" value="${authGroup.id}">${authGroup.authGroupName}</option>
                    </c:if>
                    <c:if test="${!isTrue}">
                        <option value="${authGroup.id}">${authGroup.authGroupName}</option>
                    </c:if>

                </c:forEach>
            </select>
        </div>
    </div>
</form>