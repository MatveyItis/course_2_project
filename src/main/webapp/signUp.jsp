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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <title>Sign Up</title>
</head>
<body>
<header>

</header>
<div class='container col-lg-6' align="center" style="padding: 30px;">
    <form method='post' action="signUp">
        <div class="form-group col-xl-6 col-md-8 col-sm-10">
            <label for='firstName'>First Name</label>
            <input type='text' class="form-control" name='firstName' placeholder='First Name' id='firstName'>
        </div>
        <div class="form-group col-xl-6 col-md-8 col-sm-10">
            <label for='lastName'>Last Name</label>
            <input type='text' class="form-control" name='lastName' placeholder='Last Name' id='lastName'>
        </div>
        <div class="form-group col-xl-6 col-md-8 col-sm-10">
            <label for='email'>Email</label>
            <input type='email' class="form-control" name='email' placeholder='Email' id='email'>
        </div>
        <div class="form-group col-xl-6 col-md-8 col-sm-10">
            <label for='password'>Password</label>
            <input type='password' class="form-control" name='password' placeholder='Password' id='password'>
        </div>
        <div style="margin: 25px">
            <button type="submit" class="btn btn-secondary">Sign Up</button>
            <a href="signIn" role="button" class="btn btn-primary">Sign In</a>
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
</body>
</html>