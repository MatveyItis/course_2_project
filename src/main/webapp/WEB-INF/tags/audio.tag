<%@tag description="Tag for audio form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="src" required="true" type="java.lang.String" %>
<%@attribute name="songId" required="true" type="java.lang.String" %>
<%@attribute name="songTitle" required="true" type="java.lang.String" %>

<div class="container" style="padding: 5px; background-color: white;border-radius: 10px;">
    <div class="row d-flex justify-content-between align-items-center">
        <audio controls style="background-color: rgba(0, 0, 0, 0)">
            <source src="${src}" type="audio/mpeg">
        </audio>
        <h6>${songTitle}</h6>
        <button id="b${songId}" onclick="sendSongId(${songId})" type="button" class="btn btn-primary btn-dark">
            Add
        </button>
    </div>
</div>