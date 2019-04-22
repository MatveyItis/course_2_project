<#macro player>
    <nav class="navbar fixed-bottom navbar-light bg-light">
        <div class="justify-content-between m-1">
            <a role="button" onclick="playPrevTrack()" class="pr-4"><i class="fas fa-backward"></i></a>
            <a role="button" id="played" onclick="playMusic(currentSong)" class="pr-2 pl-2"><i class="fas fa-play"></i></a>
            <a role="button" onclick="playNextTrack()" class="pl-4"><i class="fas fa-forward"></i></a>
        </div>
        <div class="col-8 col-sm-5 col-md-8">
            <input type="range" class="custom-range" id="audio_time_range" min="0" max="200" value="0"
                   onchange="setAudioTime(document.getElementById('audio_time_range').value)">
        </div>
        <div>
            <small id="song_title">No actions</small>
        </div>

    </nav>
</#macro>