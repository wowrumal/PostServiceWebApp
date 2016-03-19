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
    <title></title>
</head>
<body>
<div class="menu">
    Меню
    <ul>
        <li>
            <form action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_packages_for_user">
                <input type="submit" value="Мои посылки">
            </form>
        </li>
        <li>
            <form action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_receipts">
                <input type="submit" value="Мои чеки">
            </form>
        </li>
        <li>
            <form action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_advertisements">
                <input type="submit" value="Мои извещения">
            </form>
        </li>
        <li>
            <form action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_search_statements">
                <input type="submit" value="Заявления поиска посылки">
            </form>
        </li>

        <li>
            <form action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="get_user_search_statements">
                <input type="submit" value="Заявления оформления авансовой книги">
            </form>
        </li>
    </ul>
</div>
WELCOME SIMPLE USER , ${user.firstName}!!!
</body>
</html>
