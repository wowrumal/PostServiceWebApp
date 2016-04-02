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
    <title>Админ-домашняя</title>
</head>
<body>
<h1>Здравствуйте админ, ${user.firstName}!!!</h1>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_packages">
    <input type="submit" value="посылки">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_users">
    <input type="submit" value="клиенты">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_receipts">
    <input type="submit" value="чеки">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_advertisements">
    <input type="submit" value="извещения">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_prepayment_books">
    <input type="submit" value="заявления для оформления авансовой книжки">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_search_statements">
    <input type="submit" value="заявления поиска посылки">
</form>


<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="logout_command">
    <input type="submit" value="выход">
</form>
</body>
</html>
