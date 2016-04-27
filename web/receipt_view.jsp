<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/22/2016
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
    <h3>Чек</h3>

    <form action="controller" accept-charset="UTF-8" method="post">
        <input type="hidden" name="command" value="add_receipt">

        <div class="row">
            <div class="input-field col s6">
                <input required type="text" name="receipt_clientname" value="${user.secondName} ${user.firstName}" maxlength="45" id="receipt_clientname" class="validate">
                <label for="receipt_clientname">Имя плательщика</label>
            </div>

            <div class="input-field col s6">
                <input required type="text" name="receipt_service" value="${receipt.serviceName}" maxlength="45" id="receipt_service" class="validate">
                <label for="receipt_service">Услуга</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input required type="text" name="receipt_payment_data" value="${receipt.paymentData}" maxlength="100" id="receipt_payment_data" class="validate">
                <label for="receipt_payment_data">Данные оплаты</label>
            </div>

            <div class="input-field col s6">
                <input required type="number" min="1" name="receipt_cost" value="${receipt.cost}" maxlength="10" id="receipt_cost" class="validate">
                <label for="receipt_cost">Стоимость</label>
            </div>
        </div>

        <div class="row right">

            <form>
                <button class="btn waves-effect waves-light" type="submit" name="action" onClick="history.go(-1);return true;">
                    <i class="material-icons">arrow_back</i>
                </button>
            </form>

            <c:if test="${empty receipt}">
                <button class="btn waves-effect waves-light" type="submit" name="action">
                    <i class="material-icons">check</i>
                </button>
            </c:if>

            <c:if test="${not empty param['message']}">
                <h2>Оплата отклонена :(</h2>
            </c:if>
        </div>
    </form>

</div>
        <%--<h2>User ID:</h2>
        <p>
            <select size="1" name="user_id">
                <c:forEach var="id" items="${user_ids}">
                    <c:choose>
                        <c:when test="${receipt.userId==id}">
                            <option selected>${id}</option>
                        </c:when>
                        <c:otherwise>
                            <option>${id}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </p>--%>
<jsp:include page="footer.jsp" />