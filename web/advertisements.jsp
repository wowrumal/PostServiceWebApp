<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/24/2016
  Time: 9:04 PM
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
            <th>Package ID</th>
            <th>Destination address</th>
            <th>Weight</th>
            <th>Cost</th>
            <th>Recipient (Passport ID)</th>
        </tr>
        <c:forEach var="advertisement" items="${advertisements}">
            <tr>
                <td>${advertisement.postPackage.idPackage}</td>
                <td>${advertisement.addressForGetting}</td>
                <td>${advertisement.weight}</td>
                <td>${advertisement.cost}</td>
                <td>${advertisement.passport.passportId}</td>
                <td>
                    <form action="controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="select_advertisement">
                        <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
                        <input type="submit" value="open">
                    </form>

                    <form action="controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="delete_advertisement">
                        <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="prepare_data_for_creation_advertisement">
        <input type="submit" value="add advertisement">
    </form>
</body>
</html>
