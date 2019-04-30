<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>
<#import "parts/player.ftl" as p>

<@c.template "Favourite">
    <@n.navbar/>
    <div class="container pt-4 pb-5">
        <#if songs??>
            <div class="row">
                <div class="container p-2 shadow bg-white rounded">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner" align="center">
                            <#list songs as song>
                                <#if song.songImg??>
                                    <div class="carousel-item <#if song_index == 0>active</#if>">
                                        <img class="d-block" src="/img/${song.songImg.fileName}" alt="" width="400"
                                             height="400">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5>${song.author.username}</h5>
                                            <p>${song.title}</p>
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                           data-slide="prev">
                            <span class="carousel-control-prev-icon bg-dark" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                           data-slide="next">
                            <span class="carousel-control-next-icon bg-dark" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row pt-3">
                <p>Your favourite songs</p>
                <div class="container shadow">
                    <#list songs as song>
                        <div class="row justify-content-between p-2">
                            <button onclick="playMusic(${song_index})" class="btn"><i class="fas fa-play"></i>
                            </button>
                            <span><i>${song.author.username}</i>&nbsp;-&nbsp;${song.title}</span>
                            <audio hidden id="song_id_${song_index}" data-author="${song.author.username}"
                                   data-title="${song.title}"
                                   onended="playNextTrack()">
                                <source src="/audio/${song.audioFileName}">
                            </audio>
                            <form action="${context.getContextPath()}/user/${user.id}/remove_song" method="post">
                                <button class="btn" type="submit"><i class="fas fa-trash"></i></button>
                            </form>
                        </div>
                    </#list>
                </div>
            </div>
        <#else>
            <div class="container" align="center">
                <div class="jumbotron">
                    <h2 class="display-4">No songs in your library &nbsp;<svg xmlns="http://www.w3.org/2000/svg"
                                                                              width="50" height="50"
                                                                              viewBox="0 0 24 24">
                            <path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm.001 14c-2.332 0-4.145 1.636-5.093 2.797l.471.58c1.286-.819 2.732-1.308 4.622-1.308s3.336.489 4.622 1.308l.471-.58c-.948-1.161-2.761-2.797-5.093-2.797zm-3.501-6c-.828 0-1.5.671-1.5 1.5s.672 1.5 1.5 1.5 1.5-.671 1.5-1.5-.672-1.5-1.5-1.5zm7 0c-.828 0-1.5.671-1.5 1.5s.672 1.5 1.5 1.5 1.5-.671 1.5-1.5-.672-1.5-1.5-1.5z"></path>
                        </svg>
                    </h2>
                    <p class="lead">Чтобы добавить песни в избранное, нажмите на кнопку ниже и выберите понравившиеся
                        вам
                        песни</p>
                    <hr class="my-4">
                    <p>Если хотите что-либо конкретное, то перейдите во вкладку поиск и ищите песни по названию и
                        исполнителям</p>
                    <form action="${context.getContextPath()}/feed" method="get">
                        <button class="btn btn-dark btn-lg">Выбрать песни</button>
                    </form>
                </div>
            </div>
        </#if>
    </div>
    <@p.player/>
</@c.template>