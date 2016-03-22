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
  <title></title>
</head>
<body>

<form action="home.jsp">
  <input type="submit" value="home">
</form>

<table align="center" border="2">
  <tr>
    <th>Statement number</th>
    <th>Client name</th>
    <th>Client number</th>
    <th>Unpaid cost</th>
    <th>Passport ID</th>
    <th>Head of organization</th>
    <th>Bookkeeper</th>
    <th>Date</th>
  </tr>
  <c:forEach var="book" items="${prepayment_books}">
    <tr>
      <td>${book.statementNumber}</td>
      <td>${book.clientName}</td>
      <td>${book.clientNumber}</td>
      <td>${book.unpaidCost}</td>
      <td>${book.passportId}</td>
      <td>${book.organizationHeadName}</td>
      <td>${book.bookkeeperName}</td>
      <td>${book.date}</td>
      <td>
        <form action="controller" enctype="multipart/form-data" method="post">
          <input type="hidden" name="command" value="select_prepayment_book">
          <input type="hidden" name="prepayment_book_number" value="${book.statementNumber}">
          <input type="submit" value="open">
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

<form action="controller" enctype="multipart/form-data" method="post">
  <input type="hidden" name="command" value="prepare_data_for_creation_prepayment_book">
  <input type="submit" value="add prepayment book statement">
</form>
</body>
</html>
