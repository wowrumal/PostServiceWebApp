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
    <title></title>
</head>

<body>

    <form action="controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="load_search_statements">
        <input type="submit" value="back">
    </form>

    <form action="index.jsp">
        <input type="submit" value="home">
    </form>

    <form action="controller" accept-charset="UTF-8" method="post">
        <c:if test="${not empty search_statement}">
            <input type="hidden" name="command" value="update_search_statement">
            <h2>Statement ID:</h2>
            <input type="text" readonly name="search_statement_id" value="${search_statement.id}">
        </c:if>
        <c:if test="${empty search_statement}">
            <input type="hidden" name="command" value="add_search_statement">
        </c:if>

        <h2>Petition content:</h2>
        <textarea name="petition_content">${search_statement.petitionContent}</textarea>

        <h2>Address:</h2>
        <input type="text" name="search_statement_address" value="${search_statement.address}">

        <h2>Phone:</h2>
        <input type="text" name="phone_number" value="${search_statement.phoneNumber}">

        <h2>Package ID:</h2>
        <select size="1" name="package_id">
            <c:forEach var="id" items="${package_ids}">
                <c:choose>
                    <c:when test="${search_statement.postPackage.idPackage==id}">
                        <option selected>${id}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${id}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <h2>Passport ID:</h2>

        <select size="1" name="passport_id">
            <c:forEach var="id" items="${passport_ids}">
                <c:choose>
                    <c:when test="${search_statement.passport.passportId==id}">
                        <option selected>${id}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${id}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <h2>Manager:</h2>
        <input type="text" name="post_manager_name" value="${search_statement.postManagerName}">

        <h2>Date:</h2>
        <input type="date" name="date" value="${search_statement.currentDate}">

        <input type="submit" value="apply">
    </form>
</body>
</html>
