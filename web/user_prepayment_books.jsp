<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>МОИ Завяления авансовых книг</title>
</head>
<body>

<form action="home.jsp">
  <input type="submit" value="домой">
</form>
<h1>Список заявлений на восстановление авансовых книг</h1>
<table align="center" border="2">
  <tr>
    <th>Имя клиента</th>
    <th>Номер клиента</th>
    <th>Средства на счету</th>
    <th>Глава организации</th>
    <th>Бухгалтер</th>
    <th>Дата</th>
  </tr>
  <c:forEach var="book" items="${prepayment_books}">
    <tr>
      <td>${book.clientName}</td>
      <td>${book.clientNumber}</td>
      <td>${book.unpaidCost}</td>
      <td>${book.organizationHeadName}</td>
      <td>${book.bookkeeperName}</td>
      <td>${book.date}</td>
      <td>
        <form action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="select_prepayment_book">
          <input type="hidden" name="prepayment_book_number" value="${book.statementNumber}">
          <input type="submit" value="просмотреть">
        </form>
        <%--<form action="controller" enctype="multipart/form-data" method="post">
          <input type="hidden" name="command" value="delete_prepayment_book">
          <input type="hidden" name="prepayment_book_number" value="${book.statementNumber}">
          <input type="submit" value="delete">
        </form>--%>
      </td>
    </tr>
  </c:forEach>
</table>

<form action="controller" enctype="multipart/form-data" method="get">
  <input type="hidden" name="command" value="prepare_data_for_creation_prepayment_book">
  <input type="submit" value="создать заявление">
</form>
</body>
</html>
