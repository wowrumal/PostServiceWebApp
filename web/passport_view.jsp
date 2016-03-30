<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/20/2016
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Паспорт</title>
</head>
<body>

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_passports">
    <input type="submit" value="назад">
</form>

<c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
        <form action="home_admin.jsp">
            <input type="submit" value="домой">
        </form>
    </c:when>

    <c:otherwise>
        <form action="home_manager.jsp">
            <input type="submit" value="домой">
        </form>
    </c:otherwise>
</c:choose>

<h1>Паспорт</h1>
<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
    <c:if test="${not empty passport}">
        <input type="hidden" name="command" value="update_passport">
        <input type="hidden" readonly name="passport_id" value="${passport.passportId}">
    </c:if>

    <c:if test="${empty passport}">
        <input type="hidden" name="command" value="add_passport">
    </c:if>

    <h2>Номер паспорта:</h2>
    <input type="text" required name="passport_number" value="${passport.passportNumber}" placeholder="KH2080553" maxlength="12">

    <h2>Адресс:</h2>
    <input type="text" required name="address" value="${passport.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

    <h2>Выдан:</h2>
    <input type="text" required name="institution" value="${passport.issuingInstitution}" placeholder="Октябрьский РОВД г. Гродно" maxlength="45">

    <h2>Действителен до:</h2>
    <input type="date" required name="issuing_date" value="${passport.issueDate}">

    <input type="submit" value="применить">
</form>
</body>
</html>
