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


util.easyui.datagrid = function () {
    //easyui数据表格的id
    this.id = "#datagrid";
    //是否填充满
    this.fit = true;
    //url,显示
    this.url = "/api/user/selectByPageList.do";
    //填充列
    this.fit = true;
    //请求方法
    this.method = "post";
    //边框
    this.border = false;
    //id
    this.idField = 'id';
    //底部工具栏
    this.pagination = true;
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
    this.columns = [[
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


    //加载数据表格的方法
    this.init = function () {
        //作用域问题
        var parent = this;
        $(parent.id).datagrid({
            url: parent.url,
            fit: parent.fit,
            method: parent.method,
            border: parent.border,
            idField: parent.idField,
            pagination: parent.pagination,
            checkOnSelect: parent.checkOnSelect,
            selectOnCheck: parent.selectOnCheck,
            singleSelect: parent.singleSelect,
            pageSize: parent.pageSize,
            pageList: parent.pageList,
            pagePosition: 'bottom',
            columns:parent.columns,
            toolbar: '#toolbar'
        });
    }
}
