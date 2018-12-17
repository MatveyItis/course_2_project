<#import "/WEB-INF/ftltags/layout.ftl" as layout>
<#import "/WEB-INF/ftltags/navbar.ftl" as navbar>
<#import "/WEB-INF/ftltags/player.ftl" as player>
<#import "/WEB-INF/ftltags/audio.ftl" as audio>

<@layout.template>
    <@navbar.navbar/>
<div class="container-fluid row" style="padding: 20px">
    <div class="container col-md-3">
        <div class="list-group" id="list-tab" role="tablist">
            <a class="list-group-item list-group-item-action show active list-group-item-dark"
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
            <a class="list-group-item list-group-item-action list-group-item-dark"
               id="list-settings-list"
               data-toggle="list"
               href="#list-settings" role="tab" aria-controls="settings">Settings</a>
            <div class="list-group-item list-group-item-action list-group-item-dark">
                <@player.player/>
            </div>
        </div>
    </div>
    <div class="container col-md-9" id="content">
        <div class="tab-content" id="nav-tabContent" style="padding: 5px;">
            <div class="tab-pane fade show active" id="list-profile" role="tabpanel"
                 aria-labelledby="list-profile-list">
                <div class="container-fluid" style="background-color:  rgb(58, 63, 78);color: white;padding: 10px">
                    <div class="row">
                        <div class="col-md-4" style="padding: 10px">
                            <div class="container">
                                <img src="/images/avatarcat.png" class="d-block w-100"
                                     style="border-radius: 50%">
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
                    <div class="row" style="width: 95%">
                        <div class="container justify-content-between align-items-center col-md-4" id="friends">
                            <h3>Friends</h3>
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                            <img src="/images/default_cat_avatar.jpg" class="d-block w-25"
                                 style="border-radius: 50%">
                        </div>
                        <div class="container col-md-8">
                            <h3>Wall posts</h3>
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
                     style="background-color:  rgb(58, 63, 78);color: white;padding: 10px; height: 700px">
                    <h2 align="center">Friends</h2>
                    <div class="container row" align="center" style="margin-bottom: 10px">
                        <div class="col-md-10">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search"
                                   name="userName"
                                   id="userName"
                                   aria-label="Search">
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-online-info my-2 my-sm-0" type="submit" id="searchPeople">
                                Search
                            </button>
                        </div>
                    </div>
                    <div class="container" id="resultPeople" style="height: 570px;overflow: scroll">

                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                </div>
            </div>
            <div class="tab-pane fade" id="list-music" role="tabpanel" aria-labelledby="list-music-list">
                <div class="container-fluid"
                     style="background-color:  rgb(198, 200, 202); border-radius: 5px;padding-bottom: 20px;height: auto">
                    <h3 align="center">Music</h3>
                    <hr>
                    <div class="row" style="height: inherit">
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
                                    <div class="carousel-item">
                                        <img class="d-block w-100"
                                             src="/images/albumcovers/oxxxy_long_way_to_home.jpeg"
                                             alt="Fourth slide">
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
                        <div class="container col-md-8" style="overflow: scroll;height: inherit">
                            <h5>Favourite music</h5>
                            <ol id="music">
                            <#list userSongs as song>
                                <#if audio.audio(song, song_index + 1)??>
                                    ${audio.audio(song, song_index + 1)}
                                </#if>
                            </#list>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
                <div class="container-fluid" style="background-color: rgb(53,58,73); border-radius: 15px;padding: 10px">
                    <div class="header" align="center">
                        <h3 style="color: white">Settings</h3>
                    </div>
                    <div class="container" id="settings-form">
                        <p>You can change your details or fill in if you missed.</p>
                        <form id="updateInfo">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="firstName">First name</label>
                                    <input type="text" class="form-control" id="firstName"
                                           placeholder="Change first name" value="${user.getFirstName()}">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="lastName">Last name</label>
                                    <input type="text" class="form-control" id="lastName"
                                           placeholder="Change last name" value="${user.getLastName()}">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email"
                                           placeholder="Change Email" value="${user.getEmail()}">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="firstPassword">Password</label>
                                    <input type="password" class="form-control" id="firstPassword"
                                           placeholder="Password" minlength="6" maxlength="12">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="secondPassword">Repeat password</label>
                                    <input type="password" class="form-control" id="secondPassword"
                                           placeholder="Repeat password" minlength="6" maxlength="12">
                                </div>
                            </div>
                            <button type="button" onclick="sendUpdate()" class="btn btn-primary btn-light">Update</button>
                        </form>

                        <button hidden id="successUpdate" type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#exampleModalLong1">
                        </button>
                        <div class="modal fade" id="exampleModalLong1" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLongTitle" aria-hidden="true" style="color: black">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">x</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Успешно
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary">OK</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button hidden id="failUpdate" type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#exampleModalLong2">
                        </button>
                        <div class="modal fade" id="exampleModalLong2" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLongTitle" aria-hidden="true" style="color: black">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">x</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Passwords is not similar, please enter one more time
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary">Repeat</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.template>