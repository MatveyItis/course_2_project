document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("reg").onsubmit = function (e) {
        pass1 = document.getElementById("passwordFirst").value;
        pass2 = document.getElementById("passwordSecond").value;
        email = document.getElementById("email").value;
        firstName = document.getElementById("firstName").value;
        lastName = document.getElementById("lastName").value;
        if (pass1 !== pass2) {
            e.preventDefault();
            alert("Passwords is not similar");
        } else if (firstName.length < 2 && lastName.length < 2) {
            e.preventDefault();
            alert("First name or last name have less 2 symbols! Try again");
        } else {
            alert("Success");
            document.forms.namedItem("reg").submit();
        }
        return false;
    };

    /*document.getElementById('updateInfo').onsubmit = function (e) {
        sendUpdate(e);
    };*/
});

function sendUpdate() {
    //e.preventDefault();
    fName = document.getElementById('firstName').value;
    lName = document.getElementById('lastName').value;
    email = document.getElementById('email').value;
    firstPassword = document.getElementById('firstPassword').value;
    secondPassword = document.getElementById('secondPassword').value;
    if (firstPassword !== secondPassword) {
        document.getElementById('failUpdate').click();
    }
    $.ajax({
            type: 'post',
            url: '/updateUserInfo',
            data: {
                firstName: fName,
                lastName: lName,
                email: email,
                firstPassword: firstPassword,
                secondPassword: secondPassword
            }
        }
    ).done(function (arr) {
        console.log(arr);
        if (arr === true) {
            document.getElementById('successUpdate').click();
        } else if (arr === false) {
            document.getElementById('failUpdate').click();
        }
    }).fail(function () {
        alert('fail');
    })
}
