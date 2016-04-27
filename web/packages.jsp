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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp"/>

    <div class="container">
        <h3>Посылки</h3>

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

                            <c:if test="${packagee.deleted == false}">
                                <form action="controller" enctype="multipart/form-data" method="post">
                                    <input type="hidden" name="command" value="delete_package">
                                    <input type="hidden" name="package_id" value="${packagee.idPackage}">
                                    <button type="submit" class="waves-effect waves-light btn col s1 red lighten-1">
                                        <i class="material-icons">delete</i>
                                    </button>
                                </form>
                            </c:if>
                        </div>

                        <div class="row">
                            <span class="col s6 offset-s1"><b>Почтовый индекс:</b> ${packagee.postIndex}</span>
                            <span class="col s3 package-align"><b>Адрес:</b> ${packagee.address} </span>

                            <c:if test="${(user.userRole == 'POST_MANAGER')}">
                                <c:forEach var="pack_id" items="${new_package_ids}">
                                    <c:if test="${pack_id == packagee.idPackage}">
                                        <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='managerdropdown${packagee.idPackage}'><i class="material-icons">check</i></a>

                                        <ul id='managerdropdown${packagee.idPackage}' class='dropdown-content'>
                                            <li>
                                                <a href="controller?command=PREPARE_DATA_FOR_CREATION_ADVERTISEMENT&package_id=${packagee.idPackage}">
                                                    <i class="material-icons">check</i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="controller?command=reject_package&package_id=${packagee.idPackage}">
                                                    <i class="material-icons">close</i>
                                                </a>
                                            </li>
                                        </ul>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        </div>

                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

<jsp:include page="footer.jsp" />
