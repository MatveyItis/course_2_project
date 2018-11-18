<%@tag description="Tag for audio form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="song" required="true" type="ru.itis.maletskov.models.Song" %>
<%@attribute name="audioId" required="true" type="java.lang.Integer" %>

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
            <c:if test='${song.isHaving() == false}'>
                <button id='b${song.getSongId()}' onclick='sendSongId(${song.getSongId()})' type='button'
                        class='btn btn-primary btn-dark'>
                    Add
                </button>
            </c:if>
            <c:if test='${song.isHaving() == true}'>

            </c:if>
        </div>
    </div>
</li>
<hr>