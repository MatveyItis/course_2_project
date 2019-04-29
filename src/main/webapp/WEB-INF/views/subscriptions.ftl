<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>

<@c.template "Subscriptions">
    <@n.navbar/>
    <div class="col-md-6 mt-4">
        <h3>${userChannel.username}</h3>
        <ul class="list-group">
            <#list users as user>
                <li class="list-group-item">
                    <a href="${context.getContextPath()}/user-songs/${user.id}">${user.username}</a>
                </li>
            </#list>
        </ul>
    </div>
</@c.template>