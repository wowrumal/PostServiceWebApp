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
    <input type="hidden" name="command" value="load_passports">
    <input type="submit" value="Паспорта">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_packages">
    <input type="submit" value="Посылки">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_users">
    <input type="submit" value="Клиенты">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_receipts">
    <input type="submit" value="Квитанции">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_advertisements">
    <input type="submit" value="Извещения">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_prepayment_books">
    <input type="submit" value="Заявления для оформления авансовой книжки">
</form>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_search_statements">
    <input type="submit" value="Заявления поиска посылки">
</form>


<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="logout_command">
    <input type="submit" value="выход">
</form>
</body>
</html>
