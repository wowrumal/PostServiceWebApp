<%@ page import="by.bsuir.spp.dao.impl.MySqlPackageDao" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/>

<%
    int countNewPackages = MySqlPackageDao.getInstance().getNewPackageIds().size();
%>

<div class="container">

    <h1>Здравствуйте менеджер , ${user.firstName}!!!</h1>

    <div class="menu">
      Меню
      <ul>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="load_packages">
                <%--<input type="submit" value="Посылки. Новых посылок[${fn:length(new_package_ids)}]">--%>
                <input type="submit" value="Посылки. Новых посылок[<%out.print(countNewPackages);%>]">
            </form>
        </li>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="load_advertisements">
                <input type="submit" value="Извещения">
            </form>
        </li>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="load_prepayment_books">
                <input type="submit" value="Заявления для оформления авансовой книжки">
            </form>
        </li>
        <li>
            <form action="controller" method="get" enctype="multipart/form-data">
                <input type="hidden" name="command" value="load_search_statements">
                <input type="submit" value="Заявления поиска посылки">
            </form>
        </li>
      </ul>
    </div>
</div>

<jsp:include page="footer.jsp"/>