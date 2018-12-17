<#macro navbar>
<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="navbar-brand" href="/home"><img src="/images/bg/header-icon.png" width="32" height="32"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/library">Library</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/about">About us</a>
            </li>
            <#if admin??>
                <li class="nav-item">
                    <a class="nav-link" href="/admin">Admin</a>
                </li>
            <#else>
            </#if>
        </ul>
        <#if authorized??>
            <form class="form-inline my-2 my-lg-0" action="logout" style="padding-right: 10px">
                <button class="btn btn-light">Log out</button>
            </form>
        <#else>
        </#if>
    </div>
</nav>
</#macro>