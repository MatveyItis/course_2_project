<#import "parts/common.ftl" as layout>
<#import "parts/navbar.ftl" as navbar>

<@layout.template "Home">
    <@navbar.navbar/>
    <div id="carouselSecond" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="https://7themes.su/_ph/29/560093602.jpg?1536888260" alt="First slide">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://images8.alphacoders.com/480/480999.gif"
                     alt="Second slide">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://ekburg.tv/uploads/ed50158d-5b42-4cc5-8d3d-32dff8b93eae/noyz.jpg"
                     alt="Third slide">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="https://images.wallpaperscraft.ru/image/dzhimi_khendriks_gitarist_virtuoz_pevets_kompozitor_98151_1920x1080.jpg"
                     alt="First slide">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="https://raw.githubusercontent.com/MatveyItis/course_2_project/cc5a5063ad54f818eeb700191f29f987ffa2a018/src/main/webapp/images/carousel/guitarist.jpeg"
                     alt="Second slide">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="https://raw.githubusercontent.com/MatveyItis/course_2_project/cc5a5063ad54f818eeb700191f29f987ffa2a018/src/main/webapp/images/carousel/concert.jpeg"
                     alt="Third Slide">
                <div class="carousel-caption d-none d-lg-block">
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselSecond" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselSecond" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="container pt-3" style="padding: 20%">
        <div class="row">
            <div class="container col-6" align="center">
                <h2>Try it</h2>
                <a role="button" href="/login" class="btn btn-dark">Continue</a>
            </div>
        </div>
    </div>
</@layout.template>