var authgroupdatagrid;
var path;
$(function () {

    authgroupdatagrid = new util.easyui.datagrid();
    path = $("#path").val();
    authgroupdatagrid.urls = "/api/authGroup/selectByPageList.do";
    authgroupdatagrid.columns = [[{
        field: 'id',
        checkbox: true
    },
        {
            field: 'authGroupName',
            title: '名称',
            align: 'center',
            width: 100
        },
        {
            field: 'update',
            title: '操作',
            align: 'center',
            width: 100,
            formatter: function (value, row, index) {
                var loginurl = path + "/api/authGroup/delete.do?id=" + row.id;
                return "<a href='javascript:void(0)' onclick=\"ajaxUrl(\'" + loginurl + "\',\'" + 'datagrid' + "\')\">删除</a>";
            }
        }
    ]];
    //初始化数据表格
    authgroupdatagrid.init();

    //dialog,默认是添加
    var addOrEditDialog = new util.easyui.dialog();

    //添加用户
    $("#addauthgroup").click(function () {
        addOrEditDialog.init('添加', "/api/authGroup/addOrEdit.do", "add");
    });

    $("#updateauthgroup").click(function () {
        addOrEditDialog.init('编辑', "/api/authGroup/addOrEdit.do", "update");
    });


});

