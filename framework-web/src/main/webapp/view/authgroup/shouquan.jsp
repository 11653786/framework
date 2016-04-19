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
    <input type="text" name="ids" id="ids" value="${ids}"/>
    <ul id="ztree"></ul>
</form>
<script type="text/javascript">
    $(function () {
        var ztree = $('#ztree').tree({
            checkbox: true,
            url: '/api/auth/getAllTree.do',
            parentField: "_parentId",
            idFiled: "id",
            lines: true,
            //叠层选中
            cascadeCheck: false,
            textFiled: "authName",
            //选中事件
            onSelect: function (node) {
            },
            //一般应该实在submit里面写,我这里面已经写好了submit的事件,所以这里每次onCheck的时候设置id
            onCheck: function (data) {
                var nodes = ztree.tree('getChecked');
                var ids = "";
                for (var a = 0; a < nodes.length; a++) {
                    ids = nodes[a]['id'] + "," + ids;
                }
                $("#ids").val(ids);
            },
            onLoadSuccess: function (data) {
                //获取返回数据全部位选中的信息
                var nodes = ztree.tree('getChecked', ['unchecked', 'indeterminate']);
                var ids = $("#ids").val().split(",");
                for (var i = 0; i < ids.length; i++) {
                    for (var j = 0; j < nodes.length; j++) {
                        var node = nodes[j];
                        if (node && node.id == ids[i]) {
                            //选中
                            ztree.tree('check', node.target);
                        }
                    }
                }
            }
        });

    });
</script>