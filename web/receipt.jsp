<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 4/2/2016
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Квитанция</title>
</head>
<body>
  <h1>Квитанция</h1>

  <c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
      <div>
        <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
          <input type="hidden" name="command" value="load_receipts">
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
          <input type="hidden" name="command" value="get_user_receipts">
          <input type="submit" value="назад">
        </form>

        <form style="display: inline-block;" action="home.jsp">
          <input type="submit" value="доиой">
        </form>
      </div>
    </c:when>

    <c:otherwise>
      <div>
        <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
          <input type="hidden" name="command" value="load_receipts">
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
            <td>Имя плательщика:</td>
            <td>${receipt.clientName}</td>
        </tr>
        <tr>
            <td>Услуга:</td>
            <td>${receipt.serviceName}</td>
        </tr>
        <tr>
            <td>Данные оплаты:</td>
            <td>${receipt.paymentData}</td>
        </tr>
        <tr>
            <td>Стоимость:</td>
            <td>${receipt.cost}</td>
        </tr>
        <tr>
            <td>Дата оплаты:</td>
            <td>${receipt.date}</td>
        </tr>
    </table>
  <hr/>
  <form action="doccontroller" method="get" enctype="multipart/form-data">
      <input type="hidden" name="command" value="receipt_document">
      <input type="hidden" name="receipt_id" value="${receipt.receiptId}">
      <select name="doc_type">
          <option value="pdf">pdf</option>
          <option value="xls">xls</option>
          <option value="csv">csv</option>
      </select>
      <input type="submit" value="скачать квитанцию">
  </form>
</body>
</html>
