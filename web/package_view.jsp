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

<c:choose>
  <c:when test="${user.userRole == 'ADMIN'}">
    <form action="controller" method="post" enctype="multipart/form-data">
      <input type="hidden" name="command" value="load_packages">
      <input type="submit" value="back">
    </form>

    <form action="home_admin.jsp">
      <input type="submit" value="home">
    </form>
  </c:when>

  <c:when test="${user.userRole == 'CLIENT'}">
    <form action="controller" method="post" enctype="multipart/form-data">
      <input type="hidden" name="command" value="get_packages_for_user">
      <input type="submit" value="back">
    </form>

    <form action="home.jsp">
      <input type="submit" value="home">
    </form>
  </c:when>

  <c:otherwise>
    <form action="controller" method="post" enctype="multipart/form-data">
      <input type="hidden" name="command" value="load_packages">
      <input type="submit" value="back">
    </form>

    <form action="home_manager.jsp">
      <input type="submit" value="home">
    </form>
  </c:otherwise>
</c:choose>


<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

  <h2>Package type:</h2>
  <input type="text" name="package_type" value="${packagee.type}" placeholder="Посылка" maxlength="45">

  <h2>Date:</h2>
  <input type="date" name="package_date" value="${packagee.date}" >

  <h2>Sender name:</h2>
  <input type="text" name="package_sender_name" value="${packagee.senderName}" placeholder="Стасюкевич С.Ю." maxlength="45">

  <h2>Getter name:</h2>
  <input type="text" name="package_getter_name" value="${packagee.getterName}" placeholder="Цивако К.А." maxlength="45">

  <h2>Address:</h2>
  <input type="text" name="package_address" value="${packagee.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

  <h2>Post index:</h2>
  <input type="number" name="package_post_index" value="${packagee.postIndex}" placeholder="33524" maxlength="7">

  <h2>Barcode:</h2>
  <input type="number" name="package_barcode" value="${packagee.barCode}" placeholder="4789623" maxlength="10">

  <c:if test="${user.userRole == 'ADMIN'}">
    <c:if test="${not empty packagee}">
      <input type="hidden" name="package_id" value="${packagee.idPackage}">
      <input type="hidden" name="command" value="update_package">
      <input type="submit" value="update">
    </c:if>
  </c:if>

  <c:if test="${empty packagee}">
    <input type="hidden" name="command" value="add_package">
    <input type="submit" value="create package">
  </c:if>
</form>

</body>
</html>

