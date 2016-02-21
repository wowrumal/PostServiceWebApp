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
<html>
<head>
    <title></title>
</head>
<body>
    <form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="command" value="update_user">
            <h2>User ID:</h2>
            <input type="text" readonly name="user_id" value="${user.id}">
        </c:if>

        <c:if test="${empty user}">
            <input type="hidden" name="command" value="add_user">
        </c:if>

        <h2>login:</h2>
        <input type="text" name="login_field" value="${user.login}">

        <h2>password:</h2>
        <input type="text" name="password" value="${user.password}">

        <h2>Second name:</h2>
        <input type="text" name="sec_name" value="${user.secondName}" placeholder="Иванов">

        <h2>First name:</h2>
        <input type="text" name="first_name" value="${user.firstName}" placeholder="Иван">

        <h2>Middle name:</h2>
        <input type="text" name="middle_name" value="${user.middleName}" placeholder="Иванович">

        <h2>Passport ID:</h2>
        <p>
            <select size="1" name="passport_id">
                <c:forEach var="id" items="${passport_ids}">
                    <option>${id}</option>
                </c:forEach>
            </select>
        </p>

        <h2>Role ID:</h2>
        <p>
            <select size="1" name="user_role">
                <c:forEach var="role" items="${user_roles}">
                    <option>${role}</option>
                </c:forEach>
            </select>
        </p>

        <input type="submit" value="apply">
    </form>
</body>
</html>
