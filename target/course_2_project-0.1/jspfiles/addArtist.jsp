<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 07/10/2018
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <title>Adding Artist</title>
</head>
<body>
<div class="container col-lg-8" align="center" style="padding: 30px; border-radius: 10px;">
    <form method="post" action="addArtist" style="border-radius: 10px; background-color: lavender; padding: 10px">
        <span><h3>Добавление артиста в базу данных</h3></span>
        <div class="form-group col-xl-6 col-md-9 col-sm-9" align="left">
            <label for="nickname">Enter artist's nickname</label>
            <input type="text" name="nickname" class="form-control" id="nickname"
                   placeholder="Nickname">
        </div>
        <div class="form-group col-xl-6 col-md-9 col-sm-9" align="left">
            <label for="firstName">Enter first name</label>
            <input type="text" name="firstName" class="form-control" id="firstName"
                   placeholder="First Name">
        </div>
        <div class="form-group col-xl-6 col-md-9 col-sm-9" align="left">
            <label for="lastName">Enter last name</label>
            <input type="text" name="lastName" class="form-control" id="lastName"
                   placeholder="Last Name">
        </div>
        <div class="form-group col-xl-6 col-md-9 col-sm-9" align="left">
            <label for="birthday">Birthday</label>
            <input type="date" name="birthday" class="form-control" id="birthday" placeholder="Birthday">
        </div>
        <div class="form-group col-xl-6 col-md-9 col-sm-9" align="left">
            <label for="genreName">Select the Genre</label>
            <select class="form-control" name="genreName" id="genreName">
                <option>Rap</option>
                <option>Pop</option>
                <option>Rock</option>
                <option>Electronic</option>
                <option>Classical</option>
                <option>Hip hop</option>
                <option>Blues</option>
                <option>Country</option>
                <option>Dance</option>
                <option>Easy listening</option>
                <option>Folk</option>
                <option>Heavy metal</option>
                <option>Jazz</option>
                <option>Opera</option>
                <option>Techno</option>
                <option>Reggae</option>
            </select>
        </div>
        <button type="submit" class="btn btn-secondary">Add Artist</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
