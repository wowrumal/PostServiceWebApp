<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
WELCOME MANAGER , ${user.firstName}!!!

<form action="controller" method="get" enctype="multipart/form-data">
  <input type="hidden" name="command" value="logout_command">
  <input type="submit" value="выход">
</form>
</body>
</html>
