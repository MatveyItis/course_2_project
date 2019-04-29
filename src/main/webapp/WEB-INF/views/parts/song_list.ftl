<#include "security.ftl">
<#import "pager.ftl" as pager>

<div class="container shadow bg-white rounded mt-3" style="margin-bottom:80px">
    <h5 class="d-flex justify-content-center pt-2">Enjoy songs from all young singers!</h5>
    <@pager.pager url page/>
    <div class="card-columns" id="song-list" data-count="${page.content?size}">
        <#list page.content as song>
            <div class="card my-3" data-id="${song.id}">
                <#if song.imgFileName??>
                    <img role="button" alt="" src="/img/${song.imgFileName}" style="height: 240px; width: 100%"
                         class="card-img-top" onclick="playMusic(${song_index})"/>
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
                        <a class="col align-self-center" align="center" href="/songs/${song.id}/like"
                           style="text-decoration: none">
                            <#if song.meLiked>
                                <i class="fas fa-heart"></i>
                            <#else>
                                <i class="far fa-heart"></i>
                            </#if>
                            ${song.likes}
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
            <audio hidden id="song_id_${song_index}" data-author="${song.author.username}" data-title="${song.title}"
                   onended="playNextTrack()">
                <source src="/audio/${song.audioFileName}">
            </audio>
        <#else>
            No songs
        </#list>
    </div>
    <@pager.pager url page/>
</div>