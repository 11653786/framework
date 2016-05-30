<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/24 0024
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/top.jsp" %>
<html>
<head>
    <title></title>
    <simple:Script hasJquery="true" hasEasyUi="true" hasAngularjs="false" hasBootStrap="false"
                   hasValid="false" hasYtUtil="false" hasYtResourcesCss="false" hasYtResourcesJs="false"
                   hasYtTreeExtends="false"></simple:Script>
    <%--上传文件需要的css和js--%>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/uploadify/uploadify.css">
</head>
<body>
    <input type="file" id="uploadFile" name="uploadFile"/>
    <img style="display: none" id="upload_org_code_img" src=""
         width="60" height="60"/>
    <input type="submit" value="提交"/>
</body>
</html>
<script type="text/javascript">
    $(function () {
        $("#uploadFile").uploadify(
                {
                    height: 30,
                    width: 120,
                    auto: true,
                    multi: false,
                    swf: "${pageContext.request.contextPath}/static/js/uploadify/uploadify.swf",
                    uploader: "${pageContext.request.contextPath}/uploadPic.do",
                    fileObjName: "uploadFile",
                    fileTypeExts: "*.jpg;*.jpge;*.gif;*.png",
                    fileSizeLimit: "2MB",
                    //这个就是所谓的回调
                    onUploadSuccess: function (file, data, response) {
                        $("#upload_org_code_img").attr("src", "123");
                        $("#upload_org_code_img").show();
                        $("#img_path_id").val("456");
                    }
                });
    });
</script>
