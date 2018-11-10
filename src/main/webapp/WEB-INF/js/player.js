var isPlaying = false;
var currentSong = 0;

function playMusic(e) {
    var aud = document.getElementById(e);
    if (isPlaying === false && currentSong === 0) {
        aud.play();
        currentSong = e;
        isPlaying = true;
    } else if (isPlaying === false && currentSong !== 0) {
        pauseMusic(currentSong);
        aud.play();
        currentSong = e;
        isPlaying = true;
    } else if (currentSong === e && isPlaying === true) {
        pauseMusic(e);
        isPlaying = false;
    } else if (isPlaying === true) {
        stopMusic(currentSong);
        aud.play();
        currentSong = e;
        isPlaying = true;
    }
};

function pauseMusic(e) {
    var aud = document.getElementById(e);
    aud.pause();
};

function stopMusic(e) {
    if (e !== null) {
        var aud = document.getElementById(e);
        aud.pause();
        aud.currentTime = 0;
    }
};