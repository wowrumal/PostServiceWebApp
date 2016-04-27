<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
    <h3>Заявление поиска посылки</h3>

    <form action="controller" accept-charset="UTF-8" method="post">

        <div class="row">

            <div class="input-field col s6">
                <textarea id="petition_content" class="materialize-textarea" required name="petition_content" placeholder="Утеряна посылка">${search_statement.petitionContent}</textarea>
                <label for="petition_content">Содержание завления</label>
            </div>

            <div class="input-field col s6">
                <c:if test="${not empty search_statement.address}">
                    <input required type="text" name="search_statement_address" value="${search_statement.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45" id="search_statement_address" class="validate">
                </c:if>

                <c:if test="${empty search_statement.address}">
                    <input required type="text" name="search_statement_address" value="${user.passport.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45" id="search_statement_address" class="validate">
                </c:if>
                <label for="search_statement_address">Адрес</label>
            </div>

        </div>

        <div class="row">
            <div class="input-field col s6">
                <input required type="text" name="phone_number" value="${search_statement.phoneNumber}" placeholder="375291234567" maxlength="13" id="phone_number" class="validate">
                <label for="phone_number">Телефон</label>
            </div>

            <c:if test="${user.userRole == 'CLIENT'}">
                <div class="input-field col s6">
                    <select  required size="1" name="package_id" id="package_id">
                        <c:forEach var="packagee" items="${packages}">
                            <c:choose>
                                <c:when test="${search_statement.postPackage.idPackage==packagee.idPackage}">
                                    <option selected value="${packagee.idPackage}">${packagee.type} - ${packagee.date}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${packagee.idPackage}">${packagee.type} - ${packagee.date}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <label for="package_id">Посылка</label>
                </div>
            </c:if>

        </div>

        <c:if test="${user.userRole != 'CLIENT'}">
            <input type="hidden" readonly name="passport_id" value="${search_statement.passport.passportId}">
            <input type="hidden" name="package_id" value="${search_statement.postPackage.idPackage}">
        </c:if>

        <c:if test="${user.userRole == 'CLIENT'}">
            <input type="hidden" readonly name="passport_id" value="${user.passport.passportId}">
        </c:if>

        <div class="row">

            <div class="input-field col s6">
                <input required type="text" name="post_manager_name" value="${search_statement.postManagerName}" placeholder="Стасюкевич С.Ю." maxlength="45" id="post_manager_name" class="validate">
                <label for="post_manager_name">Менеджер</label>
            </div>

            <c:if test="${not empty search_statement}">
                <div class="input-field col s6">
                    <input required type="date" name="date" value="${search_statement.currentDate}" id="date" class="datepicker">
                    <label for="date">Дата</label>
                </div>
            </c:if>

        </div>

        <div class="row right">

            <form>
                <button class="btn waves-effect waves-light" type="submit" name="action" onClick="history.go(-1);return true;">
                    <i class="material-icons">arrow_back</i>
                </button>
            </form>

            <c:choose>
                <c:when test="${user.userRole == 'CLIENT'}">
                    <c:if test="${empty search_statement}">
                        <input type="hidden" name="command" value="add_search_statement">
                        <button class="btn waves-effect waves-light" type="submit" name="action">
                            <i class="material-icons">check</i>
                        </button>
                    </c:if>
                </c:when>

                <c:otherwise>
                    <c:if test="${not empty search_statement}">
                        <input type="hidden" name="command" value="update_search_statement">
                        <input type="hidden" readonly name="search_statement_id" value="${search_statement.id}">
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
