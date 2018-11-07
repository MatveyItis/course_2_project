<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 06/11/2018
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:if test="${not empty admin}">
    <t:layout title="Admin Page">
        <t:navbar/>
        <div class="container-fluid" style="padding: 20px">
            <div class="container col-6" align="center" style="padding: 20px; background-color: white">
                <h3>Song Upload</h3>
                <div class="container">
                    <form method="post">
                        <div class="form-group">
                            <label for="song-title">Enter please title of song</label>
                            <input type="text" class="form-control" placeholder="Song title" id="song-title"
                                   name="song-title">
                        </div>
                        <div class="custom-file col-10">
                            <label class="custom-file-label" for="song-file">Choose file</label>
                            <input type="file" class="custom-file-input" id="song-file" name="song-file">
                        </div>
                        <input type="submit" class="btn btn-dark" value="Upload">
                    </form>
                </div>
            </div>
            <div class="container col-6" align="center" style="padding: 20px; background-color: white">
                <h3>Adding Artist to Music Library</h3>
                <div class="container">
                    <form method="post">

                    </form>
                </div>
            </div>
            <div class="container col-6" align="center" style="padding: 20px; background-color: white"></div>
            <div class="container col-6" align="center" style="padding: 20px; background-color: white"></div>
        </div>
    </t:layout>
</c:if>
<c:if test="${empty admin}">
    <t:layout title="Admin Page">
        <div class="container" style="padding-top: 20%;" align="center">
            <h1 style="color: #fff;">Access denied</h1>
        </div>
    </t:layout>
</c:if>