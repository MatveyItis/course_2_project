<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 05/10/2018
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Registration">
    <t:navbar/>
    <div class="container col-lg-6 col-xl-6 col-md-7 col-sm-8 col-xs-8 shadow-lg p-3 mb-5 bg-white rounded"
         style="margin-top: 30px"
         align="center">
        <div class='container-fluid' align="center">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item nav-item-dark">
                    <a class="nav-link active" id="signIn-tab" data-toggle="tab" href="#signIn" role="tab"
                       aria-controls="signIn" aria-selected="true">Log In</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="signUp-tab" data-toggle="tab" href="#signUp" role="tab"
                       aria-controls="signUp" aria-selected="false">Sign Up</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="signIn" role="tabpanel" aria-labelledby="signIn-tab">
                    <h2>Authentication</h2>
                    <form method="post" action="signIn" id="login">
                        <div class="form-group">
                            <label for='e-mail'>Email</label>
                            <input type='email' class="form-control" name='e-mail' placeholder='Email' id='e-mail'
                                   required>
                        </div>
                        <div class="form-group">
                            <label for='password'>Password</label>
                            <input type='password' class="form-control" name='password' placeholder='Password'
                                   id='password' minlength="8" required>
                        </div>
                        <button type="submit" class="btn btn-dark">Sign In</button>
                    </form>
                </div>
                <div class="tab-pane fade" id="signUp" role="tabpanel" aria-labelledby="signUp-tab">
                    <h2>Registration</h2>
                    <form method='post' action="signUp" id="reg" name="reg">
                        <div class="form-group">
                            <label for="firstName">First name</label>
                            <input type="text" class="form-control" name="firstName" placeholder="Enter first name"
                                   id="firstName" required>
                        </div>
                        <div class="form-group">
                            <label for="lastName">First name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="Enter last name"
                                   id="lastName" required>
                        </div>
                        <div class="form-group">
                            <label for='email'>Email</label>
                            <input type='email' class="form-control" name='email' placeholder='Enter email' id='email'
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="passwordFirst">Password</label>
                            <input type="password" class="form-control" name="passwordFirst"
                                   placeholder="Enter password"
                                   id="passwordFirst" required>
                        </div>
                        <div class="form-group">
                            <label for="passwordSecond">Password</label>
                            <input type="password" class="form-control" name="passwordSecond"
                                   placeholder="Repeat password"
                                   id="passwordSecond" required>
                        </div>
                        <div style="margin: 25px">
                            <button type="submit" class="btn btn-dark" id="submit">Sign Up</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/check.js"%>
    </script>
</t:layout>