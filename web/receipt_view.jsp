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
    <title>Чек</title>
</head>
<body>

    <c:choose>
        <c:when test="${user.userRole == 'ADMIN'}">
        <div>
            <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="load_receipts">
                <input type="submit" value="назад">
            </form>

            <form style="display: inline-block;" action="home_admin.jsp">
                <input type="submit" value="домой">
            </form>
        </div>
        </c:when>

        <c:when test="${user.userRole == 'CLIENT'}">
        <div>
            <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_receipts">
                <input type="submit" value="назад">
            </form>

            <form style="display: inline-block;" action="home.jsp">
                <input type="submit" value="домой">
            </form>
        </div>
        </c:when>

        <c:otherwise>
        <div>
            <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="load_receipts">
                <input type="submit" value="назад">
            </form>

            <form style="display: inline-block;" action="home_manager.jsp">
                <input type="submit" value="домой">
            </form>
        </div>
        </c:otherwise>
    </c:choose>

    <h1>Чек</h1>
    <form action="controller" accept-charset="UTF-8" method="post">
        <input type="hidden" name="command" value="add_receipt">

        <h2>Имя плательщика:</h2>
        <input required type="text" name="receipt_clientname" value="${user.secondName} ${user.firstName}" maxlength="45">

        <h2>Услуга:</h2>
        <input required type="text" name="receipt_service" value="${receipt.serviceName}" maxlength="45">

        <h2>Данные оплаты:</h2>
        <input required type="text" name="receipt_payment_data" value="${receipt.paymentData}" maxlength="100">

        <h2>Стоимость:</h2>
        <input required type="number" min="1" name="receipt_cost" value="${receipt.cost}" maxlength="10">

        <%--<h2>User ID:</h2>
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
        </p>--%>
        <c:if test="${empty receipt}">
            <input type="submit" value="добавить">
        </c:if>

        <c:if test="${not empty param['message']}">
            <h2>Оплата отклонена :(</h2>
        </c:if>

    </form>
</body>
</html>
