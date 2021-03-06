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


var util = function () {
};

util.easyui = function () {
};


/**
 * easyui数据表格
 */
util.easyui.datagrid = function () {
    //easyui数据表格的id
    this.id = "#datagrid";
    //是否填充满
    this.fit = true;
    //url,显示
    this.urls = "";
    //横向填充满
    this.fitColumns = true;
    //请求方法
    this.method = "post";
    //边框
    this.border = false;
    //id
    this.idField = 'id';
    //底部工具栏
    this.paginations = true;
    //行选中功能是否开启
    this.checkOnSelect = false;
    //点击框选择行
    this.selectOnCheck = false;
    //一次选择1行?
    this.singleSelect = true;
    //每页显示数量
    this.pageSize = 10;
    //显示列表
    this.pageList = [10, 15, 20, 25, 30];
    //数据表格
    this.columns;
    //工具条
    this.toolbar = '#toolbar';
    //条件查询的参数
    this.queryParams;
    //排序字段
    this.sortName = "id";
    //升序降序
    this.sortOrder = "desc";
    //加载数据表格的方法
    this.init = function () {
        //作用域问题
        var parent = this;
        $(parent.id).datagrid({
            url: parent.urls,
            fit: parent.fit,
            method: parent.method,
            fitColumns: parent.fitColumns,
            border: parent.border,
            idField: parent.idField,
            pagination: parent.paginations,
            checkOnSelect: parent.checkOnSelect,
            selectOnCheck: parent.selectOnCheck,
            singleSelect: parent.singleSelect,
            pageSize: parent.pageSize,
            pageList: parent.pageList,
            pagePosition: 'bottom',
            columns: parent.columns,
            toolbar: parent.toolbar,
            sortName: parent.sortName,
            sortOrder: parent.sortOrder
        });
    }

    //条件查询方法
    this.searchInit = function () {
        var parent = this;
        $(parent.id).datagrid({
            queryParams: parent.queryParams
        });
    }
}


util.easyui.treegrid = function () {
    //easyui数据表格的id
    this.id = "#datagrid";
    //是否填充满
    this.fit = true;
    //url,显示
    this.urls = "";
    //横向填充满
    this.fitColumns = true;
    //请求方法
    this.method = "post";
    //边框
    this.border = false;
    //id
    this.idField = 'id';
    //底部工具栏
    this.paginations = true;
    //行选中功能是否开启
    this.checkOnSelect = false;
    //点击框选择行
    this.selectOnCheck = false;
    //一次选择1行?
    this.singleSelect = true;
    //每页显示数量
    this.pageSize = 10;
    //显示列表
    this.pageList = [10, 15, 20, 25, 30];
    //数据表格
    this.columns;
    //工具条
    this.toolbar = '#toolbar';
    //条件查询的参数
    this.queryParams;
    //排序字段
    this.sortName = "id";
    //升序降序
    this.sortOrder = "desc";

    this.treeField = "name";
    //加载数据表格的方法
    this.init = function () {
        //作用域问题
        var parent = this;
        $(parent.id).treegrid({
            url: parent.urls,
            fit: parent.fit,
            method: parent.method,
            treeField: parent.treeField,
            fitColumns: parent.fitColumns,
            border: parent.border,
            idField: parent.idField,
            pagination: parent.paginations,
            checkOnSelect: parent.checkOnSelect,
            selectOnCheck: parent.selectOnCheck,
            singleSelect: parent.singleSelect,
            pageSize: parent.pageSize,
            pageList: parent.pageList,
            pagePosition: 'bottom',
            columns: parent.columns,
            toolbar: parent.toolbar,
            sortName: parent.sortName,
            sortOrder: parent.sortOrder
        });
    };
}

/**
 *
 * @param url   ajaxurl和参数
 * @param datagridId    datagrid的id
 */
function ajaxUrl(url, datagridId) {
    $.ajax({
        url: url,
        //data: params,
        type: "post",
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
            if (data.success && datagridId != null && datagridId != undefined) {
                $("#" + datagridId).datagrid('reload');
            }

        },
        error: function () {
        }
    });
}
/**
 * easyui dialog
 * @param datagridObj   数据表格对象
 * @param title dialog的标题,添加和修改用的
 */
util.easyui.dialog = function () {

    //dialogid
    this.dialogId = "#addOrEditDialog";
    //当前页面的datagridId用来获取数据表格的
    this.datagridId = "#datagrid";
    //宽度
    this.widths = 550;
    //高度
    this.heights = 350;

    this.cache = false;
    //锁定当前窗口
    this.modal = true;
    //dialog上面的form的id
    //dialog标题,url和类型(add和edit)
    this.formId = "#addOrEditForm";
    //用来区分树表格和普通表格
    this.datagridType = "datagrid";
    this.init = function (titles, href, dialogType) {

        var parent = this;
        if (dialogType == undefined || dialogType == null || dialogType == '') {
            alert('没有传入类型');
            return false;
        }

        //编辑做判断
        if (dialogType != 'add') {
            var rows = null;
            if (parent.datagridType == "datagrid") {
                rows = $(parent.datagridId).datagrid("getChecked");
            } else {
                rows = $(parent.datagridId).treegrid("getChecked");
            }

            if (rows == null || rows == '' || rows == undefined) {
                alert("请选择要编辑的数据!");
                return false;
            }

            if (rows.length != 1) {
                alert("一次只能编辑一条数据!");
                return false;
            }

            //主键固定id把
            var id = rows[0].id;
            href = href + "?id=" + id;


        }
        //dialog激活
        $(parent.dialogId).dialog({
            title: titles,
            width: parent.widths,
            height: parent.heights,
            //是否不可关闭这个是固定的,bug
            closed: false,
            cache: parent.cache,
            href: href,
            modal: parent.modal,
            buttons: [{
                text: '保存',
                handler: function () {
                    //找到dialog中的表单
                    var addOrEditForm = $(parent.dialogId).find(parent.formId);
                    //验证结果true为通过
                    var isValid = addOrEditForm.form("validate");
                    if (isValid) {
                        addOrEditForm.form({
                            onSubmit: function () {
                            },
                            success: function (data) {
                                //转json
                                var result = eval('(' + data + ')');
                                $(parent.dialogId).dialog("close", true);
                                //刷新数据表格..
                                if (parent.datagridType == "datagrid") {
                                    $(parent.datagridId).datagrid("reload");
                                } else {
                                    $(parent.datagridId).treegrid("reload");
                                }
                                //提示消息
                                $.messager.show({
                                    title: '系统提示',
                                    msg: result.msg,
                                    timeout: 3000,
                                    showType: 'slide'
                                });
                            }
                        });

                        addOrEditForm.submit();
                    }
                }
            }, {
                text: '关闭',
                handler: function () {
                    //关闭dialog
                    $(parent.dialogId).dialog("close", true);
                }
            }]

        });
    }

}
