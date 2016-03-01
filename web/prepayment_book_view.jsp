<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 12:37 AM
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

<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="load_prepayment_books">
    <input type="submit" value="back">
</form>

<form action="index.jsp">
    <input type="submit" value="home">
</form>

<form action="controller" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
    <c:if test="${not empty prepayment_book}">
        <h2>Statement number</h2>
        <input type="hidden" name="command" value="update_prepayment_book">
        <input type="text" readonly name="prepayment_book_number" value="${prepayment_book.statementNumber}">
    </c:if>
    <c:if test="${empty prepaymnet_book}">
        <input type="hidden" name="command" value="add_prepayment_book">
    </c:if>

    <h2>Client name:</h2>
    <input type="text" name="client_name" value="${prepayment_book.clientName}" placeholder="Полищук Д.А." maxlength="45">

    <h2>Client number:</h2>
    <input type="text" name="client_number" value="${prepayment_book.clientNumber}" placeholder="28" maxlength="7">

    <h2>Passport ID:</h2>
    <p>
        <select size="1" name="passport_id">
            <c:forEach var="id" items="${passport_ids}">
                <c:choose>
                    <c:when test="${prepayment_book.passportId==id}">
                        <option selected>${id}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${id}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </p>

    <h2>Unpaid cost:</h2>
    <input type="text" name="unpaid_cost" value="${prepayment_book.unpaidCost}" placeholder="50000" maxlength="10">

    <h2>Head of organization (name):</h2>
    <input type="text" name="organization_head_name" value="${prepayment_book.organizationHeadName}" placeholder="Стасюкевич С.Ю." maxlength="45">

    <h2>Bookkeeper:</h2>
    <input type="text" name="bookkeeper_name" value="${prepayment_book.bookkeeperName}" placeholder="Цивако К.А." maxlength="45">

    <h2>Date:</h2>
    <input type="date" name="date" value="${prepayment_book.date}">

    <input type="submit" value="apply">

</form>
</body>
</html>
