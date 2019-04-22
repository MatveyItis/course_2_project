<#--
<div class="container">
    <a class="btn btn-outline-info" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Song editor
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group col-md-6 mt-3">
            <form method="post" enctype="multipart/form-data" action="${context.getContextPath()}/add_song">
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if song??>${song.title}<#else></#if>" name="title"
                           placeholder="Enter a song title"/>
                    <#if titleError??>
                        <div class="invalid-feedback">
                            ${titleError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if song??>${song.tag}<#else></#if>" name="tag" placeholder="Tag"/>
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="music_file" id="music_file" class="form-control" accept="audio/*"/>
                        <label class="custom-file-label" for="music_file">Choose song</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="img_file" id="img_file" class="form-control" accept="image/*"/>
                        <label class="custom-file-label" for="img_file">Choose cover for your song</label>
                    </div>
                </div>
                <input type="hidden" name="id" value="<#if song??>${song.id}<#else></#if>"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Save song</button>
                </div>
            </form>
        </div>
    </div>
</div>
-->


<!--Version with modal window-->
<div class="container">
    <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#exampleModal">
        Add song
    </button>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Create new song</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="post" enctype="multipart/form-data" action="${context.getContextPath()}/add_song"
                          id="song_form">
                        <div class="form-group">
                            <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                                   value="<#if song??>${song.title}<#else></#if>" name="title"
                                   placeholder="Enter a song title"/>
                            <#if titleError??>
                                <div class="invalid-feedback">
                                    ${titleError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control"
                                   value="<#if song??>${song.tag}<#else></#if>" name="tag" placeholder="Tag"/>
                            <#if tagError??>
                                <div class="invalid-feedback">
                                    ${tagError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <div class="custom-file">
                                <input type="file" name="music_file" id="music_file" class="form-control"
                                       accept="audio/*"/>
                                <label class="custom-file-label" for="music_file">Choose song</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="custom-file">
                                <input type="file" name="img_file" id="img_file" class="form-control" accept="image/*"/>
                                <label class="custom-file-label" for="img_file">Choose cover for your song</label>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="<#if song??>${song.id}<#else></#if>"/>
                        <input type="hidden" name="author" value="${user.id}">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" form="song_form">Add song</button>
                </div>
            </div>
        </div>
    </div>
</div>