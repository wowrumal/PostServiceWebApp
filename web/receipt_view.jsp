<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/22/2016
  Time: 2:37 PM
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
        <input type="hidden" name="command" value="load_receipts">
        <input type="submit" value="back">
    </form>

    <form action="index.jsp">
        <input type="submit" value="home">
    </form>

    <form action="controller" accept-charset="UTF-8" method="post">
        <input type="hidden" name="command" value="add_receipt">

        <c:if test="${not empty receipt}">
            <h2>Receipt ID:</h2>
            <input type="text" readonly value="${receipt.receiptId}">
        </c:if>

        <h2>Client name:</h2>
        <input type="text" name="receipt_clientname" value="${receipt.clientName}">

        <h2>Service:</h2>
        <input type="text" name="receipt_service" value="${receipt.serviceName}">

        <h2>Payment data:</h2>
        <input type="text" name="receipt_payment_data" value="${receipt.paymentData}">

        <h2>Cost:</h2>
        <input type="text" name="receipt_cost" value="${receipt.cost}">

        <h2>Date:</h2>
        <input type="date" name="receipt_date" value="${receipt.date}">

        <h2>User ID:</h2>
        <p>
            <select size="1" name="user_id">
                <c:forEach var="id" items="${user_ids}">
                    <c:choose>
                        <c:when test="${receipt.userId==id}">
                            <option selected>${id}</option>
                        </c:when>
                        <c:otherwise>
                            <option>${id}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="apply">
    </form>
</body>
</html>
