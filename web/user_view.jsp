<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/21/2016
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div class="container">
    <h3>Пользователь</h3>

    <form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

        <c:if test="${not empty user}">
            <input type="hidden" name="user_id" value="${userr.id}">
            <input type="hidden" name="passport_id" value="${userr.passport.passportId}">
            <input type="hidden" name="command" value="update_user">
        </c:if>

        <c:if test="${empty user}">
            <input type="hidden" name="command" value="add_user">
        </c:if>

        <div class="row">
            <div class="input-field col s6">
                <input required name="login_field" value="${userr.login}" id="login_field" type="text" class="validate">
                <label for="login_field">Логин</label>
            </div>

            <div class="input-field col s6">
                <input required name="sec_name" value="${userr.secondName}" placeholder="Иванов" maxlength="15" id="sec_name" type="text" class="validate">
                <label for="sec_name">Фамилия</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input required name="first_name" value="${userr.firstName}" placeholder="Иван" maxlength="15" id="first_name" type="text" class="validate">
                <label for="first_name">Имя</label>
            </div>

            <div class="input-field col s6">
                <input required name="middle_name" value="${userr.middleName}" placeholder="Иванович" maxlength="15" id="middle_name" type="text" class="validate">
                <label for="middle_name">Отчество</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input type="email" value="${userr.email}" required maxlength="50" name="email" placeholder="user@gmail.com" id="email" class="validate">
                <label for="email">E-mail</label>
            </div>

            <div class="input-field col s6">
                <input type="tel" pattern="(\+?\d[- .]*){7,13}" value="${userr.phone}" required maxlength="50" name="phone" placeholder="+375292847037" id="phone" class="validate">
                <label for="phone">Номер телефона</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input type="text" required name="passport_number" value="${userr.passport.passportNumber}" id="passport_number" class="validate">
                <label for="passport_number">Номер паспорта</label>
            </div>

            <div class="input-field col s6">
                <input type="text" required name="address" value="${userr.passport.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45" id="address" class="validate">
                <label for="address">Адрес</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input type="text" required name="institution" value="${userr.passport.issuingInstitution}" placeholder="Октябрьский РОВД г. Гродно" maxlength="45" id="institution" class="validate">
                <label for="institution">Выдан</label>
            </div>

            <div class="input-field col s6">
                <input type="date" required name="issuing_date" value="${userr.passport.issueDate}" id="issuing_date" class="datepicker">
                <label for="issuing_date">Действителен до</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <c:choose>
                    <c:when test="${userr.userRole == 'ADMIN'}">
                        <c:if test="${admins_count > 1}">
                            <select id="role" required size="1" name="user_role">
                            <c:forEach var="role" items="${user_roles}">
                                <c:choose>
                                    <c:when test="${userr.userRole==role}">
                                        <option selected>${role}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${role}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${admins_count <= 1}">
                            <p id="role">ADMIN</p>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                            <select id="role" required size="1" name="user_role">
                                <c:forEach var="role" items="${user_roles}">
                                    <c:choose>
                                        <c:when test="${userr.userRole==role}">
                                            <option selected>${role}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${role}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                    </c:otherwise>
                </c:choose>

                <label for="role">Уроень доступа</label>
            </div>
        </div>

        <div class="row right">
            <form>
                <button class="btn waves-effect waves-light" type="submit" name="action" onClick="history.go(-1);return true;">
                    <i class="material-icons">arrow_back</i>
                </button>
            </form>

            <button class="btn waves-effect waves-light" type="submit" name="action">
                <i class="material-icons">check</i>
            </button>

        </div>
    </form>
</div>

<jsp:include page="footer.jsp" />