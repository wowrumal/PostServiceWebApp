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

<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/styles.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">

    <title>Паштовы сервис</title>
</head>
<body>
<div class="wrapper" style="background-image: url(image/genelmudur.jpg);">

<jsp:include page="menu.jsp" />

    <div class="container row">
        <h3 class="center-align col s12"><b>ПОШТА Сервис</b> - уникальный веб-сервис, который в разы упростит ваше привычное взаимодействие с  почтой.</h3>
        <h3 class="center-align col s12"><b>ПОШТА Сервис</b> - проще не бывает.</h3>
    </div>

<jsp:include page="footer.jsp" />