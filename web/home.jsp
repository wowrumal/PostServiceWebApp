<%@ page import="by.bsuir.spp.bean.document.Advertisement" %>
<%@ page import="java.util.List" %>
<%@ page import="by.bsuir.spp.dao.impl.MySqlAdvertisementDao" %>
<%@ page import="by.bsuir.spp.controller.constant.RequestParameterName" %>
<%@ page import="by.bsuir.spp.bean.User" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Домашняя</title>
</head>
<body>

<%
    List<Advertisement> advertisements = MySqlAdvertisementDao.getInstance().getAdvertisementsByPassportId(
            ((User)request.getSession().getAttribute(RequestParameterName.USER)).getPassport().getPassportId()
    );

%>

<div class="menu">
    Меню
    <ul>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_packages_for_user">
                <input type="submit" value="Мои посылки">
            </form>
        </li>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_receipts">
                <input type="submit" value="Мои квитанции">
            </form>
        </li>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_advertisements">
                <%--<input type="submit" value="Мои извещения[${fn:length(advertisements)}]">--%>
                <input type="submit" value="Мои извещения[<%out.print(advertisements.size());%>]">
            </form>
        </li>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_search_statements">
                <input type="submit" value="Заявления поиска посылки">
            </form>
        </li>

        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_prepayment_books">
                <input type="submit" value="Заявления оформления авансовой книги">
            </form>
        </li>
    </ul>
</div>
WELCOME SIMPLE USER , ${user.firstName}!!!

<form action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="logout_command">
    <input type="submit" value="выход">
</form>

</body>
</html>
