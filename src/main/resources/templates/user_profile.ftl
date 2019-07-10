<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>
<@c.template "Profile">
    <@n.navbar/>
    <div class="container">
        <div class="row pt-2 justify-content-end">
            <div class="col-lg-3 col-xl-3 col-md-4 col-sm-6">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="form-control input-lg" placeholder="Search">
                        <button class="btn btn-primary btn-lg">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="pt-5" align="center">
            <#if isCurrentUser>
                <div class="row col-5 justify-content-end">
                    <a href="/edit_profile">
                        <#--<i class="fas fa-user-edit"></i>-->
                        <span class="badge badge-light">Edit</span>
                    </a>
                </div>
            <#else>
                <div class="row col-5 justify-content-end">
                    <#if isSubscriber>
                        <a href="/unsubscribe/${userChannel.id}">
                            <span class="badge badge-danger">Unsubscribe</span></a>
                    <#else>
                        <a href="/subscribe/${userChannel.id}"
                           style="text-decoration: none"><span class="badge badge-success">Subscribe</span></a>
                    </#if>
                </div>
            </#if>
            <a href="#myModal" data-toggle="modal" data-target="#myModal">
                <img class="shadow-lg p-2 bg-white"
                     src="<#if user.img??>/img/${user.img.fileName}<#else >/static/img/avatar.png</#if>"
                     width="250" height="250" style="border-radius: 50%" alt=""></a>
            <h3>${user.username}</h3>
            <div class="row col-lg-6 col-xl-5 col-md-6 col-sm-8 justify-content-center p-2">
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Subscribers</h6>
                        <a href="/user/subscribers/${user.id}"
                           style="text-decoration: none"><span>${user.subscribers?size}</span></a>
                    </div>
                </div>
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Subscriptions</h6>
                        <a href="/user/subscriptions/${user.id}"
                           style="text-decoration: none"><span>${user.subscriptions?size}</span></a>
                    </div>
                </div>
                <div class="card col-sm-5 m-1 shadow-sm bg-white rounded">
                    <div class="card-body">
                        <h6>Songs</h6>
                        <a href="/user_songs/${user.id}"
                           style="text-decoration: none;">Click</a>
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
                        <h6 class="modal-title" id="exampleModalLabel">More about ${user.username}</h6>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" align="center">
                        <img src="<#if user.img??>/img/${user.img.fileName}<#else >/static/img/avatar.png</#if>"
                             width="200" height="200" style="border-radius: 50%" alt="">
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
                            <#if user.aboutMe?has_content>${user.aboutMe}
                            <#else>Please fill your profile info. Click <a
                                    href="/edit_profile" style="text-decoration: none; color: dodgerblue">here</a>
                            </#if>
                        </p>
                        <br>
                    </div>
                    <div class="modal-footer" align="center">
                        <button type="button" class="btn btn-primary btn-dark" data-dismiss="modal">Ok</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.template>