<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/21/2016
  Time: 2:15 PM
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
            <th>Login</th>
            <th>Password</th>
            <th>Name</th>
            <th>Passport ID</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.secondName} ${user.firstName} ${user.middleName}</td>
                <td>${user.passport.passportId}</td>
                <td>${user.userRole}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="select_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="submit" value="open">
                    </form>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="delete_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="prepare_data_for_creation_user">
        <input type="submit" value="add user">
    </form>
</body>
</html>
