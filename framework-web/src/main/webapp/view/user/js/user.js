var userdatagrid;

$(function () {
    userdatagrid=new util.easyui.datagrid();
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
