<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 19.03.2016
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
  <h1>Регистрация</h1>
  <form action="controller" method="post" enctype="multipart/form-data">

    <input type="hidden" name="command" value="REGISTRATION_COMMAND">

    <label>Логин:</label>
    <input type="text" required maxlength="44" name="login_field" placeholder="user123">
    <br>

    <label>Пароль:</label>
    <input type="password" required maxlength="44" name="password" placeholder="password1">
    <br>

    <label>Фамилия:</label>
    <input type="text" required maxlength="44" name="sec_name" placeholder="Иванов">
    <br>

    <label>Имя:</label>
    <input type="text" required maxlength="44" name="first_name" placeholder="Иван">
    <br>

    <label>Отчество:</label>
    <input type="text" required maxlength="44" name="middle_name" placeholder="Иванович">
    <br>

    <h2>Пасспортные данные</h2>

    <h2>Номер:</h2>
    <input type="text" name="passport_number" value="${passport.passportNumber}" placeholder="KH2080553">

    <h2>Адрес:</h2>
    <input type="text" name="address" value="${passport.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1">

    <h2>Выдан:</h2>
    <input type="text" name="institution" value="${passport.issuingInstitution}" placeholder="Октябрьский РОВД г. Гродно">

    <h2>Дата выдачи:</h2>
    <input type="date" name="issuing_date" value="${passport.issueDate}">
    <br>
    <br>
    <input type="submit" value="зарегистрироваться">
  </form>

  <c:if test="${not empty param['message']}">
    <c:choose>
      <c:when test="${param['message'] == 'login_exist'}">
        <p>Введенный логин уже существует</p>
      </c:when>
      <c:when test="${param['message'] == 'error'}">
        <p>Ошибка сервера, повторите попытку</p>
      </c:when>
      <c:when test="${param['message'] == 'invalid'}">
        <p>Проверьте введенные данные и повторите попытку</p>
      </c:when>
    </c:choose>

  </c:if>

</body>
</html>
