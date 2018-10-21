<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 05/10/2018
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie-edge">
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="/scripts/scroll.js"></script>
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>Sign Up</title>
</head>
<body>
<div class="wrapper">
    <header>
        <nav>
            <div class="menu-icon">
                <i class="fa fa-bars fa-2x"></i>
            </div>
            <div class="logo">
                WEBM
            </div>
            <div class="menu">
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <div class="header">
        <h1>Registration</h1>
        <p>We are glad to see you on our web-site. We are trying to improve connection from user to developer. Thank you for choosing our web-site.</p>
    </div>
    <div class="form">
        <form method="post">
            <input type='text' placeholder='First Name' id='firstName'>
            <input type='text' placeholder='Last Name' id='lastName'>
            <input type='email' placeholder='E-mail' id='email'>
            <input type='password' name='password' placeholder='Password' id='password'>
            <button type="submit" class="btn btn-primary">Sign Up</button>
            <a href="signIn" role="button" class="btn btn-secondary">Sign In</a>
        </form>
    </div>
</div>
<%--
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
                    &lt;%&ndash; <div class="dropdown-divider"></div>
                     <a class="dropdown-item disabled" href=""></a>&ndash;%&gt;
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
<div class='container col-lg-5 col-xl-5 col-md-6 col-sm-8 col-xs-8' align="center" style="padding: 90px 30px 30px 30px;">
    <h2>${locale.get('signup.registration')}</h2>
    <form method='post'>
        <div class="form-group">
            <label for='firstName'>${locale.get('signup.firstname')}</label>
            <input type='text' class="form-control" name='firstName' placeholder='${locale.get("signup.firstname")}' id='firstName'>
        </div>
        <div class="form-group">
            <label for='lastName'>${locale.get("signup.lastname")}</label>
            <input type='text' class="form-control" name='lastName' placeholder='${locale.get("signup.lastname")}' id='lastName'>
        </div>
        <div class="form-group">
            <label for='email'>${locale.get("signup.email")}</label>
            <input type='email' class="form-control" name='email' placeholder='${locale.get("signup.email")}' id='email'>
        </div>
        <div class="form-group">
            <label for='password'>${locale.get("signup.password")}</label>
            <input type='password' class="form-control" name='password' placeholder='${locale.get("signup.password")}' id='password'>
        </div>
        <div style="margin: 25px">
            <button type="submit" class="btn btn-primary">${locale.get("signup.signup")}</button>
            <a href="signIn" role="button" class="btn btn-secondary">${locale.get("signup.signin")}</a>
        </div>
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
--%>
</body>
</html>