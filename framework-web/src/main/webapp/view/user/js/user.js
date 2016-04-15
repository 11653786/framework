var userdatagrid;

$(function () {

    userdatagrid = new util.easyui.datagrid();
    userdatagrid.urls = "/api/user/selectByPageList.do";
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
                return "<a href='javascript:void(0)' onclick=\"editBanner(\'" + row.id + "\')\">编辑</a>";
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
            "isLogin": $("#isLogin").combobox('getValue'),
            "startTime": $("#startTime").datetimebox('getValue'),
            "endTime": $("#endTime").datetimebox('getValue')
        };
        //查询
        userdatagrid.searchInit();
    });
    //dialog,默认是添加
    var addOrEditDialog = new util.easyui.dialog();

    //添加用户
    $("#adduser").click(function () {
        addOrEditDialog.init('添加用户', "/api/user/addOrEdit.do", "add");
    });


});

/*
 function addBanner() {
 parent.$.modalDialog({
 title: '添加banner',
 width: 650,
 height: 380,
 href: '/banner/toAddOrEditPage',
 buttons: [{
 text: '添加',
 iconCls: 'icon-save',
 handler: function () {
 parent.$.modalDialog.openner_dataGrid = userdatagrid;
 var f = parent.$.modalDialog.handler.find('#addoreditform');
 f.submit();
 }
 }]
 });
 }

 function editBanner(bannerId) {
 //获取单选框按钮
 //var banners = bannerdatagrid.datagrid('getChecked');
 //if (banners == undefined || banners==null || banners=='') {
 //	parent.$.messager.alert("警告", "请选择数据");
 //	return;
 //}
 //验证一次修改1条
 //if(banners.length>1){
 //	parent.$.messager.alert("警告", "一次只能编辑一条信息!");
 //	return;
 //}
 //获取checkbox框中的id
 //var bannerId=banners[0]['id'];

 parent.$.modalDialog({
 title: '编辑',
 width: 650,
 height: 380,
 href: '/banner/toAddOrEditPage?id=' + bannerId,
 buttons: [{
 text: '修改',
 iconCls: 'icon-edit',
 handler: function () {
 parent.$.modalDialog.openner_dataGrid = userdatagrid;
 var f = parent.$.modalDialog.handler.find('#addoreditform');
 f.submit();
 }
 }]
 });

 }


 function offline() {
 //获取单选框按钮
 var banners = userdatagrid.datagrid('getChecked');
 if (banners == undefined || banners == null || banners == '') {
 $.messager.alert("警告", "请选择数据");
 return;
 }

 //获取checkbox框中的id
 var bannerIds = "";
 for (var a in banners) {
 bannerIds = banners[a]['id'] + "," + bannerIds;
 }

 $.ajax({
 url: '/banner/onOffLine',
 data: {"ids": bannerIds},
 dataType: 'json',
 success: function (data) {
 //发送ajax请求
 $.messager.show({
 title: '系统提示',
 msg: data['msg'],
 timeout: 5000,
 showType: 'slide'
 });
 //刷新datagrid
 if (data.success) {
 userdatagrid.datagrid('reload');
 }

 },
 error: function () {
 }
 });


 }*/
