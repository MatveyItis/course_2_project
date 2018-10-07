<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 05/10/2018
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<div class="container" style="width:400px;height:400px;
    padding-top: 30px;
    margin: 30px; background-color: cornflowerblue;
    border: black 1px solid" align="center">
    <form method='post' action="signIn">
        <label for='email'>E-mail</label><br>
        <input type='text' id='email' name='email' placeholder='E-mail'><br>
        <label for='password'>Password</label><br>
        <input type='password' id='password' name='password' placeholder='Password'><br>
        <input type='submit' value='Sign In'>
    </form>
</div>
</body>
</html>
