<%@tag pageEncoding="UTF-8" description="Player tag" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h6 id="player-track-name" align="center">Oxxxymiron - Ultima Thule</h6>
<div class="container d-flex justify-content-between align-items-center">
    <button type="button" class="btn btn-light" id="prev" onclick="playPrevTrack()"><img src='/images/player/prev.png'>
    </button>
    <button type="button" class="btn btn-light" id="play-pause" onclick="playTrack()"><img
            src='/images/player/iconmonstr-media-control-48-24.png'></button>
    <button type="button" class="btn btn-light" id="next" onclick="playNextTrack()"><img src='/images/player/next.png'>
    </button>
</div>