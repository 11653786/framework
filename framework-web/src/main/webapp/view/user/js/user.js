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
            align: 'center'
        }, {
            field: 'nikeName',
            title: '昵称',
            align: 'center'
        }, {
            field: 'email',
            title: 'email',
            align: 'center'
        }, {
            field: 'phone',
            title: '手机号',
            align: 'center'
        },
        {
            field: 'isLogin',
            title: '登录状态',
            align: 'center',
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
            field: 'sort',
            title: 'banner位置',
            align: 'left',
            //显示内容为:banner显示渠道+位置,sort这个字段
            formatter: function (value, row, index) {
                var type = row['type'];
                switch (type) {
                    case 1:
                        return "PC-" + value;
                        break;
                    case 2:
                        return "微网站-" + value;
                        break;
                }
            }
        },
        {
            field: 'title',
            title: '标题',
            align: 'left',
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
            field: 'status',
            title: '状态',
            align: 'left',
            formatter: function (value, row, index) {
                switch (value) {
                    case 0:
                        return "下线";
                        break;
                    case 1:
                        return "上线";
                        break;
                }
            }
        },
        {
            field: 'update',
            title: '操作',
            align: 'center',
            width: 120,
            formatter: function (value, row, index) {
                return "<a href='javascript:void(0)' onclick=\"editBanner(\'" + row.id + "\')\">编辑</a>";
            }
        }
    ]];
    userdatagrid.init();
});

/**
 * 查询
 *
 * @param value
 * @param name
 */
/*function searchBanner() {

 var startTime = $("[name='startTime']").val();

 var endTime = $("[name='endTime']").val();

 var status = $("#status").combobox('getValue');

 userdatagrid.datagrid({
 queryParams: {
 "title": $("#title").val(),
 "status": status,
 "startTime": startTime,
 "endTime": endTime
 }
 });
 }*/
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
