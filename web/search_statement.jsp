<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 4/2/2016
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Завление поиска посылки</title>
</head>
<body>
    <h1>Завление поиска посылки</h1>
    <c:choose>
      <c:when test="${user.userRole == 'ADMIN'}">
        <div>
          <form style="display: inline-block;" action="controller" method="get" enctype="multipart/form-data">
            <input type="hidden" name="command" value="LOAD_SEARCH_STATEMENTS">
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
            <input type="hidden" name="command" value="get_user_search_statements">
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
            <input type="hidden" name="command" value="LOAD_SEARCH_STATEMENTS">
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
            <td>Посылка:</td>
            <td>${search_statement.postPackage.type} - ${search_statement.postPackage.date}</td>
        </tr>
        <tr>
            <td>Содержание заявления:</td>
            <td>${search_statement.petitionContent}</td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td>${search_statement.address}</td>
        </tr>
        <tr>
            <td>Телефон</td>
            <td>${search_statement.phoneNumber}</td>
        </tr>
        <tr>
            <td>Менеджер:</td>
            <td>${search_statement.postManagerName}</td>
        </tr>
        <tr>
            <td>Дата</td>
            <td>${search_statement.currentDate}</td>
        </tr>
    </table>
    <hr/>
</body>
</html>
