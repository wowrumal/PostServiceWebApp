<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 2:01 PM
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
            <form action="home_admin.jsp">
                <input type="submit" value="home">
            </form>
        </c:when>
        <c:otherwise>
            <form action="home_manager.jsp">
                <input type="submit" value="home">
            </form>
        </c:otherwise>
    </c:choose>

    <table align="center" border="2">
        <tr>
            <th>Petition</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Passport</th>
            <th>Package</th>
            <th>Manager</th>
            <th>Date</th>
        </tr>
        <c:forEach var="statement" items="${search_statements}">
            <tr>
                <td>${statement.petitionContent}</td>
                <td>${statement.address}</td>
                <td>${statement.phoneNumber}</td>
                <td>${statement.passport.passportNumber}</td>
                <td>${statement.postPackage.type} - ${statement.postPackage.date}</td>
                <td>${statement.postManagerName}</td>
                <td>${statement.currentDate}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="select_search_statement">
                        <input type="hidden" name="search_statement_id" value="${statement.id}">
                        <input type="submit" value="open">
                    </form>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="delete_search_statement">
                        <input type="hidden" name="search_statement_id" value="${statement.id}">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>