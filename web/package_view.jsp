<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 2/22/2016
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <title></title>
</head>
<body>

<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
  <c:if test="${not empty packagee}">
    <input type="hidden" name="command" value="update_package">
    <h2>Passport ID:</h2>
    <input type="text" readonly name="package_id" value="${packagee.packageId}">
  </c:if>

  <c:if test="${empty packagee}">
    <input type="hidden" name="command" value="add_package">
  </c:if>

  <h2>Package type:</h2>
  <input type="text" name="type" value="${packagee.type}" placeholder="">

  <h2>Date:</h2>
  <input type="date" name="date" value="${packagee.date}">

  <h2>Sender name:</h2>
  <input type="text" name="senderName" value="${packagee.senderName}" placeholder="">

  <h2>Getter name:</h2>
  <input type="text" name="getterName" value="${packagee.getterName}" placeholder="">

  <h2>Address:</h2>
  <input type="text" name="address" value="${packagee.address}" placeholder="">

  <h2>Post index:</h2>
  <input type="number" name="postIndex" value="${packagee.postIndex}" placeholder="">

  <h2>Barcode:</h2>
  <input type="text" name="barcode" value="${packagee.barcode}" placeholder="">

  <input type="submit" value="apply">
</form>

</body>
</html>

</body>
</html>
