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
<div>
    <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="load_users">
        <input type="submit" value="назад">
    </form>

    <form style="display: inline-block;" action="home_admin.jsp">
        <input type="submit" value="домой">
    </form>
</div>
<h1>Пользователь</h1>
<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
    <c:if test="${not empty user}">
        <input type="hidden" name="user_id" value="${userr.id}">
        <input type="hidden" name="passport_id" value="${userr.passport.passportId}">
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

    <h2>e-mail</h2>
    <input type="email" value="${userr.email}" required maxlength="50" name="email" placeholder="user@gmail.com">

    <h2>Номер телефона:</h2>
    <input type="tel" pattern="(\+?\d[- .]*){7,13}" value="${userr.phone}" required maxlength="50" name="phone" placeholder="+375292847037">

    <h2>Номер паспорта:</h2>
    <input type="text" required name="passport_number" value="${userr.passport.passportNumber}">

    <h2>Адрес:</h2>
    <input type="text" required name="address" value="${userr.passport.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

    <h2>Выдан:</h2>
    <input type="text" required name="institution" value="${userr.passport.issuingInstitution}" placeholder="Октябрьский РОВД г. Гродно" maxlength="45">

    <h2>Действителен до:</h2>
    <input type="date" required name="issuing_date" value="${userr.passport.issueDate}">

    <h2>Уроень доступа:</h2>

    <c:choose>
        <c:when test="${userr.userRole == 'ADMIN'}">
            <c:if test="${admins_count > 1}">
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
            </c:if>
            <c:if test="${admins_count <= 1}">
                <p>ADMIN</p>
            </c:if>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>

    <input type="submit" value="применить">
</form>
</body>
</html>
