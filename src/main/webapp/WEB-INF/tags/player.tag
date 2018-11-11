<%@tag pageEncoding="UTF-8" description="Player tag" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar fixed-bottom navbar-light" style="background-color: rgba(0,0,0,0);">
    <a class="nav-link" href="about" style="color: white">(c)Privacy policy</a>
    <div class="container" style="border-radius: 10px">
        <button type="button" class="btn btn-light" id="prev" onclick="playPrevTrack()"><<</button>
        <button type="button" class="btn btn-light" id="play-pause" onclick="playTrack()"><img src="/images/player/iconmonstr-media-control-48-24.png"></button>
        <button type="button" class="btn btn-light" id="next" onclick="playNextTrack()">>></button>
    </div>
</nav>
