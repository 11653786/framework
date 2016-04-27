<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" charset="utf-8">
</script>
<div style="float:left;">
    <img src="${pageContext.request.contextPath}/static/images/logo2.png"/>
</div>
<div style="float:left;">
    <h1>东融影业综合业务管理系统</h1>
</div>
<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 4px;" class="alert alert-info">
    <c:if test="${loginEmployee != null}">【<strong>${loginEmployee.userName}</strong>】,欢迎您</c:if>
</div>

<div style="position: absolute;right: 0px; bottom: 0px;" data-options="border:false">
    <a href="javascript:void(0);" class="easyui-menubutton"
       data-options="menu:'#layout_north_zxMenu',iconCls:'status_busy'">注销</a>
</div>

<div id="layout_north_zxMenu" style="width: 100px; display: none;">
    <div id="updatePass">修改密码</div>
    <div id="layout">退出系统</div>
</div>
<div id="updialog"></div>
<script type="text/javascript">

    $("#updatePass").click(function () {
        $('#updialog').dialog({
            title: '修改密码',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            href: '${pageContext.request.contextPath}/editCurrentUserPwdPage.do',
            modal: true,
            buttons: [{
                text: '修改',
                handler: function () {
                    //找到dialog中的表单
                    var addOrEditForm = $("#updialog").find("#addOrEditForm");
                    //验证结果true为通过
                    var isValid = addOrEditForm.form("validate");
                    if (isValid) {
                        addOrEditForm.form({
                            onSubmit: function () {
                            },
                            success: function (data) {
                                //转json
                                var result = eval('(' + data + ')');
                                $("#updialog").dialog("close", true);
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
                    $("#updialog").dialog("close", true);
                }
            }]
        });
    });


    $("#layout").click(function () {

        $.ajax({
            url: "${pageContext.request.contextPath}/loginOut.do",
            //data: params,
            type: "post",
            dataType: 'json',
            success: function (data) {
                location.href = "${pageContext.request.contextPath}/index.do";
            },
            error: function () {
            }
        });
    });

</script>