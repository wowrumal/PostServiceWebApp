<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/22/2016
  Time: 2:22 PM
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


    <form action="home_admin.jsp">
        <input type="submit" value="home">
    </form>

    <table align="center" border="2">
        <tr>
            <th>Client name</th>
            <th>Service:</th>
            <th>Payment data</th>
            <th>Cost</th>
            <th>Date</th>
            <th>User id</th>
        </tr>
        <c:forEach var="receipt" items="${receipts}">
            <tr>
                <td>${receipt.clientName}</td>
                <td>${receipt.serviceName}</td>
                <td>${receipt.paymentData}</td>
                <td>${receipt.cost}</td>
                <td>${receipt.date}</td>
                <td>${receipt.userId}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="select_receipt">
                        <input type="hidden" name="receipt_id" value="${receipt.receiptId}">
                        <input type="submit" value="open">
                    </form>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="delete_receipt">
                        <input type="hidden" name="receipt_id" value="${receipt.receiptId}">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
