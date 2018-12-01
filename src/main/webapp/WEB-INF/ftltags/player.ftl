<#macro player>
    <h6 id="player-track-name" align="center">Oxxxymiron - Ultima Thule</h6>
    <div class="container d-flex justify-content-between align-items-center">
        <button type="button" class="btn btn-light" id="prev" onclick="playPrevTrack()"><img
                src='/images/player/prev.png' width="24" height="24">
        </button>
        <button type="button" class="btn btn-light" id="play-pause" onclick="playTrack()"><img
                src='/images/player/iconmonstr-media-control-48-240.png' width="24" height="24"></button>
        <button type="button" class="btn btn-light" id="next" onclick="playNextTrack()"><img
                src='/images/player/next.png' height="24" width="24">
        </button>
    </div>
</#macro>