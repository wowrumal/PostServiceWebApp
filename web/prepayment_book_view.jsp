<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
    <h3>Заявление о восстановлении авансовой книжки</h3>

    <form action="controller" method="post" enctype="multipart/form-data" accept-charset="UTF-8">

        <div class="row">
            <div class="input-field col s6">
                <input type="text" required name="client_name" value="${prepayment_book.clientName}" placeholder="Полищук Д.А." maxlength="45" id="client_name" class="validate">
                <label for="client_name">Имя клиента</label>
            </div>

            <div class="input-field col s6">
                <input required name="client_number" value="${prepayment_book.clientNumber}" placeholder="28" maxlength="7" id="client_number" type="text" class="validate">
                <label for="client_number">Номер клиента</label>
            </div>
        </div>

        <div class="row">

            <c:if test="${not empty prepayment_book}">
                <div class="input-field col s6">
                    <input required name="date" value="${prepayment_book.date}" id="date" type="date" class="datepicker">
                    <label for="date">Дата</label>
                </div>
            </c:if>


            <c:choose>
                <c:when test="${user.userRole == 'CLIENT'}">
                    <input type="hidden" id="passport_id" name="passport_id" value="${user.passport.passportId}" class="validate">
                </c:when>
                <c:otherwise>
                    <input type="hidden" id="passport_id" name="passport_id" value="${prepayment_book.passportId}" class="validate">
                </c:otherwise>
            </c:choose>

            <div class="input-field col s6">
                <input type="number" min="1" required name="unpaid_cost" value="${prepayment_book.unpaidCost}" placeholder="50000" maxlength="10" id="unpaid_cost" class="validate">
                <label for="unpaid_cost">Сумма на счету</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input required name="organization_head_name" value="${prepayment_book.organizationHeadName}" placeholder="Стасюкевич С.Ю." maxlength="45" id="organization_head_name" type="text" class="validate">
                <label for="organization_head_name">Глава организации</label>
            </div>

            <div class="input-field col s6">
                <input required name="bookkeeper_name" value="${prepayment_book.bookkeeperName}" placeholder="Цивако К.А." maxlength="45" id="bookkeeper_name" type="text" class="validate">
                <label for="bookkeeper_name">Бухгалтер</label>
            </div>
        </div>

        <div class="row right">

            <form>
                <button class="btn waves-effect waves-light light-blue lighten-1" type="submit" name="action" onClick="history.go(-1);return true;">
                    <i class="material-icons">arrow_back</i>
                </button>
            </form>

            <c:choose>
                <c:when test="${(user.userRole == 'ADMIN') || (user.userRole == 'POST_MANAGER')}">
                    <c:if test="${not empty prepayment_book}">
                        <input type="hidden" name="command" value="update_prepayment_book">
                        <input type="hidden" readonly name="prepayment_book_number" value="${prepayment_book.statementNumber}">
                        <button class="btn waves-effect waves-light" type="submit" name="action">
                            <i class="material-icons">check</i>
                        </button>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:if test="${empty prepayment_book}">
                        <input type="hidden" name="command" value="add_prepayment_book">
                        <button class="btn waves-effect waves-light" type="submit" name="action">
                            <i class="material-icons">check</i>
                        </button>
                    </c:if>
                </c:otherwise>
            </c:choose>

        </div>

    </form>

</div>

<jsp:include page="footer.jsp" />