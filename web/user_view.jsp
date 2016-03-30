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
<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
    <c:if test="${not empty user}">
        <input type="hidden" name="user_id" value="${userr.id}">
        <input type="hidden" name="command" value="update_user">
    </c:if>

    <c:if test="${empty user}">
        <input type="hidden" name="command" value="add_user">
    </c:if>

    <h2>Логин:</h2>
    <input type="text" required name="login_field" value="${userr.login}">

    <h2>Фамилия:</h2>
    <input type="text" required name="sec_name" value="${userr.secondName}" placeholder="Иванов" maxlength="15">

    <h2>Имя:</h2>
    <input type="text" required name="first_name" value="${userr.firstName}" placeholder="Иван" maxlength="15">

    <h2>Отчество:</h2>
    <input type="text" required name="middle_name" value="${userr.middleName}" placeholder="Иванович" maxlength="15">

    <h2>Номер паспорта:</h2>
    <p>
        <select required size="1" name="passport_id">
            <c:choose>
                <c:when test="${empty passports}">
                    <option selected>${userr.passport.passportNumber}</option>
                </c:when>
                <c:otherwise>
                    <c:forEach var="passport" items="${passports}">
                        <c:choose>
                            <c:when test="${userr.passport.passportId==passport.passportId}">
                                <option selected value="${passport.passportId}">${passport.passportNumber}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${passport.passportId}">${passport.passportNumber}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
    </p>

    <h2>Уроень доступа:</h2>
    <p>
        <select required size="1" name="user_role">
            <c:forEach var="role" items="${user_roles}">
                <c:choose>
                    <c:when test="${userr.userRole==role}">
                        <option selected>${role}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${role}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>

    <input type="submit" value="применить">
</form>
</body>
</html>
