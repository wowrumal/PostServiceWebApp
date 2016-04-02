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
<html>
<head>
    <title>Завление поиска посылки</title>
</head>

<body>

<c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
        <div>
            <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="LOAD_SEARCH_STATEMENTS">
                <input type="submit" value="назад">
            </form>

            <form style="display: inline-block;" action="home_admin.jsp">
                <input type="submit" value="домой">
            </form>
        </div>
    </c:when>

    <c:when test="${user.userRole == 'CLIENT'}">
        <div>
            <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_search_statements">
                <input type="submit" value="назад">
            </form>

            <form style="display: inline-block;" action="home.jsp">
                <input type="submit" value="домой">
            </form>
        </div>
    </c:when>

    <c:otherwise>
        <div>
            <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="LOAD_SEARCH_STATEMENTS">
                <input type="submit" value="назад">
            </form>

            <form style="display: inline-block;" action="home_manager.jsp">
                <input type="submit" value="домой">
            </form>
        </div>
    </c:otherwise>
</c:choose>

<form action="controller" accept-charset="UTF-8" method="post">
    <h2>Содержание завления:</h2>
    <textarea required name="petition_content" placeholder="Утеряна посылка">${search_statement.petitionContent}</textarea>

    <h2>Адрес:</h2>
    <c:if test="${not empty search_statement.address}">
        <input required type="text" name="search_statement_address" value="${search_statement.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">
    </c:if>

    <c:if test="${empty search_statement.address}">
        <input required type="text" name="search_statement_address" value="${user.passport.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">
    </c:if>


    <h2>Телефон:</h2>
    <input required type="text" name="phone_number" value="${search_statement.phoneNumber}" placeholder="375291234567" maxlength="13">

    <c:if test="${user.userRole == 'CLIENT'}">
        <h2>Посылка:</h2>
        <select required size="1" name="package_id">
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
    </c:if>

    <c:if test="${user.userRole != 'CLIENT'}">
        <input type="hidden" readonly name="passport_id" value="${search_statement.passport.passportId}">
        <input type="hidden" name="package_id" value="${search_statement.postPackage.idPackage}">
    </c:if>

    <c:if test="${user.userRole == 'CLIENT'}">
        <input type="hidden" readonly name="passport_id" value="${user.passport.passportId}">
    </c:if>

    <h2>Менеджер:</h2>
    <input required type="text" name="post_manager_name" value="${search_statement.postManagerName}" placeholder="Стасюкевич С.Ю." maxlength="45">

    <c:if test="${not empty search_statement}">
        <h2>Дата:</h2>
        <input required type="date" name="date" value="${search_statement.currentDate}">
    </c:if>


    <c:choose>
        <c:when test="${user.userRole == 'CLIENT'}">
            <c:if test="${empty search_statement}">
                <input type="hidden" name="command" value="add_search_statement">
                <input type="submit" value="добавить">
            </c:if>
        </c:when>

        <c:otherwise>
            <c:if test="${not empty search_statement}">
                <input type="hidden" name="command" value="update_search_statement">
                <input type="hidden" readonly name="search_statement_id" value="${search_statement.id}">
                <input type="submit" value="обновить">
            </c:if>
        </c:otherwise>
    </c:choose>

</form>
</body>
</html>

