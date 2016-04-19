<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<form id="addOrEditForm" method="post" action="${pageContext.request.contextPath}/api/authGroup/shouquan.do">
    <input type="hidden" name="id" value="${authgroup.id}"/>

        <div style="width:100%;margin-left: 10dp;">
            <ul id="tt"></ul>
        </div>
</form>
<script type="text/javascript">
    $(function () {
        $('#tt').tree({
            checkbox: true,
            url: '/api/auth/getAllTree.do',
            parentField: "_parentId",
            idFiled: "id",
            textFiled: "authName",
            //选中事件
            onSelect: function (node) {
            }
        });

    });
</script>