<#macro navbar>
    <nav class="shadow-sm navbar sticky-top navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
                aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="navbar-brand" href="/home"><h4>WM</h4></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user_profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/feed">Feed</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/favourite">Favourite</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/albums">Albums</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about">About us</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" method="post" action="/logout">
                <button type="submit" class="btn bg-light">
                    <#if user??>
                        <i class="fas fa-sign-out-alt"></i>
                    <#else>
                        <i class="fas fa-sign-in-alt"></i>
                    </#if>
                </button>
            </form>
        </div>
    </nav>
</#macro>