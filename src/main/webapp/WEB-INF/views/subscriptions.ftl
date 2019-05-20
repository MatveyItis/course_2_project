<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>

<@c.template "Subscriptions">
    <@n.navbar/>
    <div class="container d-flex justify-content-center">
        <div class="col-md-5 mt-4" align="center">
            <h3>${userChannel.username}</h3>
            <ul class="list-group">
                <#list users as user>
                    <li class="list-group-item">
                        <a href="${context.getContextPath()}/user_profile/${user.id}">${user.username}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</@c.template>