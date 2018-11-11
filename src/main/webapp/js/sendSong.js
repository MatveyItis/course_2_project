function sendSongId(songId) {
    $.ajax({
            type: 'post',
            url: '/library',
            data: {
                songId: songId
            }
        }
    ).done(function () {
        var differentButton = "<img src=\"https://img.icons8.com/metro/50/000000/checkmark.png\" width='20' height='20'>";
        var buttonDiv = document.getElementById("b" + songId);
        buttonDiv.innerHTML = "";
        buttonDiv.innerHTML = differentButton;
    }).fail(function () {
        alert("Error");
    });
};
