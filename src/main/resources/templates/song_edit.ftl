<#import "parts/common.ftl" as c/>
<#import "parts/navbar.ftl" as n/>
<#import "/spring.ftl" as spring/>
<@c.template "Adding song">
    <@n.navbar/>
    <div class="container col-lg-6 col-xl-6 col-md-6" align="center" style="padding-top: 120px">
        <h3>Create new song</h3>
        <form method="post" enctype="multipart/form-data" action="/add_song"
              id="song_form">
            <@spring.bind "songForm"/>
            <div class="form-group">
                <@spring.formInput "songForm.title" 'class="form-control" placeholder="Enter a title" required'/>
                <@spring.showErrors "songForm.title" 'class="invalid-feedback"'/>
            </div>
            <div class="form-group">
                <@spring.formInput "songForm.tag" 'class="form-control" placeholder="Enter a tag" required'/>
                <@spring.showErrors "songForm.tag" 'class="invalid-feedback"'/>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="music_file"
                           id="music_file" class="form-control ${(musicFileError??)?string('is-invalid', '')}"
                           accept="audio/*" required/>
                    <#if musicFileError??>
                        <div class="invalid-feedback">
                            ${musicFileError}
                        </div>
                    </#if>
                    <label class="custom-file-label" for="music_file">Choose song</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="img_file" id="img_file" class="form-control" accept="image/*"/>
                    <label class="custom-file-label" for="img_file">Choose cover for your song</label>
                </div>
            </div>
            <button type="submit" class="btn btn-success">Add song</button>
        </form>
    </div>
</@c.template>
