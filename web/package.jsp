<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Посылка</title>
</head>
<body>
<h1>Посылка</h1>
<c:choose>
    <c:when test="${user.userRole == 'ADMIN'}">
    <div>
        <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_packages">
            <input type="submit" value="назад">
        </form>

        <form style="display: inline-block;" action="home_admin.jsp">
            <input type="submit" value="домой">
        </form>
    </div>
    </c:when>

    <c:when test="${user.userRole == 'CLIENT'}">
    <div>
        <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="get_packages_for_user">
            <input type="submit" value="назад">
        </form>

        <form style="display: inline-block;" action="home.jsp">
            <input type="submit" value="домой">
        </form>
    </div>
    </c:when>

    <c:otherwise>
      <div>
        <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_packages">
            <input type="submit" value="назад">
        </form>

        <form style="display: inline-block;" action="home_manager.jsp">
            <input type="submit" value="домой">
        </form>
      </div>
    </c:otherwise>
</c:choose>


<table>
    <tr>
        <td>Тип посылки:</td>
        <td>${packagee.type}</td>
    </tr>
    <tr>
        <td>Отправитель:</td>
        <td>${packagee.senderName}</td>
    </tr>
    <tr>
        <td>Получатель:</td>
        <td>${packagee.getterUser.secondName} ${packagee.getterUser.firstName}</td>
    </tr>
    <tr>
        <td>Адрес посылки:</td>
        <td>${packagee.address}</td>
    </tr>
    <tr>
        <td>Почтовый индекс:</td>
        <td>${packagee.postIndex}</td>
    </tr>
    <tr>
        <td>Дата отправления:</td>
        <td>${packagee.date}</td>
    </tr>
    <tr>
        <td>Код отслеживания:</td>
        <td>${packagee.trackNumber}</td>
    </tr>
    <tr>
        <td>Статус посылки:</td>
        <td>${packagee.status}</td>
    </tr>
</table>
<hr/>
<h2>Отслеживание посылки:</h2>

<c:if test="${user.userRole == 'POST_MANAGER'}">
    <form action="controller" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
        <input type="hidden" name="command" value="add_comment">
        <input type="hidden" name="package_id" value="${packagee.idPackage}">
        <textarea required maxlength="100" name="comment_text"></textarea>
        <input type="submit" value="добавить комментарий">
    </form>
</c:if>

<table>
<c:forEach var="comment" items="${comments}">
    <tr>
        <td>${comment.date}</td>
        <td>${comment.text}</td>
    </tr>
</c:forEach>
</table>

</body>
</html>

