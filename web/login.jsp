<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<h1>Вход:</h1>
<form method="POST" action="controller">
  <input type="hidden" name="command" value="login_command">

  <div>
    <input type="text" name="login_field" id="loginInput" placeholder="Логин">
  </div>

  <div>
    <input type="password" name="password" id="passwordInput" placeholder="Пароль">
  </div>

  <button type="submit">Войти</button>

  <c:if test="${not empty param['message']}">
    <p>invalid login or password</p>
  </c:if>
</form>

<a href="registration.jsp">Зарегестрироваться</a>
</body>
</html>
