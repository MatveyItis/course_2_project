/*Player JS*/

var isPlaying = false;
var currentSong = 0;
var trackerStarted = false;

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
    if (!trackerStarted) {
        playing();
        trackerStarted = true;
    }
    if (!isPlaying && currentSong === 0) {
        aud.play();
        currentSong = e;
        isPlaying = true;
    } else if (!isPlaying && currentSong !== 0) {
        pauseMusic(currentSong);
        aud.play();
        currentSong = e;
        isPlaying = true;
    } else if (currentSong === e && isPlaying) {
        pauseMusic(e);
    } else if (isPlaying) {
        stopMusic(currentSong);
        aud.play();
        currentSong = e;
        isPlaying = true;
    }
    changeMaxTimeForRange();
    changeStatus();
}

function pauseMusic(e) {
    let aud = document.getElementById('song_id_' + e);
    aud.pause();
    isPlaying = false;
    changeStatus();
}

function stopMusic(e) {
    document.getElementById('audio_time_range').value = 0;
    if (e !== null) {
        let aud = document.getElementById('song_id_' + e);
        aud.pause();
        aud.currentTime = 0;
        isPlaying = false;
    }
    changeStatus();
}

function playNextTrack() {
    document.getElementById('audio_time_range').value = 0;
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
    document.getElementById('audio_time_range').value = 0;
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

function playing() {
    let maxTime = Number(document.getElementById('audio_time_range').max);
    setInterval(function () {
        if ((Number(document.getElementById('audio_time_range').value) - 1) === maxTime ||
            (Number(document.getElementById('audio_time_range').value) + 1) === maxTime) {
            document.getElementById('audio_time_range').value = 0;
        }
        if (isPlaying && Number(document.getElementById('audio_time_range').value) < maxTime - 1) {
            document.getElementById('audio_time_range').stepUp();
        }
    }, 100);
}

function addInputForFile() {
    let songs = document.getElementById('songs');
    let count = Number(songs.getAttribute('data-count'));
    let newSong = '<div class="row">\n' +
        '<div class="form-group col-6">\n' +
        '      <input type="text"\n' +
        '       class="form-control"\n' +
        '       value="" name="song_title_' + (count + 1) + '" id="song_title_' + (count + 1) + '" \n' +
        '       placeholder="Enter a song title" required/>\n' +
        '</div>\n' +
        '<div class="form-group col-6">\n' +
        '   <div class="custom-file">\n' +
        '       <input type="file" name="audio_file_' + (count + 1) + '"\n' +
        '        id="audio_file_' + (count + 1) + '"\n' +
        '      class="form-control"\n' +
        '          accept="audio/*"/>\n' +
        '            <label class="custom-file-label" for="audio_file_' + (count + 1) + '">Choose song</label>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>';
    songs.setAttribute('data-count', count + 1);
    songs.innerHTML = songs.innerHTML + newSong;
}

document.addEventListener("DOMContentLoaded", function () {
    let form = document.getElementById("album_form");

    form.onsubmit = function (e) {
        e.preventDefault();
        let count = Number(songs.getAttribute('data-count'));
        let songTitles = [];
        let audioFiles = [];
        let formData = new FormData();
        for (let i = 1; i <= count; i++) {
            songTitles[i] = document.getElementById('song_title_' + i).value;
            audioFiles[i] = document.getElementById('audio_file_' + i).value;
            formData.append();
        }

    };
});


//AJAX likes
//TODO: сделать ajax лайки
/*
$(function() {
    $('form').submit(function(e) {
        var $form = $(this);
        $.ajax({
            type: $form.attr('method'),
            url: $form.attr('action'),
            data: $form.serialize()
        }).done(function() {
            console.log('success');
        }).fail(function() {
            console.log('fail');
        });
        //отмена действия по умолчанию для кнопки submit
        e.preventDefault();
    });
});
*/

document.addEventListener("DOMContentLoaded", function (e) {
    document.getElementById('song_like').onclick = function (ev) {
        ev.preventDefault();
        var $form = $(ev);
        $.ajax({
            type: $form.attr('method'),
            url: $form.attr('action')
        }).done(function (data) {
            console.log(data);
        }).fail(function () {
            alert('Что-то не так');
        })
    }
});