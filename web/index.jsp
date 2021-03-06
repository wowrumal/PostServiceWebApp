
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Test Data Base</title>
  </head>
  <body>
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="db_test">
        <input type="submit" value="get rows from table">
    </form>

    <br>

    <c:if test="${not empty db_content}">
        <table align="center" border="3">
            <tr>
                <th>Passport numberrrr</th>
                <th>Address</th>
                <th>Issuing Institution</th>
                <th>Date of issue</th>
            </tr>
            <c:forEach var="passport" items="${db_content}">
                <tr>
                    <td>${passport.passportNumber}</td>
                    <td>${passport.address}</td>
                    <td>${passport.issuingInsitution}</td>
                    <td>${passport.issueDate}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
  </body>
</html>
