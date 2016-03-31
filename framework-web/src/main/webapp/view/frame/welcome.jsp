<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/1 0001
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<html>
<head>
    <title>系统管理页面</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasBootStrap="false" hasZtree="false" hasAngularjs="false"></simple:Script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:100px;"></div>
<div data-options="region:'west',title:'menu',split:true" style="width:200px;">
    <div id="menu" class="easyui-accordion"  data-options="fit:'true'">
        <%--这块做个标签出来--%>
        <div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
            <h3 style="color:#0099FF;">Accordion for jQuery</h3>
            <p>111</p>
        </div>
        <div title="Title2" data-options="iconCls:'icon-reload'" style="padding:10px;">
            content2
        </div>
        <div title="Title3" data-options="iconCls:'icon-reload'">
            content3
        </div>
    </div>
</div>
<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
</div>
</body>
</html>
