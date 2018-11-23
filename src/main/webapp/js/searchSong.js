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
                            "            <img src='" + data[i].artist.artistImgSrc + "' class='d-block w-50'>\n" +
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