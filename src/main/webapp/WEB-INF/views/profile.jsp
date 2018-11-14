<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<t:layout title="Profile" style="/css/default.css">
    <div class="container-fluid row" style="padding: 20px">
        <div class="container col-md-3">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action  list-group-item-dark active show"
                   id="list-profile-list" data-toggle="list"
                   href="#list-profile" role="tab" aria-controls="profile">Profile
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-friends-list"
                   data-toggle="list"
                   href="#list-friends" role="tab" aria-controls="friends">Friends
                    <span class="badge badge-dark badge-pill" style="">14</span>
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-messages-list"
                   data-toggle="list"
                   href="#list-messages" role="tab" aria-controls="messages">Messages
                    <span class="badge badge-dark badge-pill">28</span>
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-music-list"
                   data-toggle="list"
                   href="#list-music" role="tab" aria-controls="music">Music</a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-settings-list"
                   data-toggle="list"
                   href="#list-settings" role="tab" aria-controls="settings">Settings</a>
                <div class="list-group-item list-group-item-action list-group-item-dark">
                    <t:player/>
                </div>
            </div>

        </div>
        <div class="container col-md-9">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-profile" role="tabpanel"
                     aria-labelledby="list-profile-list">
                    <div class="container-fluid" style="background-color:  rgb(58, 63, 78); color: white">
                        <div class="row">
                            <div class="col-md-4" style="padding: 10px">
                                <div class="container" style="background-color: white;
                                border-radius: 50%;width: 270px;height: 270px;">
                                    <img src="/images/avatarcat.png" alt="avatar" width="250" height="250"
                                         style="border-radius: 50%;margin-top: 10px; margin-left: -5px;">
                                </div>
                            </div>
                            <div class="col-md-8" style="padding: 10px;">
                                <div class="container"
                                     style="border-radius: 10px;">
                                    <h2>${user.getFirstName()} ${user.getLastName()}</h2>
                                    <hr>
                                    <div class="row">
                                        <div class="col-md-6">Date of birth:</div>
                                        <div class="col-md-6">11.11.1999</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">City:</div>
                                        <div class="col-md-6">Kazan</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">Lovely music:</div>
                                        <div class="col-md-6">Pop, Rap and etc</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">Phone number:</div>
                                        <div class="col-md-6">+79991231239</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="width: 90%">
                            <div class="container col-md-4" id="friends">
                                <h3>Friends</h3>
                                <img src="/images/avatar_default.png" width="60" height="60">
                                <img src="/images/avatar_default.png" width="60" height="60">
                                <img src="/images/avatar_default.png" width="60" height="60">
                                <img src="/images/avatar_default.png" width="60" height="60">
                                <img src="/images/avatar_default.png" width="60" height="60">
                            </div>
                            <div class="container col-md-8" style="width: 90%">
                                <form>
                                    <div class="form-group">
                                        <label for="new-music">Share:</label>
                                        <textarea class="form-control" rows="4" id="new-music"
                                                  placeholder="Say something for your friends.."></textarea>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-dark">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-friends" role="tabpanel" aria-labelledby="list-friends-list">
                    <div class="container-fluid"
                         style="background-color:  rgb(58, 63, 78);color: white;padding: 10px">
                        <div class="container" align="center">
                            <form class="form-inline my-2 my-lg-0">
                                <input class="form-control mr-sm-2" type="search" placeholder="Search"
                                       aria-label="Search">
                                <button class="btn btn-online-info my-2 my-sm-0" type="submit">Search</button>
                            </form>
                        </div>
                        <div class="container" id="my-music-search-result">

                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                    </div>
                </div>
                <div class="tab-pane fade" id="list-music" role="tabpanel" aria-labelledby="list-music-list">
                    <div class="container-fluid"
                         style="background-color:  rgb(198, 200, 202); border-radius: 5px;padding-bottom: 20px; ">
                        <h3 align="center">Music</h3>
                        <hr>
                        <div class="row">
                            <div class="container col-md-4" style="border-radius: 8px;">
                                <div id="carouselCovers" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img class="d-block w-100"
                                                 src="/images/albumcovers/eminem_revival_cover_art.png"
                                                 alt="First slide">
                                        </div>
                                        <div class="carousel-item">
                                            <img class="d-block w-100"
                                                 src="/images/albumcovers/Обложка_альбома_Noize_MC_Хипхопера_Орфей_&_Эвридика.jpg"
                                                 alt="Second slide">
                                        </div>
                                        <div class="carousel-item">
                                            <img class="d-block w-100"
                                                 src="/images/albumcovers/kandrick_lamar_album_cover_damn.jpg"
                                                 alt="Third slide">
                                        </div>
                                    </div>
                                    <a class="carousel-control-prev" href="#carouselCovers" role="button"
                                       data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselCovers" role="button"
                                       data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>

                            </div>
                            <div class="container col-md-8" style="height: 270px;overflow: scroll;">
                                <h5>Favourite music</h5>
                                <c:set var="number" value="1"/>
                                <ol id="music">
                                    <c:forEach var="song" items="${userSongs}">
                                        <t:audio song="${song}" audioId="${number}"/>
                                        <hr>
                                        <c:set var="number" value="${number + 1}"/>
                                    </c:forEach>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="container-fluid" style="background-color: rgb(53,58,73); border-radius: 15px">
                        <div class="header" align="center">
                            <h3 style="color: white">Settings</h3>
                        </div>
                        <div class="container" id="settings-form">
                            <p>You can change your details or fill in if you missed.</p>
                            <form>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputEmail4">Email</label>
                                        <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputPassword4">Password</label>
                                        <input type="password" class="form-control" id="inputPassword4"
                                               placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="inputAddress">Address</label>
                                        <input type="text" class="form-control" id="inputAddress"
                                               placeholder="1234 Main St">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputDate">Date of Birth</label>
                                        <input type="date" class="form-control" id="inputDate">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputCity">City</label>
                                        <input type="text" class="form-control" id="inputCity">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputState">State</label>
                                        <select id="inputState" class="form-control">
                                            <option selected>Kazan</option>
                                            <option>Moscow</option>
                                            <option>Saint-Petersburg</option>
                                        </select>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-light">Update</button>
                            </form>
                            <a href="#">Delete your account</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <t:info/>
</t:layout>