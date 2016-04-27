<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2015/8/11
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台首页</title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasBootStrapModal="false"></simple:Script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/resource/yt-util-1.0.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/resource/resource.css">
    <%--easyui tree的扩展--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/yt_tree_extend.js"></script>
    <script type="text/javascript">
        function initTree(treeId, treeUrl) {
            $('#' + treeId).tree({
                checkbox: false,
                url: treeUrl,
                parentField: "_parentId",
                idFiled: "id",
                lines: true,
                textFiled: "authName",
                //选中事件
                onClick: function (node) {
                    if(node.authUrl!=null){
                        //连接的内容
                        var iframe = "<iframe width=100% height=100% frameborder=0 scrolling=auto src=" + "${pageContext.request.contextPath}" + node.authUrl + ".do" + "></iframe>";
                        $('#tabs').tabs('add', {
                            title: node.authName,
                            content: iframe,
                            closable: true
                        });
                    }

                }
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north.jsp',border:false"
     style="height: 80px; overflow: hidden;" class=""></div>
<div data-options="region:'west',title:'菜单',split:true,
                    href:'${pageContext.request.contextPath}/layout/menu.jsp'" style="width:200px;"></div>
<div data-options="region:'center',title:'欢迎东融影业综合业务管理系统'" >
    <div id="tabs" class="easyui-tabs" data-options="fit:true">
    </div>
</div>
</body>
</html>

