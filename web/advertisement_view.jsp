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
<html>
<head>
    <title>Извещение</title>
</head>
<body>
<h1>Извещение о посылке</h1>

<FORM>
    <INPUT Type="button" VALUE="назад" onClick="history.go(-1);return true;">
</FORM>

<c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
        <%--<form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_advertisements">
            <input type="submit" value="назад">
        </form>--%>

        <form action="home_admin.jsp">
            <input type="submit" value="домой">
        </form>
    </c:when>

    <c:when test="${user.userRole == 'CLIENT'}">
        <%--<form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="get_user_advertisements">
            <input type="submit" value="назад">
        </form>--%>

        <form action="home.jsp">
            <input type="submit" value="домой">
        </form>
    </c:when>

    <c:otherwise>
        <%--<form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_advertisements">
            <input type="submit" value="назад">
        </form>--%>

        <form action="home_manager.jsp">
            <input type="submit" value="домой">
        </form>
    </c:otherwise>
</c:choose>

<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

    <h2>Адресс доставки:</h2>
    <input type="text" required name="package_address" value="${advertisement.addressForGetting}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

    <h2>Вес:</h2>
    <input type="number" min="1" required name="weight" value="${advertisement.weight}" placeholder="1000" maxlength="10">

    <h2>Стоимость доставки:</h2>
    <input type="number" min="1" required name="cost" value="${advertisement.cost}" placeholder="300000" maxlength="10">

    <c:choose>
        <c:when test="${user.userRole == 'POST_MANAGER'}">
            <c:if test="${empty advertisement}">
                <input type="hidden" name="command" value="add_advertisement">
                <input type="submit" value="создать">
                <input type="hidden" name="package_id" value="${package_id}">
            </c:if>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
            <input type="hidden" name="passport_id" value="${advertisement.passport.passportId}">
            <input type="hidden" name="command" value="update_advertisement">
            <input type="submit" value="обновить">
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
