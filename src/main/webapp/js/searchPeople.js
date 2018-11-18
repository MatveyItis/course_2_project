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
                            "           <a href='/" + data[i].email.split('@')[0] + "'><img src='/images/avatarcat.png' class='d-block w-75' style='border-radius: 50%'></a>\n" +
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