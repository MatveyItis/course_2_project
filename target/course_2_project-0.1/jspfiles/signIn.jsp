<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 05/10/2018
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <title>Sign In</title>
</head>
<body>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="">MWeb</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarText" aria-controls="navbarText"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Features</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Pricing</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="signUp" aria-expanded="true">Sign Up</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">${locale.get("header.language")}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="?lang=En">${locale.get("header.language.en")}</a>
                    <a class="dropdown-item" href="?lang=Ru">${locale.get("header.language.ru")}</a>
                   <%-- <div class="dropdown-divider"></div>
                    <a class="dropdown-item disabled" href=""></a>--%>
                </div>
            </li>
        </ul>
        <span class="navbar-text">
            ${locale.get("signup.header.inscription")}
            <img src="https://images.vector-images.com/104/ministry_culture_emb_n15700.gif" alt="herb"
                 style="width: 28px;height: 28px">
    </span>
    </div>
</nav>
<div class="container col-lg-5 col-xl-4 col-md-6 col-sm-7 col-xs-7" style="padding: 90px 30px 30px 30px;" align="center">
    <h2>${locale.get("signin.auth")}</h2>
    <form method='post' action="signIn">
        <div class="form-group">
            <label for='email'>${locale.get("signup.email")}</label>
            <input type='email' class="form-control" id="email" name='email' placeholder="${locale.get("signup.email")}">
        </div>
        <div class="form-group">
            <label for='password'>${locale.get("signup.password")}</label>
            <input type='password' class="form-control" id="password" name='password' placeholder='${locale.get("signup.password")}'>
        </div>
        <button type="submit" class="btn btn-primary">${locale.get("signup.signin")}</button>
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