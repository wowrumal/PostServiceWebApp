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

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />


<div class="container">
  <h3>Мои извещения</h3>

  <ul class="collapsible" data-collapsible="expandable">
    <c:forEach var="advertisement" items="${advertisements}">
      <li>
        <div class="collapsible-header">
          <div class="row item-header">
            <span class="col offset-s1 s6"><b>Адрес:</b> ${advertisement.addressForGetting} </span>
            <span class="col s3 offset-s1"><b>Тип посылки:</b> ${advertisement.postPackage.type} </span>
            <i class="material-icons right">arrow_drop_down</i>
          </div>
        </div>
        <div class="collapsible-body item">
          <div class="row">
            <span class="col s6 offset-s1"><b>Вес посылки:</b> ${advertisement.weight} </span>
            <span class="col s3 package-align"><b>Стоимость:</b> ${advertisement.cost} </span>

            <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='dropdown${advertisement.postPackage.idPackage}'><i class="material-icons">file_download</i></a>

            <ul id='dropdown${advertisement.postPackage.idPackage}' class='dropdown-content'>
              <li><a href="doccontroller?command=advertisement_document&package_id=${advertisement.postPackage.idPackage}&doc_type=xls">XLS</a></li>
              <li><a href="doccontroller?command=advertisement_document&package_id=${advertisement.postPackage.idPackage}&doc_type=csv">CSV</a></li>
              <li><a href="doccontroller?command=advertisement_document&package_id=${advertisement.postPackage.idPackage}&doc_type=pdf">PDF</a></li>
            </ul>

          </div>
          <div class="row">
            <span class="col s6 offset-s1"><b>Дата:</b> ${advertisement.postPackage.date} </span>

            <form action="controller" method="get" enctype="multipart/form-data">
              <input type="hidden" name="command" value="delete_advertisement">
              <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
              <button type="submit" class="waves-effect waves-light btn col s1 red lighten-1 right btn-align">
                <i class="material-icons">delete</i>
              </button>
            </form>
          </div>

          <div class="row">
            <form action="controller" method="get" enctype="multipart/form-data">
              <input type="hidden" name="command" value="select_advertisement">
              <input type="hidden" name="sub_command" value="edit">
              <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
              <button type="submit" class="waves-effect waves-light btn col s1 green lighten-1 right btn-align">
                <i class="material-icons">mode_edit</i>
              </button>
            </form>
          </div>

        </div>
      </li>
    </c:forEach>
  </ul>

</div>

<jsp:include page="footer.jsp" />