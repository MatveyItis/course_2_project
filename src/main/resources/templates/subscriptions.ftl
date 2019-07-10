<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>

<@c.template title>
    <@n.navbar/>
    <div class="container d-flex justify-content-center" style="padding-top: 60px">
        <div class="col-md-5" align="center">
            <h3><a href="/user_profile/${userChannel.id}"
                   style="text-decoration: none; color: dodgerblue">${userChannel.username}</a></h3>
            <#if users??>
                <ul class="list-group">
                    <#list users as user>
                        <li class="list-group-item">
                            <a href="/user_profile/${user.id}">${user.username}</a>
                        </li>
                    </#list>

                </ul>
            <#else>
                <img src="/static/img/subscribe.svg" alt="subscribe" width="256" height="256">
                <br>
                <h5>К сожалению, у вас нет подписок/подписчиков. </h5>
                <h5>Перейдите к поиску ваших первых друзей по <a href="/user/search"
                                                                 style="text-decoration: none; color: cornflowerblue">ссылке</a>
                </h5>
            </#if>
        </div>
    </div>
</@c.template>