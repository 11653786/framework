<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div id="menu" class="easyui-accordion" data-options="fit:true">
    <div title="业务管理" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
        <ul id="tree"></ul>
    </div>
    <div title="系统管理" data-options="iconCls:'icon-reload'" style="padding:10px;">
        <ul id="tree1"></ul>
    </div>

</div>
<script type="text/javascript" charset="utf-8">
    initTree("tree", "/api/auth/getLoginTree.do?authName=1");
    initTree("tree1", "/api/auth/getLoginTree.do?authName=2");
</script>

