var authdatagrid;
var path;
$(function () {

    authdatagrid = new util.easyui.datagrid();
    path = $("#path").val();
    authdatagrid.urls = "/api/auth/selectByPageList.do";
    authdatagrid.columns = [[{
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
            field: 'phone',
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
            field: 'loginTotal',
            title: '登录次数',
            align: 'left',
            width: 100,
            formatter: function (value, row, index) {
                var url = row['bannerUrl'];
                if (url != null && url != "") {
                    return "<a href=" + url + " target=_blank >" + value + "</a>"
                } else {
                    return value;
                }
            }
        },
        {
            field: 'lastLoginTime',
            title: '最后登录时间',
            align: 'center',
            width: 100,
            formatter: function (value, row, index) {
                //easyui返回的date类型为时间戳这里转换下
                return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
            }
        },
        {
            field: 'update',
            title: '操作',
            align: 'center',
            width: 100,
            formatter: function (value, row, index) {
                var loginurl = path + "/api/user/isLogin.do?id=" + row.id;
                var login = row.isLogin == 1 ? "可登录" : "不可登录";
                var enable = row.isEnable == 1 ? "可使用" : "不可使用";
                var enableurl = path + "/api/user/isEnable.do?id=" + row.id;
                return "<a href='javascript:void(0)' onclick=\"ajaxUrl(\'" + loginurl + "\',\'" + 'datagrid' + "\')\">" + login + "</a> <a href='javascript:void(0)' onclick=\"ajaxUrl(\'" + enableurl + "\',\'" + 'datagrid' + "\')\">" + enable + "</a>";
            }
        }
    ]];
    //初始化数据表格
    authdatagrid.init();


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