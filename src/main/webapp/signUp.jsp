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
    <style>
        a {
            text-decoration: none;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign Up</title>
</head>
<body>
<div class='container' style="width:400px;height:400px;
    padding-top: 30px;
    margin: 30px; background-color: cornflowerblue;
    border: black 1px solid" align="center">
    <%//Date date = new Date();%>
    <form method='post' action="signUp">
        <label for='firstName'>First Name</label><br>
        <input type='text' name='firstName' placeholder='First Name' id='firstName'><br>
        <label for='lastName'>LastName</label><br>
        <input type='text' name='lastName' placeholder='LastName' id='lastName'><br>
        <label for='email'>Email</label><br>
        <input type='text' name='email' placeholder='Email' id='email'><br>
        <label for='password'>Password</label><br>
        <input type='password' name='password' placeholder='Password' id='password'><br>
        <input type='submit' value='Sign Up' style="margin-top: 10px;">
        <button style="margin-top: 25px; background-color: palegreen"><a href="signIn">Already registered?</a></button>
    </form>
    <%//=date.toString()%>
</div>
</body>
</html>