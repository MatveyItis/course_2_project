<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>
<#import "parts/player.ftl" as p>

<@c.template "Feed">
    <@n.navbar/>
    <div class="container shadow bg-white rounded d-flex justify-content-between ut pt-2 mt-3">
        <div class="ut">
            <div class="form-group">
                <form method="get" action="/search_song/filter" class="form-inline">
                    <div class="form-group">
                        <input type="text" name="filter" class="form-control" placeholder="Search by tag..."
                               value="${filter!}"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-success ml-2">Поиск</button>
                    </div>
                </form>
            </div>
        </div>

        <#if user.isSinger()>
            <div class="ut">
                <#include "parts/song_edit.ftl" />
            </div>
        </#if>
    </div>

    <#include "parts/song_list.ftl" />

    <@p.player/>
</@c.template>