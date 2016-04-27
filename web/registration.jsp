<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 19.03.2016
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />

    <nav class="nav-wrapper blue darken-1">
        <a href="login.jsp" class="brand-logo">ПАШТОВЫ СЕРВИС</a>

        <div class="row right">
            <a href="login.jsp" class="waves-effect waves-light btn"><i class="material-icons auth">input</i></a>
            <a href="registration.jsp" class="waves-effect waves-light btn deep-orange lighten-1"><i class="material-icons auth">person_add</i></a>
        </div>
    </nav>

    <div class="row container">
        <h3>Регистрация</h3>
        <form class="col s12" action="controller" method="post" enctype="multipart/form-data">

            <input type="hidden" name="command" value="REGISTRATION_COMMAND">

            <div class="row">
                <div class="input-field col s4">
                    <i class="material-icons prefix register">email</i>
                    <input id="login_field" type="text" value="${user.login}" class="validate" required maxlength="44" name="login_field">
                    <label for="login_field">Логин</label>
                </div>

                <div class="input-field col s4">
                    <i class="material-icons prefix register">vpn_key</i>
                    <input id="password" type="password" class="validate" required maxlength="44" name="password">
                    <label for="password">Пароль</label>
                </div>

                <div class="input-field col s4">
                    <i class="material-icons prefix register">email</i>
                    <input id="email" type="email" class="validate" value="${user.email}" required maxlength="50" name="email">
                    <label for="email">Email</label>
                </div>
            </div>

            <div class="row">

                <div class="input-field col s4">
                    <i class="material-icons prefix register">account_circle</i>
                    <input id="sec_name" type="text" class="validate" value="${user.secondName}" name="sec_name" required maxlength="44">
                    <label for="sec_name">Фамилия</label>
                </div>

                <div class="input-field col s4">
                    <input id="first_name" type="text" class="validate" value="${user.firstName}" required maxlength="44" name="first_name">
                    <label for="first_name">Имя</label>
                </div>

                <div class="input-field col s4">
                    <input id="middle_name" type="text" class="validate" value="${user.middleName}" required maxlength="44" name="middle_name">
                    <label for="middle_name">Отчество</label>
                </div>
            </div>

            <div class="row">

            </div>

            <h4>Паспортные данные</h4>

            <div class="row">
                <div class="input-field col s6">
                    <input id="passportNumber" type="text" class="validate" name="passport_number" value="${passport.passportNumber}">
                    <label for="passportNumber">Номер</label>
                </div>

                <div class="input-field col s6">
                    <input id="address" type="text" class="validate" name="address" value="${passport.address}">
                    <label for="address">Адрес</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6">
                    <input id="institution" type="text" class="validate" name="institution" value="${passport.issuingInstitution}">
                    <label for="institution">Кем выдан</label>
                </div>

                <div class="input-field col s6">
                    <input type="date" class="datepicker" id="issuing_date" name="issuing_date" value="${passport.issueDate}">
                    <label for="issuing_date">Дата выдачи</label>
                </div>
            </div>

            <div class="row right">
                <button class="btn waves-effect waves-light" type="submit" name="action">
                    <i class="material-icons">check</i>
                </button>
            </div>

        </form> <!-- End register form -->

        <c:if test="${not empty param['message']}">
            <c:choose>
                <c:when test="${param['message'] == 'login_exist'}">
                    <p>Введенный логин уже существует</p>
                </c:when>
                <c:when test="${param['message'] == 'error'}">
                    <p>Ошибка сервера, повторите попытку</p>
                </c:when>
                <c:when test="${param['message'] == 'invalid'}">
                    <p>Проверьте введенные данные и повторите попытку</p>
                </c:when>
            </c:choose>

        </c:if>

    </div>

<jsp:include page="footer.jsp" />