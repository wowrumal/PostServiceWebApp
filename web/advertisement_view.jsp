<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/24/2016
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">

    <h3>Извещение</h3>

    <form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

        <div class="row">
            <div class="col s4">
                <b>Отправитель:</b> ${packagee.senderName}
            </div>

            <div class="col s4">
                <b>Получатель:</b> ${packagee.getterUser.secondName} ${packagee.getterUser.firstName}
            </div>

            <div class="col s4">
                <b>Дата отправления:</b> ${packagee.date}
            </div>
        </div>

        <div class="row">
            <div class="input-field col s4">
                <c:if test="${not empty advertisement}">
                    <input type="text" id="package_address" class="validate" required name="package_address" value="${advertisement.addressForGetting}"/>
                </c:if>
                <c:if test="${empty advertisement}">
                    <input type="text" id="package_address" class="validate" required name="package_address" value="${passport.address}"/>
                </c:if>
                <label for="package_address">Адрес доставки</label>
            </div>

            <div class="input-field col s4">
                <input type="number" id="weight" min="1" class="validate" required name="weight" value="${advertisement.weight}" maxlength="10"/>
                <label for="weight">Вес</label>
            </div>

            <div class="input-field col s4">
                <input type="number" id="cost" min="1" class="validate" required name="cost" value="${advertisement.cost}" maxlength="10">
                <label for="cost">Стоимость доставки</label>
            </div>
        </div>

        <div class="row right">

            <form>
                <button class="btn waves-effect waves-light" type="submit" name="action" onClick="history.go(-1);return true;">
                    <i class="material-icons">arrow_back</i>
                </button>
            </form>

            <c:choose>
                <c:when test="${user.userRole == 'POST_MANAGER'}">
                    <c:if test="${empty advertisement}">
                        <input type="hidden" name="command" value="add_advertisement">
                        <input type="hidden" name="package_id" value="${package_id}">
                        <button class="btn waves-effect waves-light" type="submit" name="action">
                            <i class="material-icons">check</i>
                        </button>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
                    <input type="hidden" name="passport_id" value="${advertisement.passport.passportId}">
                    <input type="hidden" name="command" value="update_advertisement">
                    <button class="btn waves-effect waves-light" type="submit" name="action">
                        <i class="material-icons">check</i>
                    </button>
                </c:otherwise>
            </c:choose>
        </div>
    </form>

</div>

<jsp:include page="footer.jsp" />