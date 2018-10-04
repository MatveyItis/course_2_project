<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign Up</title>
</head>

<body>
<div class='container' style="width:300px;height:400px;
    margin: 30px; background-color: cornflowerblue;
    border: black 2px solid" align="center">
    <form method='post' action="signUp">
        <label for='firstName'>First Name</label><br>
        <input type='text' name='firstName' placeholder='First Name' id='firstName'><br>
        <label for='lastName'>LastName</label><br>
        <input type='text' name='lastName' placeholder='LastName' id='lastName'><br>
        <label for='email'>Email</label><br>
        <input type='text' name='email' placeholder='Email' id='email'><br>
        <label for='password'>Password</label><br>
        <input type='password' name='password' placeholder='Password' id='password'><br>
        <input type='submit' value='Sign Up'>
    </form>
</div>
</body>
</html>