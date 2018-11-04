<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 04/11/2018
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Oops 404!">
    <div class="container" align="center" style="margin-top: 60px">
        <div class="jumbotron">
            <h2 class="display-6">500 - Внутренняя ошибка сервера</h2>
            <p class="lead"><img src="https://thumbs.gfycat.com/GratefulObedientEel-size_restricted.gif" alt="error cat 500"></p>
            <hr class="my-4">
            <p>Чтобы вернуться назад просто нажмите кнопку</p>
            <a class="btn btn-dark btn-md" href="home" role="button">Home</a>
        </div>
    </div>
</t:layout>