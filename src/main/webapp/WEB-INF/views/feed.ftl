<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>
<@c.template "Feed">
    <@n.navbar/>
    <div class="container d-flex justify-content-between ut pt-3">
        <div class="ut">
            <div class="form-group">
                <form method="get" action="${context.getContextPath()}/search_song/filter" class="form-inline">
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
        <div class="ut">
            <#include "parts/songEdit.ftl" />
        </div>
    </div>

    <#include "parts/songList.ftl" />
</@c.template>