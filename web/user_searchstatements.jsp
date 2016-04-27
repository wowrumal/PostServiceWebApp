<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
  <h3>Мои заявления поиска посылки</h3>
  <a href="controller?command=prepare_data_for_creation_search_statement" class="btn waves-effect waves-light"><i class="material-icons">add</i></a>

  <ul class="collapsible" data-collapsible="expandable">
    <c:forEach var="statement" items="${search_statements}">
      <li>
        <div class="collapsible-header">
          <div class="row item-header">
            <span class="col offset-s1 s6"><b>Посылка:</b> ${statement.postPackage.type} - ${statement.postPackage.date} </span>
            <span class="col s3 offset-s1"><b>Дата:</b> ${statement.currentDate} </span>
            <i class="material-icons right">arrow_drop_down</i>
          </div>
        </div>
        <div class="collapsible-body item">
          <div class="row">
            <span class="col s6 offset-s1"><b>Содержание заявления:</b> ${statement.petitionContent} </span>
            <span class="col s3 offset-s1 package-align"><b>Паспорт:</b> ${statement.passport.passportNumber} </span>

            <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='dropdown${statement.id}'><i class="material-icons">file_download</i></a>

            <ul id='dropdown${statement.id}' class='dropdown-content'>
              <li><a href="doccontroller?command=search_document&package_id=${statement.id}&doc_type=xls">XLS</a></li>
              <li><a href="doccontroller?command=search_document&package_id=${statement.id}&doc_type=csv">CSV</a></li>
              <li><a href="doccontroller?command=search_document&package_id=${statement.id}&doc_type=pdf">PDF</a></li>
            </ul>

          </div>
          <div class="row">
            <span class="col s6 offset-s1"><b>Адрес:</b> ${statement.address} </span>
            <span class="col s3 package-align"><b>Телефон:</b> ${statement.phoneNumber} </span>
          </div>

          <div class="row">
            <span class="col s6 offset-s1"><b>Менеджер:</b> ${statement.postManagerName} </span>
          </div>

        </div>
      </li>
    </c:forEach>
  </ul>
</div>

<jsp:include page="footer.jsp" />