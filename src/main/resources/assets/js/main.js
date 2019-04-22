/*Player JS*/

var isPlaying = false;
var currentSong = 1;

function setCurrentTrackName() {
    document.getElementById('track-name').innerHTML;
}

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
}

function pauseMusic(e) {
    var aud = document.getElementById(e);
    aud.pause();
    isPlaying = false;
    setPlayPause();
}

function stopMusic(e) {
    if (e !== null) {
        var aud = document.getElementById(e);
        aud.pause();
        aud.currentTime = 0;
        isPlaying = false;
        setPlayPause();
    }
}

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
}

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
}

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
}

function setPlayPause() {
    var playPause = document.getElementById('play-pause');
    playPause.innerHTML = "";
    if (isPlaying) {
        playPause.innerHTML = "<img audioFileName='/assets/files/player/iconmonstr-media-control-49-240.png' width='24' height='24'>";
    } else {
        playPause.innerHTML = "<img audioFileName='/assets/files/player/iconmonstr-media-control-48-240.png' width='24' height='24'>";
    }
}

/*Search People*/

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('userName').oninput =
        function searchFriends() {
            $.ajax({
                type: 'post',
                url: '/profile/search',
                data: {
                    userName: document.getElementById('userName').value
                }
            }).done(function (data) {
                document.getElementById('resultPeople').innerHTML = "";
                if (data.length > 0) {
                    var resultSearch = "<ol>\n";
                    for (var i = 0; i < data.length; i++) {
                        resultSearch += "<li>\n";
                        resultSearch += "<div class='container row' style='margin: 5px'>\n" +
                            "       <div class='col-md-3 col-sm-3'>\n" +
                            "           <a href=" + data[i].email.split('@')[0] + "'/'><img audioFileName='/assets/files/avatarcat.png' class='d-block w-75' style='border-radius: 50%'></a>\n" +
                            "       </div>\n" +
                            "       <div class='col-md-9 col-sm-9 d-flex justify-content-between align-items-center'>\n" +
                            "           <h4>" + data[i].firstName + " " + data[i].lastName + "</h4>\n" +
                            "           <button class='btn btn-dark' role='button'>Add to friends</button>" +
                            "       </div>\n" +
                            "   </div>\n" +
                            "</li>\n" +
                            "<hr>\n";
                    }
                    resultSearch += "</ol>\n";
                    var resultSearchBlock = document.getElementById('resultPeople');
                    resultSearchBlock.innerHTML = resultSearch;

                } else {
                    document.getElementById('resultPeople').innerHTML = "<h5 align='center'>Not found</h5>";
                }
            }).fail(function () {
                alert("Error. Ну емае, Матвей, когда код писать научишься?")
            })
        };
});

/*Search Songs*/

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('songName').oninput =
        function searchSong() {
            $.ajax({
                type: 'post',
                url: '/library/search',
                data: {
                    songName: document.getElementById('songName').value
                }
            }).done(function (data) {
                document.getElementById('result').innerHTML = "";
                if (data.length > 0) {
                    var resultSearch = "<ol>";
                    for (var i = 0; i < data.length; i++) {
                        resultSearch += "<li>\n" +
                            "<hr>\n" +
                            "    <div class='container row' style='margin: 5px;'>\n" +
                            "        <div class='col-md-2'>\n" +
                            "            <img audioFileName='" + data[i].artist.artistImgSrc + "' class='d-block w-50'>\n" +
                            "        </div>\n" +
                            "        <div class='col-md-8'>\n" +
                            "            <h6 id='track-name'>" + data[i].artist.nickname + " - " + data[i].title + "</h6>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</li>\n" +
                            "<hr>";
                    }
                    resultSearch += "</ol>";
                    var resultSearchBlock = document.getElementById('result');
                    resultSearchBlock.innerHTML = resultSearch;
                } else {
                    document.getElementById('result').innerHTML = "<h5 align='center'>Nothing found</h5>";
                }
            }).fail(function () {
                alert("Error. Ну емае, Матвей, когда код писать научишься?")
            })
        };
});

/*Adding and Removing Songs*/

function addSong(songId) {
    $.ajax({
            type: 'post',
            url: '/library',
            data: {
                songId: songId
            }
        }
    ).done(function () {
        document.getElementById('addingSong' + songId).innerHTML = "";
        document.getElementById('addingSong' + songId).innerHTML = "<button id='r" + songId + "' onclick='removeSong(" + songId + ")' type='button'\n" +
            "                        class='btn btn-primary btn-dark'>" +
            "                    <b>-</b>" +
            "                </button>";
    }).fail(function () {
        alert('Опять 25. Пишите разработчику в вк, чтобы чинил сервак, все упало:(');
    });
};

function removeSong(songId) {
    $.ajax({
        type: 'delete',
        url: '/library?id=' + songId
    }).done(function () {
        if (document.location.pathname.indexOf('profile') + 1) {
            document.getElementById('addingSong' + songId).parentElement.parentElement.remove();
        } else {
            document.getElementById('addingSong' + songId).innerHTML = "";
            document.getElementById('addingSong' + songId).innerHTML = "<button id='b" + songId + "' onclick='addSong(" + songId + ")' type='button'\n" +
                "                        class='btn btn-primary btn-dark'>" +
                "                    <b>+</b>" +
                "                </button>";
        }
    }).fail(function () {
        alert('Опять 25. Пишите разработчику в вк, чтобы чинил сервак, все упало:(');
    })
}