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
    <title>Извещения</title>
</head>
<body>

    <c:choose>
        <c:when test="${user.userRole == 'ADMIN'}">
            <form action="home_admin.jsp">
                <input type="submit" value="домой">
            </form>
        </c:when>
        <c:otherwise>
            <form action="home_manager.jsp">
                <input type="submit" value="домой">
            </form>
        </c:otherwise>
    </c:choose>


    <table align="center" border="2">
        <tr>
            <th>Тип посылки</th>
            <th>Адрес доставки</th>
            <th>Вес</th>
            <th>Стоимость доставки</th>
            <th>Номер паспорта</th>
        </tr>
        <c:forEach var="advertisement" items="${advertisements}">
            <tr>
                <td>${advertisement.postPackage.type}</td>
                <td>${advertisement.addressForGetting}</td>
                <td>${advertisement.weight}</td>
                <td>${advertisement.cost}</td>
                <td>${advertisement.passport.passportNumber}</td>
                <td>
                    <form action="controller" method="get" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="select_advertisement">
                        <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
                        <input type="submit" value="просмотреть">
                    </form>

                    <form action="controller" method="get" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="select_advertisement">
                        <input type="hidden" name="sub_command" value="edit">
                        <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
                        <input type="submit" value="изменить">
                    </form>

                    <form action="controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="delete_advertisement">
                        <input type="hidden" name="package_id" value="${advertisement.postPackage.idPackage}">
                        <input type="submit" value="удалить">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <%--<c:if test="${user.userRole == 'POST_MANAGER'}">
        <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="prepare_data_for_creation_advertisement">
            <input type="submit" value="создать извещение">
        </form>
    </c:if>--%>

</body>
</html>
