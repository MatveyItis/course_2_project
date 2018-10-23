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
    <script>
        <%@include file="/js/scroll.js"%>
    </script>
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file="/css/style.css"%>
    </style>
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
    <div class="form">
        <div class="header">
            <h1>Registration</h1>
            <p>We are trying to improve connection from user to developer.
                Thank you for choosing our web-site.</p>
        </div>
        <form method="post">
            <input type='text' name="firstName" placeholder='First Name' id='firstName'>
            <input type='text' name="lastName" placeholder='Last Name' id='lastName'>
            <input type='email' name="email" placeholder='E-mail' id='email'>
            <input type='password' name='password' placeholder='Password' id='password'>
            <button type="submit">Sign Up</button>
            <a class="button" href="/signIn">Sign In</a>
        </form>
    </div>
</div>
</body>
</html>