<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
WELCOME ADMIN , ${user.firstName}!!!

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_passports">
    <input type="submit" value="load passports">
</form>

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_packages">
    <input type="submit" value="load packages">
</form>

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_users">
    <input type="submit" value="load users">
</form>

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_receipts">
    <input type="submit" value="load receipts">
</form>

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_advertisements">
    <input type="submit" value="load advertisements">
</form>

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_prepayment_books">
    <input type="submit" value="load statements of prepayment books">
</form>

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_search_statements">
    <input type="submit" value="load search statements">
</form>


<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="logout_command">
    <input type="submit" value="выход">
</form>
</body>
</html>
