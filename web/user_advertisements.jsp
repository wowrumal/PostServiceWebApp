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
  <title></title>
</head>
<body>

<form action="home.jsp">
  <input type="submit" value="home">
</form>

<table align="center" border="2">
  <tr>
    <th>Destination address</th>
    <th>Weight</th>
    <th>Cost</th>
    <th>Package type</th>
    <th>Date</th>
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
        <form action="controller" method="post" enctype="multipart/form-data">
          <input type="hidden" name="command" value="select_advertisement">
          <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
          <input type="submit" value="open">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
