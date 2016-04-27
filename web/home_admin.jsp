<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />


    <div class="container">

        <form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_packages">
            <input type="submit" value="Посылки">
        </form>

        <form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_users">
            <input type="submit" value="Клиенты">
        </form>

        <form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_receipts">
            <input type="submit" value="Квитанции">
        </form>

        <form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_advertisements">
            <input type="submit" value="Извещения">
        </form>

        <form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_prepayment_books">
            <input type="submit" value="Заявления для оформления авансовой книжки">
        </form>

        <form action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_search_statements">
            <input type="submit" value="Заявления поиска посылки">
        </form>
    </div>
<jsp:include page="footer.jsp" />
