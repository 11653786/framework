var authdatagrid;
var path;
$(function () {

    path = $("#path").val();
    authdatagrid = $('#datagrid').treegrid({
        url: path + '/api/auth/selectByPageList.do',
        idField: 'id',
        treeField: 'authName',
        fit: true,
        method: "post",
        fitColumns: true,
        pagination: true,
        singleSelect: true,
        pageSize: 10,
        pageList: [10, 15, 20, 25, 30],
        pagePosition: 'bottom',
        toolbar: "#toolbar",
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'authName', title: '权限名称', width: 180},
            {field: 'authUrl', title: '权限url', width: 80},
            {field: 'authType', title: '权限类型', width: 60, align: 'right'},
            {field: 'isEnable', title: '是否启用', width: 80}
        ]]
    });


    //dialog,默认是添加
    var addOrEditDialog = new util.easyui.dialog();

    //添加用户
    $("#adduser").click(function () {
        addOrEditDialog.init('添加', "/api/auth/addOrEdit.do", "add");
    });

    $("#updateuser").click(function () {
        addOrEditDialog.init('修改', "/api/auth/addOrEdit.do", "update");
    });

});