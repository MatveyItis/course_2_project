<%@tag description="Tag for audio form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="src" required="true" type="java.lang.String" %>
<%@attribute name="songId" required="true" type="java.lang.String" %>
<%@attribute name="songTitle" required="true" type="java.lang.String" %>
<%@attribute name="isHaving" type="java.lang.String" %>
<%@attribute name="artistImageSrc" type="java.lang.String" %>

<c:if test="${empty artistImageSrc}">
    <c:set var="artistImageSrc" value="/images/artists/default.png"/>
</c:if>

<div class="container" style="margin: 5px;">
    <div class="container row">
        <div class="col-1">
            <button class="btn btn-dark" type="button" onclick="playMusic(${songId})">
                <img id="play" src="${artistImageSrc}" width="24" height="24">
            </button>
        </div>
        <div class="col-5">
            <audio id="${songId}">
                <source src="${src}" type="audio/mpeg">
            </audio>
            <h6>${songTitle}</h6>
        </div>
        <div class="col-2">
            <c:if test="${isHaving == false}">
                <button id="b${songId}" onclick="sendSongId(${songId})" type="button" class="btn btn-primary btn-dark">
                    Add
                </button>
            </c:if>
            <c:if test="${isHaving == true}">
                <%--<button type="button" class="btn btn-primary btn-dark">
                    <img src="https://img.icons8.com/metro/50/000000/checkmark.png" width='20' height='20'>
                </button>--%>
            </c:if>
        </div>
    </div>
</div>