<#include "security.ftl">

<div class="container">
    <h5 class="d-flex justify-content-center">Enjoy songs from all young singers!</h5>
    <div class="card-columns" id="song-list">
        <#list songs as song>
            <div class="card my-3" data-id="${song.id}">
                <#if song.songImg??>
                    <img alt="" src="/img/${song.songImg.fileName}" style="height: 300px; width: 100%"
                         class="card-img-top"/>
                </#if>
                <div class="m-2">
                    <span>${song.title}</span>
                    <br/>
                    <i>#${song.tag}</i>
                </div>
                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-center"
                           href="${context.getContextPath()}/user-songs/${song.author.id}">${song.author.username}</a>
                        <a class="col align-self-center" align="center" href="#" style="text-decoration: none">
                            <#if !true <#--message.meLiked-->>
                                <i class="fas fa-heart"></i>
                            <#else>
                                <i class="far fa-heart"></i>
                            </#if>
                            76
                        </a>
                        <#if song.author.id == currentUserId>
                            <a class="col align-self-center d-flex justify-content-end"
                               href="${context.getContextPath()}/user-songs/${song.author.id}?song=${song.id}">
                                <i class="far fa-edit"></i>
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        <#else>
            No songs
        </#list>
    </div>
</div>