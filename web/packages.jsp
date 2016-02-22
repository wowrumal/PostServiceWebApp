<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 2/21/2016
  Time: 10:14 PM
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
  <table align="center" border="2">
    <tr>
      <th>Type</th>
      <th>Date</th>
      <th>Sender Name</th>
      <th>Getter Name</th>
      <th>Address</th>
      <th>Post Index</th>
      <th>Barcode</th>
    </tr>

    <c:forEach var="package" items="${packages}">
    <tr>
      <td>$(package.type)</td>
      <td>$(package.date)</td>
      <td>$(package.senderName)</td>
      <td>$(package.getterName)</td>
      <td>$(package.address)</td>
      <td>$(package.postIndex)</td>
      <td>$(package.barcode)</td>
      <td>
        <form action="controller" enctype="multipart/form-data" method="post">
          <input type="hidden" name="command" value="select_package">
          <input type="hidden" name="package_id" value="${package.packageId}">
          <input type="submit" value="open">
        </form>
        <form action="controller" enctype="multipart/form-data" method="post">
          <input type="hidden" name="command" value="delete_package">
          <input type="hidden" name="package_id" value="${package.packageId}">
          <input type="submit" value="delete">
        </form>
      </td>
    </tr>
    </c:forEach>
  </table>

  <form action="package_view.jsp">
    <input type="submit" value="add package">
  </form>

</body>
</html>
