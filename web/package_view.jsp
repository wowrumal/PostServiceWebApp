<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 2/22/2016
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Посылка</title>

    <script type="text/javascript">
        function func1() {
            var address = document.getElementById('pack_address');
            var usersList = document.getElementById('getter');
            var selectedUser = usersList.options[usersList.selectedIndex].value;

            var arr = [];

            <c:forEach var="user" varStatus="status" items="${users}">
                arr[${status.index}] = [];
                arr[${status.index}][0] = "${user.id}";
                arr[${status.index}][1] = "${user.passport.address}";
            </c:forEach>

            arr.forEach(function(item, i, arr){
                if (i == selectedUser){
                    address.value = item[1];
                }
            });
        }
    </script>
</head>
<>
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


<form action="controller" name="package_form" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

    <h2>Тип посылки:</h2>
    <select required size="1" name="package_type">
        <option>Письмо</option>
        <option>Бандероль</option>
        <option>Крупногабаритная</option>
    </select>

    <%--<h2>Дата:</h2>
    <input type="date" required name="package_date" value="${packagee.date}">--%>

    <h2>Отправитель:</h2>
    <input type="text" required name="package_sender_name" value="${user.secondName} ${user.firstName}"
           placeholder="Стасюкевич С.Ю." maxlength="45">

    <h2>Получатель:</h2>

    <p>
        <select id="getter" required size="1" onchange="func1()" name="package_getter_name">

            <c:choose>
                <c:when test="${empty users}">
                    <option selected
                            value="${packagee.getterUser.id}">${packagee.getterUser.secondName} ${packagee.getterUser.firstName}</option>
                </c:when>
                <c:otherwise>
                    <c:forEach var="getter_user" items="${users}">
                        <c:choose>
                            <c:when test="${packagee.getterUser.id==getter_user.id}">
                                <option selected
                                        value="${getter_user.id}">${getter_user.secondName} ${getter_user.firstName}</option>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${user.id != getter_user.id}">
                                    <option value="${getter_user.id}">${getter_user.secondName} ${getter_user.firstName}</option>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
    </p>

    <h2>Адрес посылки:</h2>
    <input required type="text" id="pack_address" name="package_address" value="${packagee.address}"
           placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

    <h2>Почтовый индекс:</h2>
    <input required type="number" min="1" name="package_post_index" value="${packagee.postIndex}" placeholder="33524"
           maxlength="7">


    <%--<h2>Код:</h2>
    <input required type="number" min="1" name="package_barcode" value="${packagee.barCode}" placeholder="4789623"
           maxlength="10">--%>

    <c:if test="${user.userRole == 'ADMIN'}">
        <c:if test="${not empty packagee}">
            <input type="hidden" name="package_id" value="${packagee.idPackage}">
            <input type="hidden" name="command" value="update_package">
            <input type="submit" value="обновить">
        </c:if>
    </c:if>

    <c:if test="${empty packagee}">
        <input type="hidden" name="command" value="add_package">
        <input type="submit" value="создать">
    </c:if>
</form>

</body>
</html>

