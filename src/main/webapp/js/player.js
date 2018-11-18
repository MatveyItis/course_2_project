var isPlaying = false;
var currentSong = 1;

function setCurrentTrackName() {

    document.getElementById('track-name').innerHTML;
};

function playMusic(e) {
    var aud = document.getElementById(e);
    if (!isPlaying && currentSong === 1) {
        aud.play();
        currentSong = e;
        isPlaying = true;
        setPlayPause();
    } else if (!isPlaying && currentSong !== 1) {
        pauseMusic(currentSong);
        aud.play();
        currentSong = e;
        isPlaying = true;
        setPlayPause();
    } else if (currentSong === e && isPlaying) {
        pauseMusic(e);
    } else if (isPlaying) {
        stopMusic(currentSong);
        aud.play();
        currentSong = e;
        isPlaying = true;
        setPlayPause();
    }
};

function pauseMusic(e) {
    var aud = document.getElementById(e);
    aud.pause();
    isPlaying = false;
    setPlayPause();
};

function stopMusic(e) {
    if (e !== null) {
        var aud = document.getElementById(e);
        aud.pause();
        aud.currentTime = 0;
        isPlaying = false;
        setPlayPause();
    }
};

function playTrack() {
    if (isPlaying) {
        pauseMusic(currentSong);
        isPlaying = false;
    } else if (!isPlaying && currentSong === 1) {
        playMusic(1);
        isPlaying = true;
    } else if (!isPlaying && currentSong !== 1) {
        playMusic(currentSong);
        isPlaying = true;
    }
};

function playNextTrack() {
    if (isPlaying) {
        stopMusic(currentSong);
        if (currentSong === document.getElementById('music').getElementsByTagName('li').length) {
            playMusic(1);
        } else {
            playMusic(currentSong + 1);
        }
        isPlaying = true;
    } else {
        if (currentSong === document.getElementById('music').getElementsByTagName('li').length) {
            currentSong = 1;
        } else {
            currentSong = currentSong + 1;
        }
    }
};

function playPrevTrack() {
    if (isPlaying) {
        stopMusic(currentSong);
        if (currentSong === 1) {
            playMusic(document.getElementById('music').getElementsByTagName('li').length);
        } else {
            playMusic(currentSong - 1);
        }
        isPlaying = true;
    } else {
        if (currentSong === 1) {
            currentSong = 1;
        } else {
            currentSong = currentSong - 1;
        }
    }
};

function setPlayPause() {
    var playPause = document.getElementById('play-pause');
    playPause.innerHTML = "";
    if (isPlaying) {
        playPause.innerHTML = "<img src='/images/player/iconmonstr-media-control-49-24.png'>";
    } else {
        playPause.innerHTML = "<img src='/images/player/iconmonstr-media-control-48-24.png'>";
    }
};
