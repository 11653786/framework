<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/24 0024
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/uploadPic.do" method="post"
      enctype="multipart/form-data">
    <input type="file" name="uploadFile"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
