<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 4/2/2016
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Авансовая книжка</title>
</head>
<body>
    <h1>Авансовая книжка</h1>

    <c:choose>
        <c:when test="${user.userRole == 'ADMIN'}">
            <div>
                <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="load_prepayment_books">
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
                    <input type="hidden" name="command" value="get_user_prepayment_books">
                    <input type="submit" value="назад">
                </form>

                <form style="display: inline-block;" action="home.jsp">
                    <input type="submit" value="доиой">
                </form>
            </div>
        </c:when>

        <c:otherwise>
            <div>
                <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="load_prepayment_books">
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
        <td>Имя клиента:</td>
        <td>${prepayment_book.clientName}</td>
    </tr>
    <tr>
        <td>Номер клиента:</td>
        <td>${prepayment_book.clientNumber}</td>
    </tr>
    <tr>
        <td>Средства на счету:</td>
        <td>${prepayment_book.unpaidCost}</td>
    </tr>
    <tr>
        <td>Глава организации:</td>
        <td>${prepayment_book.organizationHeadName}</td>
    </tr>
    <tr>
        <td>Бухгалтер</td>
        <td>${prepayment_book.bookkeeperName}</td>
    </tr>
    <tr>
        <td>Дата:</td>
        <td>${prepayment_book.date}</td>
    </tr>
</table>
<hr/>

    <form action="doccontroller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="prepayment_book_document">
        <input type="hidden" name="prepayment_book_number" value="${prepayment_book.statementNumber}">
        <select name="doc_type">
            <option value="pdf">pdf</option>
            <option value="xls">xls</option>
            <option value="xml">xml</option>
        </select>
        <input type="submit" value="скачать заявление на восстановление авансовой книжки">
    </form>
</body>
</html>
