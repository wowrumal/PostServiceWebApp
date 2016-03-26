<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/24/2016
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Мои извещения</title>
</head>
<body>

<form action="home.jsp">
  <input type="submit" value="домой">
</form>
<h1>Мои извещения</h1>
<table align="center" border="2">
  <tr>
    <th>Адресс доставки</th>
    <th>Вес</th>
    <th>Стоимость доставки</th>
    <th>Тип посылки</th>
    <th>Дата</th>
    <th></th>
  </tr>
  <c:forEach var="advertisement" items="${advertisements}">
    <tr>
      <td>${advertisement.addressForGetting}</td>
      <td>${advertisement.weight}</td>
      <td>${advertisement.cost}</td>
      <td>${advertisement.postPackage.type}</td>
      <td>${advertisement.postPackage.date}</td>
      <td>
        <form action="controller" method="get" enctype="multipart/form-data">
          <input type="hidden" name="command" value="select_advertisement">
          <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
          <input type="submit" value="просмотреть">
        </form>
        <form action="controller" method="post" enctype="multipart/form-data">
          <input type="hidden" name="command" value="delete_advertisement">
          <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
          <input type="submit" value="удалить">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
