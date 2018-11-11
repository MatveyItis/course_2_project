<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Home">
    <div id="carouselFirst" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="https://images.wallpaperscraft.ru/image/dzhimi_khendriks_gitarist_virtuoz_pevets_kompozitor_98151_1920x1080.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://images.wallpaperscraft.ru/image/lindsi_stirling_skripka_devushka_muzykant_100465_1920x1080.jpg"
                     alt="Second slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselFirst" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselFirst" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div id="carouselSecond" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="https://7themes.su/_ph/29/560093602.jpg?1536888260" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://images8.alphacoders.com/480/480999.gif"
                     alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://ekburg.tv/uploads/ed50158d-5b42-4cc5-8d3d-32dff8b93eae/noyz.jpg" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselSecond" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselSecond" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="container" style="padding: 25%">
        <div class="row">
            <div class="container col-6" align="center">
                <h3 style="color: white">We are glad to see you on our site! Enjoy please</h3>
                <a role="button" href="profile" class="btn btn-dark">Continue</a>
            </div>
        </div>
    </div>


</t:layout>