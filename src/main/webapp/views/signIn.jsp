<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 05/10/2018
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script>
        <%@include file="/js/scroll.js"%>
    </script>
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file="/css/style.css"%>
    </style>
    <title>Sign In</title>
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
            <h1>Sign In</h1>
        </div>
        <form method='post' action="signIn">
            <input type='email' id="email" name='email' placeholder="E-mail">
            <input type='password' id="password" name='password' placeholder='Password'>
            <button type="submit">Sign In</button>
        </form>
    </div>
</div>
</body>
</html>