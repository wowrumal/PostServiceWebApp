<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/22/2016
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
  <h3>Список квитанций</h3>
  <a href="controller?command=prepare_data_for_creation_receipt" class="btn waves-effect waves-light"><i class="material-icons">add</i></a>

  <ul class="collapsible" data-collapsible="expandable">
    <c:forEach var="receipt" items="${receipts}">
      <li>
        <div class="collapsible-header">
          <div class="row item-header">
            <span class="col offset-s1 s6"><b>Имя плательщика:</b> ${receipt.clientName} </span>
            <span class="col s3 offset-s1"><b>Стоимость:</b> ${receipt.cost} </span>
            <i class="material-icons right">arrow_drop_down</i>
          </div>
        </div>
        <div class="collapsible-body item">
          <div class="row">
            <span class="col s6 offset-s1"><b>Данные опдаты:</b> ${receipt.paymentData} </span>
            <span class="col s3 offset-s1 package-align"><b>Услуга:</b> ${receipt.serviceName} </span>

            <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='dropdown${receipt.receiptId}'><i class="material-icons">file_download</i></a>

            <ul id='dropdown${receipt.receiptId}' class='dropdown-content'>
              <li><a href="doccontroller?command=receipt_document&package_id=${receipt.receiptId}&doc_type=xls">XLS</a></li>
              <li><a href="doccontroller?command=receipt_document&package_id=${receipt.receiptId}&doc_type=csv">CSV</a></li>
              <li><a href="doccontroller?command=receipt_document&package_id=${receipt.receiptId}&doc_type=pdf">PDF</a></li>
            </ul>

          </div>
          <div class="row">
            <span class="col s6 offset-s1"><b>Дата:</b> ${receipt.date} </span>
          </div>

        </div>
      </li>
    </c:forEach>
  </ul>

</div>

<jsp:include page="footer.jsp" />