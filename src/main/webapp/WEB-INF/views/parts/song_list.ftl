<#include "security.ftl">
<#import "pager.ftl" as pager>

<div class="container shadow bg-white rounded mt-3" style="margin-bottom:80px">
    <h5 class="d-flex justify-content-center pt-2">Enjoy songs from all young singers!</h5>
    <@pager.pager url page/>
    <div class="card-columns" id="song-list" data-count="${page.content?size}">
        <#list page.content as song>
            <div class="card my-3 box" data-id="${song.id}">
                <#if song.imgFileName??>
                    <img role="button" alt="" src="/img/${song.imgFileName}"
                         class="card-img-top img100" onclick="playMusic(${song_index})"/>
                </#if>
                <div class="m-2">
                    <span>${song.title}</span>
                    <br>
                    <i>#${song.tag}</i>
                </div>
                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-center"
                           href="${context.getContextPath()}/user-songs/${song.author.id}"
                           style="text-decoration: none">${song.author.username}</a>
                        <a class="col align-self-center" align="center" role="button"
                           name="${song.id}" href="${context.getContextPath()}/songs/${song.id}/like"
                           style="text-decoration: none">
                            <#if song.meLiked>
                                <i class="fas fa-heart" style="color: red"></i>
                            <#else>
                                <i class="far fa-heart"></i>
                            </#if>
                            ${song.likes}
                        </a>
                        <#if song.author.id == currentUserId>
                            <a class="col align-self-center d-flex justify-content-end" style="text-decoration: none"
                               href="${context.getContextPath()}/user-songs/${song.author.id}?song=${song.id}">
                                <i class="far fa-edit"></i>
                            </a>
                        </#if>
                        <a class="col align-self-center d-flex justify-content-end" style="text-decoration: none"
                           href="${context.getContextPath()}/user/${user.id}/add_song/?song=${song.id}">
                            <i class="fas fa-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <audio preload="auto" hidden id="song_id_${song_index}" data-author="${song.author.username}"
                   data-title="${song.title}"
                   onended="playNextTrack()">
                <source src="/audio/${song.audioFileName}">
            </audio>
            <#if songIdError?? && addingError??>
                <#if songIdError == song.id>
                    <small style="color: red">${addingError}</small>
                </#if>
            </#if>
        <#else>
            No songs
        </#list>
    </div>
    <@pager.pager url page/>
</div>