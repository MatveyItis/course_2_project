document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('songName').oninput =
        function searchSong() {
            $.ajax({
                type: 'post',
                url: '/search',
                data: {
                    songName: document.getElementById('songName').value
                }
            }).done(function (data) {
                document.getElementById('result').innerHTML = "";
                var resultSearch = "<ol>";
                for (var i = 0; i < data.length; i++) {
                    resultSearch += "<li>\n" +
                        "    <div class='container row' style='margin: 5px;'>\n" +
                        "        <div class='col-md-2'>\n" +
                        "            <b></b>\n" +
                        "            <button class='btn btn-dark' type='button'>\n" +
                        "                <img src='" + data[i].artist.artistImgSrc + "' width='24' height='24'>\n" +
                        "            </button>\n" +
                        "        </div>\n" +
                        "        <div class='col-md-8'>\n" +
                        "            <h6 id='track-name'>" + data[i].artist.nickname + " - " + data[i].title + "</h6>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</li>";
                }
                resultSearch += "</ol>";
                var resultSearchBlock = document.getElementById('result');
                resultSearchBlock.innerHTML = resultSearch;
            }).fail(function () {
                alert("Error. Ну емае, Матвей, когда код писать научишься?")
            })
        }
});