<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/21/2016
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Пользователь</title>
</head>
<body>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_users">
    <input type="submit" value="назад">
</form>

<form action="home_admin.jsp">
    <input type="submit" value="домой">
</form>
<h1>Пользователь</h1>

<table>
    <tr>
        <td>Логин:</td>
        <td>${userr.login}</td>
    </tr>
    <tr>
        <td>Фамилия</td>
        <td>${userr.secondName}</td>
    </tr>
    <tr>
        <td>Имя</td>
        <td>${userr.firstName}</td>
    </tr>
    <tr>
        <td>Отчество</td>
        <td>${userr.middleName}</td>
    </tr>
    <tr>
        <td>Адрес</td>
        <td>${userr.passport.address}</td>
    </tr>
    <tr>
        <td>Номер паспорта:</td>
        <td>${userr.passport.passportNumber}</td>
    </tr>
    <tr>
        <td>Выдан:</td>
        <td>${userr.passport.issuingInstitution}</td>
    </tr>
    <tr>
        <td>Действителен до:</td>
        <td>${userr.passport.issueDate}</td>
    </tr>
    <tr>
        <td>Уровень доступа:</td>
        <td>${userr.userRole}</td>
    </tr>
</table>

</body>
</html>
