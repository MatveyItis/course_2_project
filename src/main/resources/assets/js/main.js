/*Player JS*/

var isPlaying = false;
var currentSong = 0;

function changeStatus() {
    if (isPlaying) {
        document.getElementById('played').innerHTML = '<i class="fas fa-pause"></i>';
    } else {
        document.getElementById('played').innerHTML = '<i class="fas fa-play"></i>';
    }
    let audio = document.getElementById('song_id_' + currentSong);
    let title = audio.getAttribute('data-title');
    let author = audio.getAttribute('data-author');
    document.getElementById('song_title').innerHTML = author + ' - ' + title;
}

function playMusic(e) {
    let aud = document.getElementById('song_id_' + e);
    if (!isPlaying && currentSong === 0) {
        aud.play();
        playing();
        currentSong = e;
        isPlaying = true;
    } else if (!isPlaying && currentSong !== 0) {
        pauseMusic(currentSong);
        aud.play();
        playing();
        currentSong = e;
        isPlaying = true;
    } else if (currentSong === e && isPlaying) {
        pauseMusic(e);
    } else if (isPlaying) {
        stopMusic(currentSong);
        aud.play();
        playing();
        currentSong = e;
        isPlaying = true;
    }
    changeMaxTimeForRange();
    changeStatus();
    playing();
}

function pauseMusic(e) {
    let aud = document.getElementById('song_id_' + e);
    aud.pause();
    isPlaying = false;
    changeStatus();
}

function stopMusic(e) {
    if (e !== null) {
        let aud = document.getElementById('song_id_' + e);
        aud.pause();
        aud.currentTime = 0;
        isPlaying = false;
    }
    changeStatus();
}

function playTrack() {
    if (isPlaying) {
        pauseMusic(currentSong);
        isPlaying = false;
    } else if (!isPlaying && currentSong === 0) {
        playMusic(0);
        isPlaying = true;
    } else if (!isPlaying && currentSong !== 0) {
        playMusic(currentSong);
        isPlaying = true;
    }
    changeStatus();
}

function playNextTrack() {
    let songsCount = document.getElementById('song-list').dataset.count;
    if (isPlaying) {
        stopMusic(currentSong);
        if (currentSong === (songsCount - 1) || currentSong > (songsCount - 1)) {
            playMusic(0);
        } else {
            playMusic(currentSong + 1);
        }
    } else {
        if (currentSong === (songsCount - 1) || currentSong > (songsCount - 1)) {
            currentSong = 0;
        } else {
            currentSong = currentSong + 1;
        }
    }
    changeStatus();
    changeMaxTimeForRange();
}

function playPrevTrack() {
    let songsCount = Number(document.getElementById('song-list').dataset.count);
    if (isPlaying) {
        stopMusic(currentSong);
        if (currentSong === 0) {
            playMusic(Number(document.getElementById('song-list').dataset.count));
        } else {
            playMusic(Number(currentSong - 1));
        }
        isPlaying = true;
    } else {
        if (currentSong > 0) {
            currentSong = currentSong - 1;
        } else {
            currentSong = songsCount - 1;
        }
    }
    changeStatus();
    changeMaxTimeForRange();
}

function setAudioTime(time) {
    let audio = document.getElementById('song_id_' + currentSong);
    audio.currentTime = time;
}

function changeMaxTimeForRange() {
    document.getElementById('audio_time_range').max =
        document.getElementById('song_id_' + currentSong).duration;
}

function sleep(ms) {
    ms += new Date().getTime();
    while (new Date() < ms) {
    }
}

function playing() {
    let maxTime = Number(document.getElementById('audio_time_range').max);
    for (let i = 0; i < Number(document.getElementById('audio_time_range').value); i++) {
        if (isPlaying && Number(document.getElementById('audio_time_range').value) < maxTime - 1) {
            document.getElementById('audio_time_range').value =
                Number(document.getElementById('audio_time_range').value) + 1;
        }
        sleep(1000)
    }
}
