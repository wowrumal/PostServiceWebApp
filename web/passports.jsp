<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/20/2016
  Time: 11:49 PM
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


    <form action="index.jsp">
        <input type="submit" value="home">
    </form>

    <table align="center" border="2">
        <tr>
            <th>Passport number</th>
            <th>Address</th>
            <th>Issuing institution</th>
            <th>Date of issue</th>
        </tr>
        <c:forEach var="passport" items="${passports}">
            <tr>
                <td>${passport.passportNumber}</td>
                <td>${passport.address}</td>
                <td>${passport.issuingInstitution}</td>
                <td>${passport.issueDate}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="select_passport">
                        <input type="hidden" name="passport_id" value="${passport.passportId}">
                        <input type="submit" value="open">
                    </form>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="delete_passport">
                        <input type="hidden" name="passport_id" value="${passport.passportId}">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="passport_view.jsp">
        <input type="submit" value="add passport">
    </form>
</body>
</html>
