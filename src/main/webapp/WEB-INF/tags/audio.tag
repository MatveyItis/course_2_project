<%@tag description="Tag for audio form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="src" required="true" type="java.lang.String" %>
<%@attribute name="songId" required="true" type="java.lang.String" %>
<%@attribute name="songTitle" required="true" type="java.lang.String" %>

<div class="container">
    <form method="post" id="${songId}">
        <div class="row d-flex justify-content-between align-items-center">
            <audio controls style="background-color: rgba(0, 0, 0, 0)">
                <source src="${src}" type="audio/mpeg">
            </audio>
            <h4>${songTitle}</h4>
            <button type="submit" class="btn btn-primary btn-dark">
                Add
            </button>
        </div>
    </form>
</div>