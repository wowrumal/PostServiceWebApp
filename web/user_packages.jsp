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
  <title>Мои посылки</title>
</head>
<body>

<form action="home.jsp">
  <input type="submit" value="домой">
</form>
<h1>Мои посылки</h1>
<table align="center" border="2">
  <tr>
    <th>Тип посыли</th>
    <th>Дата</th>
    <th>Отправитель</th>
    <th>Получатель</th>
    <th>Адрес</th>
    <th>Почтовый индекс</th>
    <th>Статус посылки</th>
  </tr>

  <c:forEach var="packagee" items="${packages}">
    <tr>
      <td>${packagee.type}</td>
      <td>${packagee.date}</td>
      <td>${packagee.senderName}</td>
      <td>${packagee.getterUser.secondName} ${packagee.getterUser.firstName}</td>
      <td>${packagee.address}</td>
      <td>${packagee.postIndex}</td>
      <td>${packagee.status}</td>
      <td>
        <form action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="select_package">
          <input type="hidden" name="package_id" value="${packagee.idPackage}">
          <input type="submit" value="просмотреть">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<form action="controller" method="post" enctype="multipart/form-data">
  <input type="hidden" name="command" value="PREPARE_DATA_FOR_CREATION_PACKAGE">
  <input type="hidden" name="sub_command" value="create_package">
  <input type="submit" value="добавить посылку">
</form>

</body>
</html>
