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

<c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_prepayment_books">
            <input type="submit" value="back">
        </form>

        <form action="home_admin.jsp">
            <input type="submit" value="home">
        </form>
    </c:when>

    <c:when test="${user.userRole == 'CLIENT'}">
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="get_user_prepayment_books">
            <input type="submit" value="back">
        </form>

        <form action="home.jsp">
            <input type="submit" value="home">
        </form>
    </c:when>

    <c:otherwise>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_prepayment_books">
            <input type="submit" value="back">
        </form>

        <form action="home_manager.jsp">
            <input type="submit" value="home">
        </form>
    </c:otherwise>
</c:choose>

<form action="controller" method="post" enctype="multipart/form-data" accept-charset="UTF-8">

    <h2>Client name:</h2>
    <input type="text" name="client_name" value="${prepayment_book.clientName}" placeholder="Полищук Д.А." maxlength="45">

    <h2>Client number:</h2>
    <input type="text" name="client_number" value="${prepayment_book.clientNumber}" placeholder="28" maxlength="7">

    <c:choose>
        <c:when test="${user.userRole == 'CLIENT'}">
            <input type="hidden" name="passport_id" value="${user.passport.passportId}">
        </c:when>
        <c:otherwise>
            <input type="hidden" name="passport_id" value="${prepayment_book.passportId}">
        </c:otherwise>
    </c:choose>

    <h2>Unpaid cost:</h2>
    <input type="text" name="unpaid_cost" value="${prepayment_book.unpaidCost}" placeholder="50000" maxlength="10">

    <h2>Head of organization (name):</h2>
    <input type="text" name="organization_head_name" value="${prepayment_book.organizationHeadName}" placeholder="Стасюкевич С.Ю." maxlength="45">

    <h2>Bookkeeper:</h2>
    <input type="text" name="bookkeeper_name" value="${prepayment_book.bookkeeperName}" placeholder="Цивако К.А." maxlength="45">

    <h2>Date:</h2>
    <input type="date" name="date" value="${prepayment_book.date}">


    <c:choose>
        <c:when test="${(user.userRole == 'ADMIN') || (user.userRole == 'POST_MANAGER')}">
            <c:if test="${not empty prepayment_book}">
                <input type="hidden" name="command" value="update_prepayment_book">
                <input type="hidden" readonly name="prepayment_book_number" value="${prepayment_book.statementNumber}">
                <input type="submit" value="update">
            </c:if>
        </c:when>
    </c:choose>

    <c:if test="${empty prepayment_book}">
        <input type="hidden" name="command" value="add_prepayment_book">
        <input type="submit" value="add">
    </c:if>

</form>
</body>
</html>
