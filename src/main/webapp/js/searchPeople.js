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
                    if (typeof data[0].firstName === 'undefined' || typeof data[0].lastName === 'undefined') {
                        document.getElementById('resultPeople').innerHTML = "<h5 align='center'>Not found</h5>"
                    } else {
                        var resultSearch = "<ol>\n";
                        for (var i = 0; i < data.length; i++) {
                            resultSearch += "<li>\n";
                            resultSearch += "<div class='container row' style='margin: 5px'>\n" +
                                "       <div class='col-md-3'>\n" +
                                "           <img src='/images/avatarcat.png' width='135' height='135' style='border-radius: 50%'>\n" +
                                "       </div>\n" +
                                "       <div class='col-md-9'>\n" +
                                "           <h3>" + data[i].firstName + " " + data[i].lastName + "</h3>\n" +
                                "       </div>\n" +
                                "   </div>\n" +
                                "</li>\n" +
                                "<hr>\n";
                        }
                        resultSearch += "</ol>\n";
                        var resultSearchBlock = document.getElementById('resultPeople');
                        resultSearchBlock.innerHTML = resultSearch;
                    }
                } else {
                    document.getElementById('resultPeople').innerHTML = "<h5 align='center'>Not found</h5>";
                }
            }).fail(function () {
                alert("Error. Ну емае, Матвей, когда код писать научишься?")
            })
        };
});