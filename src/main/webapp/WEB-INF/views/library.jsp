<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Library">
    <div class="container-fluid row" style="padding: 20px">
        <div class="container col-md-3">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action  list-group-item-dark active show"
                   id="list-library-list" data-toggle="list"
                   href="#list-library" role="tab" aria-controls="library">Library
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-top-list"
                   data-toggle="list"
                   href="#list-top" role="tab" aria-controls="top">Top charts
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-albums-list"
                   data-toggle="list"
                   href="#list-albums" role="tab" aria-controls="albums">Albums
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-search-list"
                   data-toggle="list"
                   href="#list-search" role="tab" aria-controls="search">Search</a>
                <div class="list-group-item list-group-item-action list-group-item-dark">
                    <t:player/>
                </div>
            </div>
        </div>
        <div class="container col-md-9">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-library" role="tabpanel"
                     aria-labelledby="list-library-list">
                    <div class="container-fluid"
                         style="background: url('/images/bg/paper_2.png'); border-radius: 15px;padding: 10px;">
                        <div class="container" align="center">
                            <h2>The best library of the music<span class="badge badge-secondary">v1.0</span></h2>
                            <img class="d-block w-50" src="/images/man-earphones-1.jpeg">
                            <h3>Enjoy listening music with us</h3>
                        </div>
                        <div class="container col-md-12" style="padding: 10px;height: 650px;overflow: scroll">
                            <c:set var="number" value="1"/>
                            <ol id="music">
                                <c:forEach var="song" items="${songs}">
                                    <t:audio song="${song}" audioId="${number}"/>
                                    <c:set var="number" value="${number + 1}"/>
                                </c:forEach>
                            </ol>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-top" role="tabpanel" aria-labelledby="list-top-list">
                    <div class="container-fluid"
                         style="background: url('/images/bg/paper_2.png'); border-radius: 15px">

                    </div>
                </div>
                <div class="tab-pane fade" id="list-albums" role="tabpanel" aria-labelledby="list-albums-list">
                    <div class="container-fluid"
                         style="background: url('/images/bg/paper_2.png'); border-radius: 15px">

                    </div>
                </div>
                <div class="tab-pane fade" id="list-search" role="tabpanel" aria-labelledby="list-search-list">
                    <div class="container-fluid"
                         style="background: url('/images/bg/paper_2.png'); border-radius: 15px;padding: 10px;height: 600px;overflow: scroll">
                        <h2 align="center">Search your lovely songs:</h2>
                        <div class="container row">
                            <div class="col-md-10">
                                <input class="form-control mr-sm-2" type="search" placeholder="Search" name="songName"
                                       id="songName"
                                       aria-label="Search">
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-online-info my-2 my-sm-0" type="submit" id="searchSong">Search
                                </button>
                            </div>
                        </div>
                        <div class="container col-md-12" align="center">
                            <div class="result-search" id="result">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>