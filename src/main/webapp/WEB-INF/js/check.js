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
    }
});
