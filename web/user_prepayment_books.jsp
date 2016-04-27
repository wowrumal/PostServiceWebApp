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

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
  <h3>Список заявлений на восстановление авансовых книг</h3>
  <a href="controller?command=prepare_data_for_creation_prepayment_book" class="btn waves-effect waves-light"><i class="material-icons">add</i></a>

  <ul class="collapsible" data-collapsible="expandable">
    <c:forEach var="book" items="${prepayment_books}">
      <li>
        <div class="collapsible-header">
          <div class="row item-header">
            <span class="col offset-s1 s6"><b>Организация:</b> ${book.organizationHeadName} </span>
            <span class="col s3 offset-s1"><b>Средства на счету:</b> ${book.unpaidCost} </span>
            <i class="material-icons right">arrow_drop_down</i>
          </div>
        </div>
        <div class="collapsible-body item">
          <div class="row">
            <span class="col s6 offset-s1"><b>Имя клиента:</b> ${book.clientName}</span>
            <span class="col s3 offset-s1 package-align"><b>Номер клиента:</b> ${book.clientNumber}</span>

            <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='dropdown${book.clientNumber}'><i class="material-icons">file_download</i></a>

            <ul id='dropdown${book.clientNumber}' class='dropdown-content'>
              <li><a href="doccontroller?command=prepayment_book_document&prepayment_book_number=${book.statementNumber}&doc_type=xls">XLS</a></li>
              <li><a href="doccontroller?command=prepayment_book_document&prepayment_book_number=${book.statementNumber}&doc_type=csv">CSV</a></li>
              <li><a href="doccontroller?command=prepayment_book_document&prepayment_book_number=${book.statementNumber}&doc_type=pdf">PDF</a></li>
            </ul>

          </div>
          <div class="row">
            <span class="col s6 offset-s1"><b>Бухгалтер:</b> ${book.bookkeeperName}</span>
            <span class="col s3 package-align"><b>Дата:</b> ${book.date} </span>
          </div>

        </div>
      </li>
    </c:forEach>
  </ul>
</div>

<jsp:include page="footer.jsp" />