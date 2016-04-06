<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/6 0006
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入自定义标签-->
<%@ include file="/top.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasBootStrapModal="false"></simple:Script>
    <simple:angular hasAngularTree="false"></simple:angular>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/user/js/user.js"></script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
<!-- datagrid -->
<div data-options="region:'center',border:false"
     style="overflow: hidden;">
    <div id="datagrid"></div>
</div>
<!-- 工具栏 -->
<div id="userToolbar" class="pt-10">
    <form id="searchForm" method="post">
        <ul class="conditionUl">
            <li class="width-180 text-center pt-5">
                <a onclick="addBanner();" class="easyui-linkbutton"
                   iconCls="icon-add">发布</a>

                <a onclick="offline();" class="easyui-linkbutton">上线/下线</a>
            </li>
            <li class="height-30 width-270">
                <div class="left text-right width-110">
                    标题：
                </div>
                <div class="left width-150">
                    <input  id="title" class="width-145" />
                </div>
            </li>
            <li class="height-30 width-450">
                <div class="left text-right width-110">
                    上线时间：
                </div>
                <div class="left width-330">
                    <input
                            class="easyui-datetimebox" type="text" id="startTime" name="startTime">
                    至
                    <input
                            class="easyui-datetimebox" type="text" name="endTime" id="endTime">
                </div>
            </li>
            <li class="height-30 width-270">
                <div class="left text-right width-110">
                    状态：
                </div>
                <div class="left width-150">
                    <select id="status"  class="easyui-combobox width-145" >
                        <option value="">请选择</option>
                        <option value="1">已上线</option>
                        <option value="0">已下线</option>
                    </select>
                </div>
            </li>
            <li class="width-100 text-center pt-5">
                <a onclick="searchBanner();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
            </li>
        </ul>
    </form>

    <div class="clear height-10">
    </div>
</div>
</body>
</html>
