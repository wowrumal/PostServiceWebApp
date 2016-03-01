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
<html>
<head>
    <title></title>
</head>
<body>

<form action="controller" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
    <c:if test="${not empty packagee}">
        <input type="hidden" name="command" value="update_package">
        <h2>Package ID:</h2>
        <input type="number" readonly name="package_id" value="${packagee.idPackage}">
    </c:if>

    <c:if test="${empty packagee}">
        <input type="hidden" name="command" value="add_package">
    </c:if>

    <h2>Package type:</h2>
    <input type="text" name="package_type" value="${packagee.type}" placeholder="Посылка" maxlength="45">

    <h2>Date:</h2>
    <input type="date" name="package_date" value="${packagee.date}" >

    <h2>Sender name:</h2>
    <input type="text" name="package_sender_name" value="${packagee.senderName}" placeholder="Стасюкевич С.Ю." maxlength="45">

    <h2>Getter name:</h2>
    <input type="text" name="package_getter_name" value="${packagee.getterName}" placeholder="Цивако К.А." maxlength="45">

    <h2>Address:</h2>
    <input type="text" name="package_address" value="${packagee.address}" placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45">

    <h2>Post index:</h2>
    <input type="number" name="package_post_index" value="${packagee.postIndex}" placeholder="33524" maxlength="7">

    <h2>Barcode:</h2>
    <input type="number" name="package_barcode" value="${packagee.barCode}" placeholder="4789623" maxlength="10">

    <input type="submit" value="apply">
</form>

</body>
</html>

</body>
</html>
