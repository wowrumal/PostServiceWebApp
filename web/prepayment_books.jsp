<%--
  Created by IntelliJ IDEA.
  User: stas-
  Date: 2/25/2016
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

    <div class="container">
        <h3>Заявления на восстановление авансовых книг</h3>

        <ul class="collapsible" data-collapsible="expandable">
            <c:forEach var="book" items="${prepayment_books}">
                <li>
                    <div class="collapsible-header">
                        <div class="row item-header">
                            <span class="col offset-s1 s5"><b>Организация:</b> ${book.organizationHeadName} </span>
                            <span class="col s4 offset-s1"><b>Средства на счету:</b> ${book.unpaidCost} </span>
                            <i class="material-icons right">arrow_drop_down</i>
                        </div>
                    </div>
                    <div class="collapsible-body item">
                        <div class="row">
                            <span class="col s5 offset-s1"><b>Имя клиента:</b> ${book.clientName}</span>
                            <span class="col s4 offset-s1 package-align"><b>Номер клиента:</b> ${book.clientNumber}</span>

                            <a class="waves-effect waves-light btn col s1 dropdown-button" data-activates='dropdown${book.clientNumber}'><i class="material-icons">file_download</i></a>

                            <ul id='dropdown${book.clientNumber}' class='dropdown-content'>
                                <li><a href="doccontroller?command=prepayment_book_document&prepayment_book_number=${book.statementNumber}&doc_type=xls">XLS</a></li>
                                <li><a href="doccontroller?command=prepayment_book_document&prepayment_book_number=${book.statementNumber}&doc_type=csv">CSV</a></li>
                                <li><a href="doccontroller?command=prepayment_book_document&prepayment_book_number=${book.statementNumber}&doc_type=pdf">PDF</a></li>
                            </ul>

                        </div>
                        <div class="row">
                            <span class="col s5 offset-s1"><b>Бухгалтер:</b> ${book.bookkeeperName}</span>
                            <span class="col s4 package-align"><b>Дата:</b> ${book.date} </span>

                            <form action="controller" enctype="multipart/form-data" method="get">
                                <input type="hidden" name="command" value="delete_prepayment_book">
                                <input type="hidden" name="prepayment_book_number" value="${book.statementNumber}">
                                <button type="submit" class="waves-effect waves-light btn col s1 red lighten-1">
                                    <i class="material-icons">delete</i>
                                </button>
                            </form>
                        </div>

                        <div class="row">
                            <form action="controller" enctype="multipart/form-data" method="get">
                                <input type="hidden" name="command" value="select_prepayment_book">
                                <input type="hidden" name="sub_command" value="update">
                                <input type="hidden" name="prepayment_book_number" value="${book.statementNumber}">
                                <button type="submit" class="waves-effect waves-light btn col s1 green right btn-align lighten-1">
                                    <i class="material-icons">mode_edit</i>
                                </button>
                            </form>
                        </div>

                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

<jsp:include page="footer.jsp" />