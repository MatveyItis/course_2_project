<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
        <a class="navbar-brand" href="<c:url value="/home"/>">Home</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/profile"/> ">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/library"/> ">Library</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/about"/> ">About us</a>
            </li>
            <c:if test="${not empty admin}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin"/>">Admin</a>
                </li>
            </c:if>
        </ul>
        <c:if test="${not empty authorized}">
            <form class="form-inline my-2 my-lg-0" action="logout" style="padding-right: 10px">
                <button class="btn btn-dark">Log out</button>
            </form>
        </c:if>
    </div>
</nav>