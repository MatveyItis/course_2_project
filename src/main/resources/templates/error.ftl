<#import "./parts/common.ftl" as c>

<@c.template "Error">
    <div align="center" class="container" style="padding-top: 120px">
        <h1>Упс! <a href="/feed" style="text-decoration: none;">На главную</a></h1>
        <table>
            <tr>
                <td><b>Path</b></td>
                <td>${path}</td>
            </tr>
            <tr>
                <td><b>Error</b></td>
                <td>${error}</td>
            </tr>
            <tr>
                <td><b>Status</b></td>
                <td>${status}</td>
            </tr>
            <tr>
                <td><b>Message</b></td>
                <td>${message}</td>
            </tr>
        </table>
        <br>
        <img src="/static/img/error.png" alt="error">
    </div>
</@c.template>