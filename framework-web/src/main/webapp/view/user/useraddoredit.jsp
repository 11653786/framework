<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/user/saveAddOrEdit.do">
    <input type="hidden" name="isUpdate" value="${isUpdate}"/>
    <input type="hidden" name="id" value="${user.id}"/>
    <%--  <input type="hidden" name="createDate" value="${user.createDate}"/>--%>
    <input type="hidden" name="createUser" value="${user.createUser}"/>
    <%--<input type="hidden" name="updateDate" value="${user.updateDate}"/>--%>
    <input type="hidden" name="updateUser" value="${user.updateUser}"/>
    <input type="hidden" name="lastLoginTime" value="${user.lastLoginTime}"/>
    <input type="hidden" name="loginTotal" value="${user.loginTotal}"/>
    <input type="hidden" name="password" value="${user.password}"/>

    <div class="mymenu">
        <div class="two">
            <label for="userName">用户名:</label>
            <input class="easyui-validatebox" value="${user.userName}" id="userName" type="text" name="userName"
                   data-options="required:true"/>
        </div>
        <div class="two"><label for="nikeName">昵称1:</label>
            <input class="easyui-validatebox" value="${user.nikeName}" id="nikeName" type="text" name="nikeName"/>
        </div>
        <div class="two">
            <label for="email">email:</label>
            <input class="easyui-validatebox" value="${user.email}" id="email" type="text" name="email"
                   data-options="required:true,validType:'email'"/>
        </div>
        <div class="two">
            <label for="phone">手机号:</label>
            <input class="easyui-validatebox" id="phone" value="${user.phone}" type="text" name="phone"
                   data-options="required:true,validType:'isPhone[5]'"/>
        </div>
        <div class="two">
            <label for="isLogin">登录状态:</label>
            <select class="easyui-combobox" id="isLogin" name="isLogin" style="width:160px;">
                <option value="">请选择</option>
                <c:choose>
                    <c:when test="${user.isLogin==1}">
                        <option value="1" selected="selected">正常</option>
                        <option value="0">限制登录</option>
                    </c:when>
                    <c:when test="${user.isLogin==0}">
                        <option value="1">正常</option>
                        <option value="0" selected="selected">限制登录</option>
                    </c:when>
                    <c:when test="${user.isLogin=='' ||user.isLogin==null}">
                        <option value="1" selected="selected">正常</option>
                        <option value="0">限制登录</option>
                    </c:when>
                </c:choose>
            </select>


        </div>
        <div class="two">
            <label for="isEnable">启动状态:</label>
            <select class="easyui-combobox" id="isEnable" name="isEnable" style="width:160px;">
                <option value="">请选择</option>
                <c:choose>
                    <c:when test="${user.isEnable==1}">
                        <option value="1" selected="selected">可用</option>
                        <option value="0">不可用</option>
                    </c:when>
                    <c:when test="${user.isEnable==0}">
                        <option value="1">可用</option>
                        <option value="0" selected="selected">不可用</option>
                    </c:when>
                    <c:when test="${user.isEnable=='' ||user.isEnable==null}">

                        <option value="1" selected="selected">可用</option>
                        <option value="0">不可用</option>
                    </c:when>
                </c:choose>
            </select>
        </div>
    </div>
</form>