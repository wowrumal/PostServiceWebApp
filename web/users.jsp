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
    <title>Пользователи</title>
</head>
<body>

    <form action="home_admin.jsp">
        <input type="submit" value="домой">
    </form>

    <table align="center" border="2">
        <tr>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Имя</th>
            <th>Паспорт</th>
            <th>Адрес</th>
            <th>Роль</th>
        </tr>
        <c:forEach var="userr" items="${users}">
            <tr>
                <td>${userr.login}</td>
                <td>${userr.password}</td>
                <td>${userr.secondName} ${userr.firstName} ${userr.middleName}</td>
                <td>${userr.passport.passportNumber}</td>
                <td>${userr.passport.address}</td>
                <td>${userr.userRole}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="get">
                        <input type="hidden" name="command" value="select_user">
                        <input type="hidden" name="user_id" value="${userr.id}">
                        <input type="submit" value="просмотреть">
                    </form>
                    <form action="controller" enctype="multipart/form-data" method="get">
                        <input type="hidden" name="command" value="select_user">
                        <input type="hidden" name="sub_command" value="update">
                        <input type="hidden" name="user_id" value="${userr.id}">
                        <input type="submit" value="изменить">
                    </form>
                    <c:choose>
                        <c:when test="${(userr.userRole == 'ADMIN')}">
                            <c:if test="${admins_count > 1}">
                                <form action="controller" enctype="multipart/form-data" method="get">
                                    <input type="hidden" name="command" value="delete_user">
                                    <input type="hidden" name="user_id" value="${userr.id}">
                                    <input type="submit" value="удалить">
                                </form>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <form action="controller" enctype="multipart/form-data" method="get">
                                <input type="hidden" name="command" value="delete_user">
                                <input type="hidden" name="user_id" value="${userr.id}">
                                <input type="submit" value="удалить">
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
