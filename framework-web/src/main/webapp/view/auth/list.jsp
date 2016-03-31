<%--
  Created by IntelliJ IDEA.
  User: yt
  Date: 2016/3/1 0001
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<html ng-app="auth">
<head>
    <title>权限管理</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasBootStrapModal="false"></simple:Script>
    <simple:angular hasAngularTree="false"></simple:angular>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/auth/js/auth.js"></script>
</head>
<body>
<input id="pwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true"/><br>
<input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"
       required="required" validType="auth['#pwd']"/>
<form action="1.jsp">
    <select
            id="test"
            class="easyui-combobox"
            name="state"
            style="width:200px;"
            required="required"
            validType="selectValueRequired['#test']"
            >
        <option value="">test1</option>
        <option value="2">test2</option>
        <option value="3">test3</option>
    </select>
    <input type="submit" value="tijiao" />

</form>


</body>
</html>
