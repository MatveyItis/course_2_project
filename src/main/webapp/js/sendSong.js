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
    }).fail(function () {
        alert('Опять 25. Пишите разработчику в вк, чтобы чинил сервак, все упало:(');
    });
};

function removeSong(songId) {
    $.ajax({
        type: 'DELETE',
        url: '/library',
        data: {
            songId: songId
        },
        dataType: 'json'
    }).done(function () {
        location.reload();
    }).fail(
        alert('Опять 25. Пишите разработчику в вк, чтобы чинил сервак, все упало:(')
    );
}
