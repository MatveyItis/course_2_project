<#import "parts/common.ftl" as c>
<#import "parts/navbar.ftl" as n>
<@c.template "Albums">
    <@n.navbar/>
    <div class="container pt-2">
        <div class="container justify-content-end d-flex">
            <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#exampleModal">
                Add album
            </button>

            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Add new Album</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post" enctype="multipart/form-data"
                                  action="/add_album"
                                  id="album_form">
                                <div class="form-group">
                                    <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                                           value="<#if album??>${album.title}<#else></#if>" name="album_title"
                                           placeholder="Enter album title" required/>
                                    <#if titleError??>
                                        <div class="invalid-feedback">
                                            ${titleError}
                                        </div>
                                    </#if>
                                </div>
                                <div class="form-group">
                                    <div class="custom-file">
                                        <input type="file" name="album_cover"
                                               id="album_cover"
                                               class="form-control ${(albumCoverError??)?string('is-invalid', '')}"
                                               accept="image/*" required/>
                                        <#if albumCoverError??>
                                            <div class="invalid-feedback">
                                                ${albumCoverError}
                                            </div>
                                        </#if>
                                        <label class="custom-file-label" for="album_cover">Choose album's cover</label>
                                    </div>
                                </div>
                                <div class="container p-1" id="songs" data-count="1">
                                    <div class="row">
                                        <div class="form-group col-6">
                                            <input type="text"
                                                   class="form-control"
                                                   value="" name="song_title_1" id="song_title_1"
                                                   placeholder="Enter a song title" required/>
                                        </div>
                                        <div class="form-group col-6">
                                            <div class="custom-file">
                                                <input type="file" name="audio_file_1"
                                                       id="audio_file_1"
                                                       class="form-control"
                                                       accept="audio/*" required/>
                                                <label class="custom-file-label" for="audio_file_1">Choose songs</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="container">
                                    <button type="button" class="btn" onclick="addInputForFile()">song+</button>
                                </div>
                                <input type="hidden" name="id" value="<#if album??>${album.id}<#else></#if>"/>
                                <input type="hidden" name="owner" value="${user.id}">
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success" form="album_form">Add album</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container pt-4">
        <#list albums as album>
            <div class="row p-2 justify-content-between d-flex">
                <b>${album.title}</b>
                <s>${album.date}</s>
                <i>${album.owner.username}</i>
            </div>
        </#list>
    </div>
</@c.template>
