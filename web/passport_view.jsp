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
    <title></title>
</head>
<body>
    <form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
        <c:if test="${not empty passport}">
            <input type="hidden" name="command" value="update_passport">
            <h2>Passport ID:</h2>
            <input type="text" readonly name="passport_id" value="${passport.passportId}">
        </c:if>

        <c:if test="${empty passport}">
            <input type="hidden" name="command" value="add_passport">
        </c:if>

        <h2>Passport number:</h2>
        <input type="text" name="passport_number" value="${passport.passportNumber}" placeholder="KH2080553">

        <h2>Address:</h2>
        <input type="text" name="address" value="${passport.address}" placeholder="г. Гродно, ул. Гстелло 17, кв. 1">

        <h2>Issuing institution:</h2>
        <input type="text" name="institution" value="${passport.issuingInstitution}" placeholder="Октябрьский РОВД г. Гродно">

        <h2>Date of issue:</h2>
        <input type="date" name="issuing_date" value="${passport.issueDate}">

        <input type="submit" value="apply">
    </form>
</body>
</html>
