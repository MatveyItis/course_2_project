<#import "/WEB-INF/ftltags/layout.ftl" as layout>
<#import "/WEB-INF/ftltags/navbar.ftl" as navbar>

<#if admin??>
    <@layout.template>
        <@navbar.navbar/>
<div class="container-fluid" style="padding: 20px">
    <div class="container col-6" align="center" style="padding: 20px; background-color: white">
        <h3>Song Upload</h3>
        <div class="container">
            <form enctype="multipart/form-data" method="post" action="uploadSong">
                <div class="form-group">
                    <label for="song-title">Enter please title of song</label>
                    <input type="text" class="form-control" placeholder="Song title" id="song-title"
                           name="song-title" required>
                </div>
                <div class="form-group">
                    <label for="artist">Choose artist:</label>
                    <select id="artist" name="artist" class="form-control" required>
                    <#list artists as artist>
                        <option value="${artist.getArtistId()}">
                            ${artist.getNickname()}
                        </option>
                    </#list>
                    </select>
                </div>
                <div class="custom-file">
                    <label class="custom-file-label" for="song-file">Choose file</label>
                    <input type="file" class="custom-file-input" id="song-file" name="song-file" required>
                </div>
                <input type="submit" class="btn btn-dark" value="Upload" style="margin-top: 20px">
            </form>
        </div>
    </div>
    <div class="container col-6" align="center" style="padding: 20px; background-color: white">
        <h3>Adding Artist to Music Library</h3>
        <div class="container">
            <form method="post">

            </form>
        </div>
    </div>
    <div class="container col-6" align="center" style="padding: 20px; background-color: white"></div>
    <div class="container col-6" align="center" style="padding: 20px; background-color: white"></div>
</div>
    </@layout.template>
<#else>
    <@layout.template>
    <div class="container" style="padding-top: 20%;" align="center">
        <h1 style="color: #fff;">Access denied</h1>
    </div>
    </@layout.template>
</#if>