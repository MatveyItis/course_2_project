<#macro navbar>
    <nav class="shadow-sm navbar sticky-top navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
                aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="navbar-brand" href="${context.getContextPath()}/home"><h4>WM</h4></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link s9" href="${context.getContextPath()}/user_profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/feed">Feed</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/favourite">Favourite</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/about">About us</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" method="post" action="${context.getContextPath()}/logout">
                <input type="submit" class="btn btn-outline-dark" value="<#if user??>Log out<#else>Log in</#if>">
            </form>
        </div>
    </nav>
</#macro>