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
    <title></title>
</head>
<body>

<c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_advertisements">
            <input type="submit" value="back">
        </form>

        <form action="home_admin.jsp">
            <input type="submit" value="home">
        </form>
    </c:when>

    <c:when test="${user.userRole == 'CLIENT'}">
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="get_user_advertisement">
            <input type="submit" value="back">
        </form>

        <form action="home.jsp">
            <input type="submit" value="home">
        </form>
    </c:when>

    <c:otherwise>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_advertisements">
            <input type="submit" value="back">
        </form>

        <form action="home_manager.jsp">
            <input type="submit" value="home">
        </form>
    </c:otherwise>
</c:choose>

<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

    <h2>Destination address:</h2>
    <input type="text" name="package_address" value="${advertisement.addressForGetting}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

    <h2>Weight:</h2>
    <input type="text" name="weight" value="${advertisement.weight}" placeholder="1000" maxlength="10">

    <h2>Cost:</h2>
    <input type="text" name="cost" value="${advertisement.cost}" placeholder="300000" maxlength="10">

    <c:if test="${(user.userRole == 'POST_MANAGER') || (user.userRole == 'ADMIN')}">
        <c:if test="${not empty advertisemente}">
            <input type="hidden" name="PACKAGE_ID" value="${advertisement.postPackage.idPackage}">
            <input type="hidden" name="command" value="update_advertisement">
            <input type="submit" value="update">
        </c:if>
    </c:if>

    <c:if test="${empty advertisement}">
        <input type="hidden" name="command" value="add_package">
        <input type="submit" value="add_advertisement">
    </c:if>
</form>
</body>
</html>
