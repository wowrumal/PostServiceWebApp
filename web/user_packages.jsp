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

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
  <h3>Мои посылки</h3>

  <a href="controller?command=PREPARE_DATA_FOR_CREATION_PACKAGE&sub_command=create_package" class="btn waves-effect waves-light"><i class="material-icons">add</i></a>

  <ul class="collapsible" data-collapsible="expandable">
    <c:forEach var="packagee" items="${packages}">
      <li>
        <div class="collapsible-header">
          <div class="row item-header">
            <span class="col offset-s1 s6"><b>Код отслеживания:</b> ${packagee.trackNumber} </span>
            <c:if test="${packagee.deleted == true}">
              <span class="col s3 offset-s1"><b>Статус:</b> Удалена </span>
            </c:if>
            <c:if test="${packagee.deleted == false}">
              <span class="col s3 offset-s1"><b>Статус:</b> ${packagee.status} </span>
            </c:if>
            <i class="material-icons right">arrow_drop_down</i>
          </div>
        </div>
        <div class="collapsible-body item">
          <div class="row">
            <span class="col s6 offset-s1"><b>Тип посылки:</b> ${packagee.type}</span>
            <span class="col s3 offset-s1 package-align"><b>Дата:</b> ${packagee.date}</span>

            <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='dropdown${packagee.idPackage}'><i class="material-icons">file_download</i></a>

            <ul id='dropdown${packagee.idPackage}' class='dropdown-content'>
              <li><a href="doccontroller?command=package_document&package_id=${packagee.idPackage}&doc_type=xls">XLS</a></li>
              <li><a href="doccontroller?command=package_document&package_id=${packagee.idPackage}&doc_type=csv">CSV</a></li>
              <li><a href="doccontroller?command=package_document&package_id=${packagee.idPackage}&doc_type=pdf">PDF</a></li>
            </ul>

          </div>
          <div class="row">
            <span class="col s6 offset-s1"><b>Отправитель:</b> ${packagee.senderName}</span>
            <span class="col s3 package-align"><b>Получатель:</b> ${packagee.getterUser.secondName} ${packagee.getterUser.firstName}</span>
          </div>

          <div class="row">
            <span class="col s6 offset-s1"><b>Почтовый индекс:</b> ${packagee.postIndex}</span>
            <span class="col s3 package-align"><b>Адрес:</b> ${packagee.address} </span>
          </div>

        </div>
      </li>
    </c:forEach>
  </ul>
</div>

<jsp:include page="footer.jsp" />