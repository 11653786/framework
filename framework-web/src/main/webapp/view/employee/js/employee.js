var userdatagrid;
var path;
$(function () {

    userdatagrid = new util.easyui.datagrid();
    path = $("#path").val();
    userdatagrid.urls = "/api/employee/selectByPageList.do";
    userdatagrid.columns = [[{
        field: 'id',
        checkbox: true
         },
        {
            field: 'userName',
            title: '帐号',
            align: 'center',
            width: 100
        }, {
            field: 'nikeName',
            title: '昵称',
            align: 'center',
            width: 100
        }, {
            field: 'email',
            title: 'email',
            align: 'center',
            width: 100
        }, {
            field: 'mobile',
            title: '手机号',
            align: 'center',
            width: 100
        },
        {
            field: 'isLogin',
            title: '登录状态',
            align: 'center',
            width: 100,
            formatter: function (value, row, index) {
                if (value != null && value != undefined) {
                    switch (value) {
                        case 0:
                            return "限制登录";
                            break;
                        case 1:
                            return "正常";
                            break;
                    }
                }
            },
            styler: function (value, row, index) {
                if (value == 0) {
                    return 'background-color:#ffee00;color:red;';
                }

                if (value == 1) {
                    return 'background-color:#faccc0;color:green;';
                }
            }
        },
        {
            field: 'isEnable',
            title: '是否可用',
            width: 100,
            align: 'left',
            //显示内容为:banner显示渠道+位置,sort这个字段
            formatter: function (value, row, index) {
                switch (value) {
                    case 0:
                        return "不可用"
                        break;
                    case 1:
                        return "可用";
                        break;
                }
            },
            styler: function (value, row, index) {
                if (value == 0) {
                    return 'background-color:#aaae00;color:#ccc;';
                }
                if (value == 1) {
                    return 'background-color:#f00f00;color:blue;';
                }
            }
        },
        {
            field: 'update',
            title: '操作',
            align: 'center',
            width: 100,
            formatter: function (value, row, index) {
                var loginurl = path + "/api/employee/isLogin.do?id=" + row.id;
                var login = row.isLogin == 1 ? "可登录" : "不可登录";
                var enable = row.isEnable == 1 ? "可使用" : "不可使用";
                var enableurl = path + "/api/employee/isEnable.do?id=" + row.id;
                return "<a href='javascript:void(0)' onclick=\"ajaxUrl(\'" + loginurl + "\',\'" + 'datagrid' + "\')\">" + login + "</a> <a href='javascript:void(0)' onclick=\"ajaxUrl(\'" + enableurl + "\',\'" + 'datagrid' + "\')\">" + enable + "</a>";
            }
        }
    ]];
    //初始化数据表格
    userdatagrid.init();
    //条件查询
    $("#searchButton").click(function () {
        userdatagrid.queryParams = {
            "username": $("#userName").val(),
            "nikeName": $("#nikeName").val(),
            "phone": $("#phone").val(),
            "email": $("#email").val(),
            "isLogin": $("#isLogin").combobox('getValue')
        };
        //查询
        userdatagrid.searchInit();
    });
    //dialog,默认是添加
    var addOrEditDialog = new util.easyui.dialog();

    //添加用户
    $("#adduser").click(function () {
        addOrEditDialog.init('添加员工', "/api/employee/addOrEdit.do", "add");
    });

    $("#updateuser").click(function () {
        addOrEditDialog.init('编辑员工', "/api/employee/addOrEdit.do", "update");
    });

    $("#updatepass").click(function () {
        addOrEditDialog.init('修改密码', "/api/employee/updatepass.do", "update");
    });

    $("#addauth").click(function () {
        addOrEditDialog.init('分配权限', "/api/employee/auth.do", "update");
    });

});

