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
  <title>Мои чеки</title>
</head>
<body>


<form action="home.jsp">
  <input type="submit" value="домой">
</form>

<table align="center" border="2">
  <tr>
    <th>Имя плательщика</th>
    <th>Услуга:</th>
    <th>Данные оплаты</th>
    <th>Стоимость</th>
    <th>Дата</th>
  </tr>
  <c:forEach var="receipt" items="${receipts}">
    <tr>
      <td>${receipt.clientName}</td>
      <td>${receipt.serviceName}</td>
      <td>${receipt.paymentData}</td>
      <td>${receipt.cost}</td>
      <td>${receipt.date}</td>
      <td>
        <form action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="select_receipt">
          <input type="hidden" name="receipt_id" value="${receipt.receiptId}">
          <input type="submit" value="просмотреть">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<form action="controller" enctype="multipart/form-data" method="get">
  <input type="hidden" name="command" value="prepare_data_for_creation_receipt">
  <input type="submit" value="оплатить услугу">
</form>
</body>
</html>
