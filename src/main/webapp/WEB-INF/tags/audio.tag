<%@tag description="Tag for audio form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="song" required="true" type="ru.itis.maletskov.models.Song" %>
<%@attribute name="audioId" required="true" type="java.lang.Integer" %>

<li>
    <div class='container row' style='margin: 5px;'>
        <div class='col-md-2'>
            <b></b>
            <button class='btn btn-dark' type='button' onclick='playMusic(${audioId})'>
                <img id='play' src='${song.getArtist().getArtistImgSrc()}'
                     width='24' height='24'>
            </button>
        </div>
        <div class='col-md-7'>
            <audio id='${audioId}' onended='playNextTrack()'>
                <source src='${song.getSongSrc()}' type='audio/mpeg'>
            </audio>
            <h6 id='track-name'>${song.getArtist().getNickname()} - ${song.getTitle()}</h6>
        </div>
        <div class='col-md-2' align='right'>
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