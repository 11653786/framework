<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div id="menu" class="easyui-accordion" data-options="fit:true">
    <div title="业务管理" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
        <ul id="tree"></ul>
    </div>
    <div title="系统管理" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
        content2
    </div>
    <div title="Title3">
        content3
    </div>
</div>
<script type="text/javascript" charset="utf-8">
    $('#tree').tree({
        checkbox: false,
        url: '/api/auth/getAllTree.do',
        parentField: "_parentId",
        idFiled: "id",
        lines: true,
        textFiled: "authName",
        //选中事件
        onClick: function (node) {
        }
    });
</script>

