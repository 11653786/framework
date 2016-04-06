var userdatagrid;
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


$(function () {
    userdatagrid = $('#userdatagrid')
        .datagrid(
        {
            url: '/api/user/selectByPageList.do',
            fit: true,
            fitColumns: true,
            method: 'post', // 默认是post方法
            border: false,
            idField: 'id',
            pagination: true,
            checkOnSelect: false,
            selectOnCheck: false,
            singleSelect: true,
            pageSize: 10,
            pageList: [10, 15, 20, 25, 30],
            pagePosition: 'bottom',
            //行样式用来区分显示上线和下线
            /*	rowStyler:function(index,row){
             var status=row['status'];
             if(status!=undefined && status!=null && status==1){
             return 'background-color:ccc;';
             }
             }  ,*/
            rownumbers: true,
            columns: [[
                {
                    field: 'id',
                    checkbox: true
                },
                {
                    field: 'effectTime',
                    title: '上线时间',
                    width: 120,
                    align: 'center'
                },
                {
                    field: 'type',
                    title: 'banner显示渠道',
                    width: 120,
                    align: 'left',
                    formatter: function (value, row, index) {
                        if (value != null && value != undefined) {
                            switch (value) {
                                case 1:
                                    return "PC";
                                    break;
                                case 2:
                                    return "微网站";
                                    break;
                            }
                        }
                    }
                },
                {
                    field: 'sort',
                    title: 'banner位置',
                    width: 100,
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
                    width: 200,
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
                    width: 100,
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
                    width: 100,
                    formatter: function (value, row, index) {
                        return "<a href='javascript:void(0)' onclick=\"editBanner(\'" + row.id + "\')\">编辑</a>";
                    }
                }
            ]],
            toolbar: '#userToolbar',
            onLoadSuccess: function () {
                parent.$.messager.progress('close');
            }

        });
});

/**
 * 查询
 *
 * @param value
 * @param name
 */
function searchBanner() {

    var startTime = $("[name='startTime']").val();

    var endTime = $("[name='endTime']").val();

    var status = $("#status").combobox('getValue');

    bannerdatagrid.datagrid({
        queryParams: {
            "title": $("#title").val(),
            "status": status,
            "startTime": startTime,
            "endTime": endTime
        }
    });
}

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
                parent.$.modalDialog.openner_dataGrid = bannerdatagrid;
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
                parent.$.modalDialog.openner_dataGrid = bannerdatagrid;
                var f = parent.$.modalDialog.handler.find('#addoreditform');
                f.submit();
            }
        }]
    });

}


function offline() {
    //获取单选框按钮
    var banners = bannerdatagrid.datagrid('getChecked');
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
                bannerdatagrid.datagrid('reload');
            }

        },
        error: function () {
        }
    });


}
