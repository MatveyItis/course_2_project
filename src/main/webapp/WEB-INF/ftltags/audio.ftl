<#function audio song audioId>
    <#assign t>
<li>
    <div class='container row' style='margin: 5px;'>
        <div class='col-md-3 col-sm-3'>
            <a role='button' onclick='playMusic(${audioId})'>
                <img src="${song.getArtist().getArtistImgSrc()}" class="d-block w-50">
            </a>
        </div>
        <div class='col-md-7 col-sm-7'>
            <audio id='${audioId}' onended='playNextTrack()'>
                <source src='${song.getSongSrc()}' type='audio/mpeg'>
            </audio>
            <h6 id='track-name'>${song.getArtist().getNickname()} - ${song.getTitle()}</h6>
        </div>
        <div class='col-md-2 col-sm-2' align='right' id='addingSong${song.getSongId()}'>
            <#if !song.isHaving()>
                <button id='b${song.getSongId()}' onclick='addSong(${song.getSongId()})' type='button'
                        class='btn btn-primary btn-dark'>
                    <b>+</b>
                </button>
            <#else>
                <button id='r${song.getSongId()}' onclick='removeSong(${song.getSongId()})' type='button'
                        class='btn btn-primary btn-dark'>
                    <b>-</b>
                </button>
            </#if>
        </div>
    </div>
    <hr>
</li>
    </#assign>
    <#return t>
</#function>