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
        url: '/library?songId=' + songId
    }).done(function () {
        document.getElementById('addingSong' + songId).innerHTML = "";
        document.getElementById('addingSong' + songId).innerHTML = "<button id='b" + songId + "' onclick='addSong(" + songId + ")' type='button'\n" +
            "                        class='btn btn-primary btn-dark'>" +
            "                    <b>+</b>" +
            "                </button>";
    }).fail(function () {
        alert('Опять 25. Пишите разработчику в вк, чтобы чинил сервак, все упало:(');
    })
}
