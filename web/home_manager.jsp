<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 3/8/2016
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>WELCOME MANAGER , ${user.firstName}!!!</h1>

<div class="menu">
  Меню
  <ul>
    <li>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_packages">
            <input type="submit" value="Packages[${fn:length(new_package_ids)}]">
        </form>
    </li>
    <li>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_advertisements">
            <input type="submit" value="load advertisements">
        </form>
    </li>
    <li>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_prepayment_books">
            <input type="submit" value="load statements of prepayment books">
        </form>
    </li>
    <li>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="load_search_statements">
            <input type="submit" value="load search statements">
        </form>
    </li>
  </ul>
</div>

<form action="controller" method="get" enctype="multipart/form-data">
  <input type="hidden" name="command" value="logout_command">
  <input type="submit" value="выход">
</form>
</body>
</html>
