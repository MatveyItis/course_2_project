<#import "parts/common.ftl" as layout>
<#import "parts/navbar.ftl" as navbar>

<@layout.template "About">
    <@navbar.navbar/>

    <div class="media-container">
        <h4><a id="audio-player" class="anchor" href="#audio-player" aria-hidden="true">
                <span aria-hidden="true" class="octicon octicon-link"></span></a>
            <div class="clearfix">
                <span class="title">Audio Player</span>
                <select name="player2-sources" id="player2-sources">
                    <option value="">Select source</option>
                    <option value="http://www.largesound.com/ashborytour/sound/AshboryBYU.mp3">MP3</option>
                    <!--<option value="http://www.vorbis.com/music/Hydrate-Kenny_Beltrey.ogg">OGG</option>-->
                    <option value="http://db3.indexcom.com/bucket/ram/00/05/64k/05.m3u8">HLS</option>
                    <!--<option value="rtmp://s3b78u0kbtx79q.cloudfront.net/cfx/st/mp3:fake_empire-cbr">RTMP</option>-->
                    <option value="https://api.soundcloud.com/tracks/323195515/stream?client_id=95f22ed54a5c297b1c41f72d713623ef">
                        SoundCloud
                    </option>
                </select>
            </div>
        </h4>

        <div class="player">
            <audio id="player2" preload="none" controls style="max-width: 100%">
                <source src="http://www.largesound.com/ashborytour/sound/AshboryBYU.mp3" type="audio/mp3">
            </audio>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelement-and-player.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/dailymotion.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/facebook.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/soundcloud.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/twitch.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/renderers/vimeo.min.js"></script>
    <script src="https://buttons.github.io/buttons.js"></script>
    <script src="https://platform.twitter.com/widgets.js"></script>
    <script>

        var
            sourcesSelector = document.body.querySelectorAll('select'),
            sourcesTotal = sourcesSelector.length
        ;

        for (var i = 0; i < sourcesTotal; i++) {
            sourcesSelector[i].addEventListener('change', function (e) {
                var
                    media = e.target.closest('.media-container').querySelector('.mejs__container').id,
                    player = mejs.players[media]
                ;

                player.setSrc(e.target.value.replace('&amp;', '&'));
                player.setPoster('');
                player.load();

            });

            // These media types cannot play at all on iOS, so disabling them
            if (mejs.Features.isiOS) {
                if (sourcesSelector[i].querySelector('option[value^="rtmp"]')) {
                    sourcesSelector[i].querySelector('option[value^="rtmp"]').disabled = true;
                }
                if (sourcesSelector[i].querySelector('option[value$="webm"]')) {
                    sourcesSelector[i].querySelector('option[value$="webm"]').disabled = true;
                }
                if (sourcesSelector[i].querySelector('option[value$=".mpd"]')) {
                    sourcesSelector[i].querySelector('option[value$=".mpd"]').disabled = true;
                }
                if (sourcesSelector[i].querySelector('option[value$=".ogg"]')) {
                    sourcesSelector[i].querySelector('option[value$=".ogg"]').disabled = true;
                }
                if (sourcesSelector[i].querySelector('option[value$=".flv"]')) {
                    sourcesSelector[i].querySelector('option[value*=".flv"]').disabled = true;
                }
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            var mediaElements = document.querySelectorAll('video, audio'), total = mediaElements.length;

            for (var i = 0; i < total; i++) {
                new MediaElementPlayer(mediaElements[i], {
                    pluginPath: 'https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/',
                    shimScriptAccess: 'always',
                    success: function () {
                        var target = document.body.querySelectorAll('.player'), targetTotal = target.length;
                        for (var j = 0; j < targetTotal; j++) {
                            target[j].style.visibility = 'visible';
                        }
                    }
                });
            }
        });
    </script>
</@layout.template>