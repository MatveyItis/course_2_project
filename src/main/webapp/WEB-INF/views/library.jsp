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
    <script>
        function sendSongId(songId) {
            $.ajax({
                    type: 'post',
                    url: '/library',
                    data: {
                        songId: songId
                    }
                }
            ).done(function () {
                var differentButton = "<img src=\"https://img.icons8.com/metro/50/000000/checkmark.png\" width='20' height='20'>";
                var buttonDiv = document.getElementById("b" + songId);
                buttonDiv.innerHTML = "";
                buttonDiv.innerHTML = differentButton;
                /*var contentTableHTML = "<table>";
                contentTableHTML += "<tr>" +
                    "<th>Номер</th>" +
                    "</tr>";
                for (var i = 0; i < data.length; i++) {
                    contentTableHTML += "<tr>";
                    contentTableHTML += "<td>" + [i] + "</td>";
                    contentTableHTML += "</tr>";
                }
                contentTableHTML += "</table>";
                var contentTableDiv = document.getElementById("table");
                contentTableDiv.innerHTML = contentTableHTML;*/

            }).fail(function () {
                alert("Error");
            });
        }
    </script>
    <t:navbar/>
    <div class="container-fluid row" style="padding-top: 20px">
        <div class="col-3">
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
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-library" role="tabpanel"
                     aria-labelledby="list-library-list">
                    <div class="container-fluid"
                         style="background: url('/images/paper_2.png'); border-radius: 15px; height: 600px;padding: 10px;">
                        <div class="container" align="center">
                            <h2>The best library of the music<span class="badge badge-secondary">v1.0</span></h2>
                        </div>
                        <div class="container">
                            <c:forEach var="song" items="${songs}">
                                <t:audio src="${song.getSongSrc()}" songId="${song.getSongId()}"
                                         songTitle="${song.getTitle()}"/>
                            </c:forEach>
                        </div>
                        <div id="table">

                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-top" role="tabpanel" aria-labelledby="list-top-list">
                    <div class="container-fluid"
                         style="background: url('/images/paper_2.png'); border-radius: 15px">

                    </div>
                </div>
                <div class="tab-pane fade" id="list-albums" role="tabpanel" aria-labelledby="list-albums-list">
                    <div class="container-fluid"
                         style="background: url('/images/paper_2.png'); border-radius: 15px">

                    </div>
                </div>
                <div class="tab-pane fade" id="list-search" role="tabpanel" aria-labelledby="list-search-list">
                    <div class="container-fluid"
                         style="background: url('/images/paper_2.png'); border-radius: 15px;padding: 10px">
                        <div class="search-class">
                            <form class="form-inline my-2 my-lg-0">
                                <input class="form-control mr-sm-2" type="search" placeholder="Search"
                                       aria-label="Search">
                                <button class="btn btn-online-info my-2 my-sm-0" type="submit">Search</button>
                            </form>
                        </div>
                        <div class="container" align="center">
                            <div class="result-search" id="result">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>