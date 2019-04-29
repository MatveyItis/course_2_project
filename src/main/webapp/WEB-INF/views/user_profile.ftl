<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>
<@c.template "Profile">
    <@n.navbar/>
    <div class="container">
        <div class="pt-5" align="center">
            <#if isCurrentUser>
                <div class="row col-5 justify-content-end">
                    <a href="#editModal" data-toggle="modal" data-target="#editModal">
                        <#--<i class="fas fa-user-edit"></i>-->
                        <span class="badge badge-light">Edit</span>
                    </a>
                </div>
            <#else>
                <div class="row col-5 justify-content-end">
                    <#if isSubscriber>
                        <a href="${context.getContextPath()}/unsubscribe/${userChannel.id}">
                            <span class="badge badge-danger">Unsubscribe</span></a>
                    <#else>
                        <a href="${context.getContextPath()}/subscribe/${userChannel.id}"
                           style="text-decoration: none"><span class="badge badge-success">Subscribe</span></a>
                    </#if>
                </div>
            </#if>
            <a href="#myModal" data-toggle="modal" data-target="#myModal">
                <img class="shadow-lg p-2 bg-white"
                     src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRbezqZpEuwGSvitKy3wrwnth5kysKdRqBW54cAszm_wiutku3R"
                     width="250" height="250" style="border-radius: 50%" alt=""></a>
            <h3>${user.username}</h3>
            <div class="row col-sm-6 justify-content-center p-2">
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Subscribers</h6>
                        <a href="${context.getContextPath()}/user/subscribers/${user.id}" style="text-decoration: none"><span>${user.subscribers?size}</span></a>
                    </div>
                </div>
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Subscriptions</h6>
                        <a href="${context.getContextPath()}/user/subscriptions/${user.id}" style="text-decoration: none"><span>${user.subscriptions?size}</span></a>
                    </div>
                </div>
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Songs</h6>
                        <a href="#" class="s9"><span>Click</span></a>
                    </div>
                </div>
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Favourite</h6>
                        <a href="#" style="text-decoration: none"><span><i class="far fa-star"></i></span></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal for information -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h6 class="modal-title" id="exampleModalLabel">More about Joe Six Pack</h6>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" align="center">
                        <img src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRbezqZpEuwGSvitKy3wrwnth5kysKdRqBW54cAszm_wiutku3R"
                             width="200" height="200" style="border-radius: 50%"></a>
                        <h3 class="media-heading">${user.username}
                            <small>Russia</small>
                        </h3>
                        <span><strong>Lovely music:</strong></span>
                        <span>Rap</span>
                        <span>Pop</span>
                        <span>Trap</span>
                        <span>Rock</span>
                        <hr>
                        <p class="text-left"><strong>Bio: </strong><br>
                            <#--${user.aboutMe}-->
                        </p>
                        <br>
                    </div>
                    <div class="modal-footer" align="center">
                        <button type="button" class="btn btn-primary btn-dark" data-dismiss="modal">Ok</button>
                    </div>
                </div>
            </div>
        </div>

        <!--Modal for edit user data-->
        <#if isCurrentUser>
            <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h6 class="modal-title" id="editModalLabel">Edit info:</h6>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="${context.getContextPath()}/update_info" method="post" id="update_info_form">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input class="form-control" type="text" id="username" name="username"
                                           placeholder="Enter username">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input class="form-control" type="text" id="email" name="email"
                                           placeholder="Enter email">
                                </div>
                                <div class="form-group">
                                    <label for="firstName">First name</label>
                                    <input class="form-control" type="text" id="firstName" name="firstName"
                                           placeholder="Enter first name">
                                </div>
                                <div class="form-group">
                                    <label for="lastName">Last name</label>
                                    <input class="form-control" type="text" id="lastName" name="lastName"
                                           placeholder="Enter lastName">
                                </div>
                                <div class="form-group">
                                    <label for="aboutMe">About me</label>
                                    <textarea class="form-control" id="aboutMe" name="aboutMe"
                                              placeholder="Tell us about yourself"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input class="form-control" type="password" id="password" name="password"
                                           placeholder="Enter password">
                                </div>
                                <div class="form-group">
                                    <label for="password2">Password</label>
                                    <input class="form-control" type="password" id="password2" name="password2"
                                           placeholder="Repeat password">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-dark" form="update_info_form">Update</button>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </div>
</@c.template>