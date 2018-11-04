<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:layout title="Profile">
    <t:navbar/>
    <div class="container-fluid row" style="padding-top: 20px">
        <div class="col-3">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action  list-group-item-dark active show"
                   id="list-profile-list" data-toggle="list"
                   href="#list-profile" role="tab" aria-controls="profile">Profile
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-friends-list"
                   data-toggle="list"
                   href="#list-friends" role="tab" aria-controls="friends">Friends
                    <span class="badge badge-dark badge-pill" style="">14</span>
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-messages-list"
                   data-toggle="list"
                   href="#list-messages" role="tab" aria-controls="messages">Messages
                    <span class="badge badge-dark badge-pill">28</span>
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-music-list"
                   data-toggle="list"
                   href="#list-music" role="tab" aria-controls="music">Music</a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-settings-list"
                   data-toggle="list"
                   href="#list-settings" role="tab" aria-controls="settings">Settings</a>
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-profile" role="tabpanel"
                     aria-labelledby="list-profile-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                        <div class="row">
                            <div class="col-4">
                                <div class="container" style="width: inherit; padding: 10px">
                                    <img src="/images/avatarcat.png" alt="avatar" width="" height="33%"
                                         style="border-radius: 50%">
                                </div>
                            </div>
                            <div class="col-8">
                                <div class="container" style="padding: 10px">
                                    <h2>${user.getFirstName()} ${user.getLastName()}</h2>
                                    <hr>
                                    <div class="row">
                                        <div class="col-6">Date of birth:</div>
                                        <div class="col-6">11.11.1999</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">City:</div>
                                        <div class="col-6">Kazan</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">Lovely music:</div>
                                        <div class="col-6">Pop, Rap and etc</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <hr>
                            <div class="container-fluid">
                                <p>Here is my lovely songs:</p>
                                <p>Дорогие друзья, социально-экономическое развитие требует от нас анализа модели
                                    развития? Соображения высшего порядка, а также новая модель организационной
                                    деятельности влечет за собой процесс внедрения и модернизации системы обучения
                                    кадров, соответствующей насущным потребностям. Равным образом постоянное
                                    информационно-техническое обеспечение нашей деятельности представляет собой
                                    интересный эксперимент проверки существующих финансовых и административных
                                    условий?

                                    Значимость этих проблем настолько очевидна, что курс на
                                    социально-ориентированный национальный проект способствует повышению
                                    актуальности экономической целесообразности принимаемых решений.

                                    Задача организации, в особенности же начало повседневной работы по формированию
                                    позиции требует определения и уточнения существующих финансовых и
                                    административных условий. С другой стороны социально-экономическое развитие
                                    позволяет оценить значение форм воздействия? Таким образом, консультация с
                                    профессионалами из IT обеспечивает актуальность дальнейших направлений развитая
                                    системы массового участия!</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-friends" role="tabpanel" aria-labelledby="list-friends-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                        <p>Разнообразный и богатый опыт реализация намеченного плана развития позволяет выполнить важнейшие
                        задания по разработке экономической целесообразности принимаемых решений. Значимость этих
                        проблем настолько очевидна, что сложившаяся структура организации позволяет выполнить важнейшие
                        задания по разработке новых предложений! Повседневная практика показывает, что рамки и место
                        обучения кадров обеспечивает актуальность направлений прогрессивного развития.

                        Таким образом, постоянное информационно-техническое обеспечение нашей деятельности играет важную
                        роль в формировании соответствующих условий активизации.

                        Значимость этих проблем настолько очевидна, что рамки и место обучения кадров позволяет оценить
                        значение дальнейших направлений развития проекта. С другой стороны повышение уровня гражданского
                        сознания позволяет оценить значение новых предложений. С другой стороны постоянный
                        количественный рост и сфера нашей активности напрямую зависит от дальнейших направлений развитая
                        системы массового участия.

                        Повседневная практика показывает, что новая модель организационной деятельности играет важную
                        роль в формировании форм воздействия. Разнообразный и богатый опыт новая модель организационной
                        деятельности обеспечивает широкому кругу специалистов участие в формировании направлений
                        прогрессивного развития. Таким образом, дальнейшее развитие различных форм деятельности
                        представляет собой интересный эксперимент проверки форм воздействия. Не следует, однако,
                        забывать о том, что рамки и место обучения кадров обеспечивает актуальность соответствующих
                        условий активизации! Задача организации, в особенности же дальнейшее развитие различных форм
                        деятельности способствует повышению актуальности новых предложений? Таким образом, начало
                            повседневной работы по формированию позиции...</p>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                    </div>
                </div>
                <div class="tab-pane fade" id="list-music" role="tabpanel" aria-labelledby="list-music-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                    </div>
                </div>
                <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>