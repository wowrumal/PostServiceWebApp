a<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 4/2/2016
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Извещение</title>
</head>
<body>
<h1>Извещение</h1>
<c:choose>
  <c:when test="${user.userRole == 'ADMIN'}">
    <div>
      <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="load_advertisements">
        <input type="submit" value="назад">
      </form>

      <form style="display: inline-block;" action="home_admin.jsp">
        <input type="submit" value="домой">
      </form>
    </div>
  </c:when>

  <c:when test="${user.userRole == 'CLIENT'}">
    <div>
      <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="get_user_advertisements">
        <input type="submit" value="назад">
      </form>

      <form style="display: inline-block;" action="home.jsp">
        <input type="submit" value="домой">
      </form>
    </div>
  </c:when>

  <c:otherwise>
    <div>
      <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="load_advertisements">
        <input type="submit" value="назад">
      </form>

      <form style="display: inline-block;" action="home_manager.jsp">
        <input type="submit" value="домой">
      </form>
    </div>
  </c:otherwise>
</c:choose>

<table>
  <tr>
    <td>Посылка:</td>
    <td><%--${advertisement.postPacakge}--%></td>
  </tr>
  <tr>
    <td>Вес посылки:</td>
    <td>${advertisement.weight}</td>
  </tr>
  <tr>
    <td>Стоимость доставки:</td>
    <td>${advertisement.cost}</td>
  </tr>
  <tr>
    <td>Получатель:</td>
    <td>${advertisement.passport}</td>
  </tr>
  <tr>
    <td>Адрес получателя:</td>
    <td>${advertisement.addressForGetting}</td>
  </tr>
</table>

</body>
</html>
