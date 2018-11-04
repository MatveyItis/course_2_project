<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Oops 404!">
    <div class="container" align="center" style="margin-top: 60px">
        <div class="jumbotron">
            <h2 class="display-6">404 - Запрашиваемая вами страница не найдена</h2>
            <p class="lead"><img src="https://i.imgur.com/VJNkdvJ.gif" alt="error cat"></p>
            <hr class="my-4">
            <p>Чтобы вернуться назад просто нажмите кнопку ниже</p>
            <a class="btn btn-dark btn-md" href="home" role="button">Home</a>
        </div>
    </div>
</t:layout>