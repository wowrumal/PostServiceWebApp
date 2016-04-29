<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 4/26/2016
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="nav-wrapper blue darken-1">
  <a href="" data-activates="slide-out" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>
  <a href="home.jsp" class="brand-logo">ПАШТОВЫ СЕРВИС</a>

  <div class="row right">
    <a href="controller?command=logout_command" class="waves-effect waves-light btn red lighten-1"><i class="material-icons auth">power_settings_new</i></a>
  </div>

  <ul id="slide-out" class="side-nav">
    <li class="user-name blue darken-1">${user.firstName} ${user.secondName} </li>

    <c:choose>
      <c:when test="${user.userRole == 'ADMIN'}">

        <li><a href="controller?command=load_packages">Посылки</a></li>
        <li><a href="controller?command=load_users">Пользователи</a></li>
        <li><a href="controller?command=load_receipts">Квитанции</a></li>
        <li><a href="controller?command=load_advertisements">Извещения</a></li>
        <li><a href="controller?command=load_search_statements">Поиск посылки</a></li>
        <li><a href="controller?command=load_prepayment_books">Авансовая книжка</a></li>

      </c:when>

      <c:when test="${user.userRole == 'POST_MANAGER'}">

        <li><a href="controller?command=load_packages">Посылки</a></li>
        <li><a href="controller?command=load_advertisements">Извещения</a></li>
        <li><a href="controller?command=load_search_statements">Поиск посылки</a></li>
        <li><a href="controller?command=load_prepayment_books">Авансовая книжка</a></li>

      </c:when>

      <c:when test="${user.userRole == 'CLIENT'}">

        <li><a href="controller?command=get_packages_for_user">Посылки</a></li>
        <li><a href="controller?command=get_user_receipts">Квитанции</a></li>
        <li><a href="controller?command=get_user_advertisements">Извещения</a></li>
        <li><a href="controller?command=get_user_search_statements">Поиск посылки</a></li>
        <li><a href="controller?command=get_user_prepayment_books">Авансовая книжка</a></li>

      </c:when>
    </c:choose>

  </ul>

</nav>